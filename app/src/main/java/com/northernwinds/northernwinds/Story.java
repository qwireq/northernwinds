package com.northernwinds.northernwinds;

import com.backendless.Backendless;
import com.backendless.util.persistence.AbstractBackendlessDataObject;

/**
 * Created by Dias on 14.07.2016.
 */
public class Story {
    String objectId;
    String text;
    String nextChoice;

    public Story() {
    }

    public Story(String objectId, String text, String nextChoice) {
        this.objectId = objectId;
        this.text = text;
        this.nextChoice = nextChoice;
    }

    public String getNextChoice() {
        return nextChoice;
    }

    public void setNextChoice(String nextChoice) {
        this.nextChoice = nextChoice;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
