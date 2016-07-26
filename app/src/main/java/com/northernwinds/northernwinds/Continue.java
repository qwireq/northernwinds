package com.northernwinds.northernwinds;

import io.realm.RealmObject;

/**
 * Created by Dias on 21.07.2016.
 */
public class Continue extends RealmObject {
    private String Id;

    public Continue() {
    }

    public Continue(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
