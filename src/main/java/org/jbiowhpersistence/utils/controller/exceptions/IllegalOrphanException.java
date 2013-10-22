package org.jbiowhpersistence.utils.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class IllegalOrphanException extends Exception {

    private List messages;

    public IllegalOrphanException(List<String> messages) {
        super((messages != null && messages.size() > 0 ? messages.get(0) : null));
        if (messages == null) {
            this.messages = new ArrayList();
        } else {
            this.messages = messages;
        }
    }

    public List getMessages() {
        return messages;
    }
}
