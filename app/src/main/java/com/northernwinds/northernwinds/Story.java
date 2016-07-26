package com.northernwinds.northernwinds;

import com.backendless.Backendless;
import com.backendless.util.persistence.AbstractBackendlessDataObject;

import io.realm.RealmObject;

/*
import io.realm.RealmObject;

/**
 * Created by Dias on 14.07.2016.
 */
public class Story extends RealmObject{
    String objectId;
    String text;
    String nextChoice;
    String razdel;

    public Story() {
    }

    public Story(String objectId, String text, String nextChoice, String razdel) {
        this.objectId = objectId;
        this.text = text;
        this.nextChoice = nextChoice;
        this.razdel = razdel;
    }

    public String getRazdel() {
        return razdel;
    }

    public void setRazdel(String razdel) {
        this.razdel = razdel;
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
