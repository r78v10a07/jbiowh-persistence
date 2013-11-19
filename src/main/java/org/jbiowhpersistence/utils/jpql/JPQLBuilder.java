package org.jbiowhpersistence.utils.jpql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.jbiowhcore.logger.VerbLogger;
import org.jbiowhcore.utility.constrains.JPLConstrains;
import org.jbiowhcore.utility.utils.BioWHPattern;
import org.jbiowhpersistence.utils.entitymanager.EntityParserFieldProxy;

/**
 * This Class build a SQL sentence
 *
 * $Author: r78v10a07@gmail.com $ $LastChangedDate: 2013-03-19 09:38:47 +0100
 * (Tue, 19 Mar 2013) $ $LastChangedRevision: 396 $
 *
 * @since Feb 20, 2012
 */
public class JPQLBuilder {

    private Class entity;
    private String id;
    private HashMap<String, Class> fields;
    private HashMap<String, Object> parm;
    private HashMap<String, String> fieldAfterWhere;
    private HashMap<String, String> fieldBeforeWhere;
    private HashMap<String, String> fieldOnEntity;
    private HashMap<Class, List> constrainField;
    private StringBuilder jpqlBeforeWhere;
    private StringBuilder jpqlAfterWhere;
    private String jpqlQuery;

    public JPQLBuilder(Class entity, String id, HashMap<String, Class> fields, HashMap<String, String> fieldAfterWhere, HashMap<String, String> fieldBeforeWhere, HashMap<String, String> fieldOnEntity, HashMap<Class, List> constrainField) {
        this.entity = entity;
        this.id = id;
        this.fields = fields;
        this.fieldAfterWhere = fieldAfterWhere;
        this.fieldBeforeWhere = fieldBeforeWhere;
        this.fieldOnEntity = fieldOnEntity;
        this.constrainField = constrainField;
        jpqlBeforeWhere = new StringBuilder();
        jpqlAfterWhere = new StringBuilder();
    }

    public HashMap<String, Object> getParm() {
        return parm;
    }

    /**
     * This method create the JPL query
     *
     * @param search is a List<List> object with the internal list has 4
     * elements. The first element is the logical operator to link with the
     * previous condition (empty string for the first list). The second element
     * is the FIELD on the DataSet to be used to compare. The third element is
     * the operator to compare. The fourth element is the string to be searched.
     * @param constrains The JPL Constrain to be used on the search
     * @return a JPLQ string
     * @throws java.sql.SQLException
     */
    public String getJpqlQuery(List search, JPLConstrains constrains) throws SQLException {
        createJPQL(search, constrains);
        VerbLogger.getInstance().log(this.getClass(), "JPQL: " + jpqlQuery);
        for (String obj : parm.keySet()) {
            VerbLogger.getInstance().log(this.getClass(), "JPQL Parameters: " + obj + " --> '" + parm.get(obj) + "'");
        }

        return jpqlQuery;
    }

    private void createJPQL(List search, JPLConstrains constrains)
            throws SQLException {
        StringBuilder beforeWhere = new StringBuilder();
        if (!search.isEmpty()) {
            getParamFromSearch(search);
            boolean groupBy = false;
            int i = 0;
            for (Object a : search) {
                if (a instanceof List) {
                    if (((List) a).size() == 4) {
                        String key = (String) ((List) a).get(1);
                        String oper = (String) ((List) a).get(2);
                        if (i != 0) {
                            jpqlAfterWhere.append((String) ((List) a).get(0)).append(" ");
                        }
                        for (String field : fields.keySet()) {
                            if (key.equals(field)) {
                                if (!isBooleanOperation(oper)) {
                                    if (fieldBeforeWhere.get(key) != null) {
                                        if (!fieldBeforeWhere.get(key).isEmpty()) {
                                            groupBy = true;
                                        }
                                    }
                                    if (beforeWhere.indexOf(fieldBeforeWhere.get(key)) == -1) {
                                        beforeWhere.append(fieldBeforeWhere.get(key));
                                    }                                                                 
                                    jpqlAfterWhere.append(fieldAfterWhere.get(key)).append(" ").
                                            append(oper).append(" ").
                                            append(":").append(key).append(i);
                                } else {
                                    jpqlAfterWhere.append(fieldOnEntity.get(key)).append(" ").append(oper).append(" ");
                                }
                                break;
                            }
                        }
                        i++;
                    }
                }
            }
            if (constrains != null || groupBy) {
                jpqlBeforeWhere.append("SELECT g.").append(id).append(" FROM ");
                if (constrains != null) {
                    jpqlAfterWhere.append(" AND ");
                }
            } else {
                jpqlBeforeWhere.append("SELECT g FROM ");
            }
            jpqlBeforeWhere.append(entity.getSimpleName()).append(" g ").append(beforeWhere.toString());
            jpqlAfterWhere.append(getStringConstrains(constrains, true, i));
            jpqlQuery = jpqlBeforeWhere.toString() + " WHERE " + jpqlAfterWhere.toString();
            if (constrains != null || groupBy) {
                jpqlQuery = jpqlQuery + " group by g." + id;
            }
        } else {
            if (constrains != null) {
                jpqlAfterWhere.append(getStringConstrains(constrains, false, 0));
                jpqlQuery = "SELECT g." + id + " FROM " + entity.getSimpleName() + " g " + jpqlBeforeWhere.toString() + " WHERE " + jpqlAfterWhere.toString() + " group by g." + id;
            }
        }
    }

    private String getStringConstrains(JPLConstrains constrains, boolean parenthesis, int parameterIndex)
            throws SQLException {

        if (constrains != null) {
            HashMap<String, Object> parameters = new HashMap();
            StringBuilder compareSyntax = new StringBuilder();
            StringBuilder innerJoinSyntax = new StringBuilder();

            if (parenthesis) {
                compareSyntax.append("(");
            }

            for (int i = 0; i < constrains.getConstrains().size(); i++) {
                if (i > 0 && i <= constrains.getExtOperation().size()) {
                    compareSyntax.append(constrains.getExtOperation().get(i - 1));
                }

                if (constrains.getConstrainObject(i) instanceof JPLConstrains) {
                    compareSyntax.append(getStringConstrains((JPLConstrains) constrains.getConstrains().get(i), true, parameterIndex + constrains.getConstrains().size()));
                } else {
                    if (constrainField.get(constrains.getConstrainObject(i).getClass()) != null) {
                        List constrainFieldList = constrainField.get(constrains.getConstrainObject(i).getClass());

                        String joinOp = "INNER";
                        if (constrainFieldList.size() > 20) {
                            joinOp = "LEFT OUTER";
                        }
                        for (int j = 0; j < 2/*constrainFieldList.size() */; j += 2) {
                            String mappingField = (String) constrainFieldList.get(j);
                            String compareField = (String) constrainFieldList.get(j + 1);

                            if (j != 0) {
                                compareSyntax.append(" OR ");
                            }

                            if (!mappingField.isEmpty()) {
                                innerJoinSyntax.append(joinOp).append(" JOIN ").append(mappingField).append(" t").append(parameterIndex + i).append(j).append(" ");
                                if (!compareField.isEmpty()) {
                                    compareSyntax.append(" t").append(parameterIndex + i).append(j).append(".").append(compareField).append(" ").append(constrains.getOperation().get(i)).append(" :parm").append(parameterIndex + i).append(" ");
                                }
                            } else {
                                if (!compareField.isEmpty()) {
                                    compareSyntax.append(" ").append(compareField).append(" ").append(constrains.getOperation().get(i)).append(" :parm").append(parameterIndex + i).append(" ");
                                }
                            }
                        }
                        if (constrains.getConstrains().get(i) instanceof Collection) {
                            List wid = new ArrayList();
                            for (Object t : ((Collection<Object>) constrains.getConstrains().get(i))) {
                                Long widL = EntityParserFieldProxy.getInstance().getWID(t);
                                if (widL != null) {
                                    wid.add(widL);
                                }
                            }
                            parameters.put("parm" + (parameterIndex + i), wid);
                        }

                        if (parameters.get("parm" + (parameterIndex + i)) == null) {
                            throw new SQLException(
                                    "There is not parameter object assosiation before this exception");
                        }
                    } else {
                        throw new SQLException(
                                "The constrain DataSet " + constrains.getConstrainObject(i).getClass().getSimpleName()
                                + " is not related with the used DataSet "
                                + entity.getSimpleName());
                    }
                }
            }

            if (parenthesis) {
                compareSyntax.append(")");
            }

            if (!parameters.isEmpty()) {
                if (parm == null) {
                    parm = new HashMap();
                }
                parm.putAll(parameters);
            }

            jpqlBeforeWhere.append(innerJoinSyntax.toString());
            return compareSyntax.toString();
        }
        return "";
    }

    private void getParamFromSearch(List search) {
        int i = 0;
        parm = new HashMap();
        for (Object a : search) {
            if (a instanceof List) {
                if (((List) a).size() == 4) {
                    String key = (String) ((List) a).get(1) + i;
                    Object value = ((List) a).get(3);
                    if (value instanceof String) {
                        if (BioWHPattern.getInstance().isLong((String) value)) {
                            parm.put(key, new Long((String) value));
                        } else {
                            parm.put(key, value);
                        }
                    } else {
                        parm.put(key, value);
                    }
                    i++;
                }
            }
        }
    }

    private boolean isBooleanOperation(String ope) {
        if (ope.equals("is null") || ope.equals("is not null")) {
            return true;
        }
        return false;
    }
}
