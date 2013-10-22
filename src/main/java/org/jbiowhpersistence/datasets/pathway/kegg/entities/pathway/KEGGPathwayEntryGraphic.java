package org.jbiowhpersistence.datasets.pathway.kegg.entities.pathway;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is the KEGG Pathway Entry Graphic entity
 *
 * $Author: r78v10a07@gmail.com $
 * $LastChangedDate: 2012-10-03 22:11:05 +0200 (Wed, 03 Oct 2012) $
 * $LastChangedRevision: 270 $
 * @since Nov 26, 2011
 */
@Entity
@Table(name = "KEGGPathwayEntryGraphic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findAll", query = "SELECT k FROM KEGGPathwayEntryGraphic k"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByWid", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.wid = :wid"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByKEGGPathwayEntryWID", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.kEGGPathwayEntryWID = :kEGGPathwayEntryWID"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByName", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.name = :name"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByFGColor", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.fGColor = :fGColor"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByBGColor", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.bGColor = :bGColor"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByType", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.type = :type"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByX", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.x = :x"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByY", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.y = :y"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByCoord", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.coord = :coord"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByWidth", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.width = :width"),
    @NamedQuery(name = "KEGGPathwayEntryGraphic.findByHeight", query = "SELECT k FROM KEGGPathwayEntryGraphic k WHERE k.height = :height")})
public class KEGGPathwayEntryGraphic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WID")
    private Long wid;
    @Basic(optional = false)
    @Column(name = "KEGGPathwayEntry_WID")
    private long kEGGPathwayEntryWID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "FGColor")
    private String fGColor;
    @Column(name = "BGColor")
    private String bGColor;
    @Column(name = "Type")
    private String type;
    @Column(name = "X")
    private Integer x;
    @Column(name = "Y")
    private Integer y;
    @Column(name = "Coord")
    private String coord;
    @Column(name = "Width")
    private Integer width;
    @Column(name = "Height")
    private Integer height;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "KEGGPathwayEntry_WID", insertable = false, unique = false, nullable = true, updatable = false)
    private KEGGPathwayEntry kEGGPathwayEntry;

    public KEGGPathwayEntryGraphic() {
    }

    public KEGGPathwayEntryGraphic(Long wid) {
        this.wid = wid;
    }

    public KEGGPathwayEntryGraphic(Long wid, long kEGGPathwayEntryWID, String name) {
        this.wid = wid;
        this.kEGGPathwayEntryWID = kEGGPathwayEntryWID;
        this.name = name;
    }

    public String getbGColor() {
        return bGColor;
    }

    public void setbGColor(String bGColor) {
        this.bGColor = bGColor;
    }

    public String getfGColor() {
        return fGColor;
    }

    public void setfGColor(String fGColor) {
        this.fGColor = fGColor;
    }

    public KEGGPathwayEntry getkEGGPathwayEntry() {
        return kEGGPathwayEntry;
    }

    public void setkEGGPathwayEntry(KEGGPathwayEntry kEGGPathwayEntry) {
        this.kEGGPathwayEntry = kEGGPathwayEntry;
    }

    public long getkEGGPathwayEntryWID() {
        return kEGGPathwayEntryWID;
    }

    public void setkEGGPathwayEntryWID(long kEGGPathwayEntryWID) {
        this.kEGGPathwayEntryWID = kEGGPathwayEntryWID;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public long getKEGGPathwayEntryWID() {
        return kEGGPathwayEntryWID;
    }

    public void setKEGGPathwayEntryWID(long kEGGPathwayEntryWID) {
        this.kEGGPathwayEntryWID = kEGGPathwayEntryWID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFGColor() {
        return fGColor;
    }

    public void setFGColor(String fGColor) {
        this.fGColor = fGColor;
    }

    public String getBGColor() {
        return bGColor;
    }

    public void setBGColor(String bGColor) {
        this.bGColor = bGColor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KEGGPathwayEntryGraphic other = (KEGGPathwayEntryGraphic) obj;
        if (!Objects.equals(this.wid, other.wid)) {
            return false;
        }
        if (this.kEGGPathwayEntryWID != other.kEGGPathwayEntryWID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.fGColor, other.fGColor)) {
            return false;
        }
        if (!Objects.equals(this.bGColor, other.bGColor)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.x, other.x)) {
            return false;
        }
        if (!Objects.equals(this.y, other.y)) {
            return false;
        }
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        if (!Objects.equals(this.width, other.width)) {
            return false;
        }
        if (!Objects.equals(this.height, other.height)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KEGGPathwayEntryGraphic{" + "wid=" + wid + ", kEGGPathwayEntryWID=" + kEGGPathwayEntryWID + ", name=" + name + ", fGColor=" + fGColor + ", bGColor=" + bGColor + ", type=" + type + ", x=" + x + ", y=" + y + ", coord=" + coord + ", width=" + width + ", height=" + height + '}';
    }
}
