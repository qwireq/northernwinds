package com.northernwinds.northernwinds;

import android.text.Html;

import io.realm.RealmObject;


/**
 * Created by Dias on 20.07.2016.
 */
public class Oku extends RealmObject{
    private String text;
    private String objectId;

    public Oku() {
    }

    public Oku(String text, String objectId) {
        this.text = text;
        this.objectId = objectId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
