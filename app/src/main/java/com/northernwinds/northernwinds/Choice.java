package com.northernwinds.northernwinds;

import com.backendless.Backendless;
import com.backendless.util.persistence.AbstractBackendlessDataObject;

/**
 * Created by Dias on 14.07.2016.
 */
public class Choice {
    String objectId;
    String first;
    String second;
    String storyFirst;
    String storySecond;
    public Choice() {
    }

    public Choice(String objectId, String first, String second, String storyFirst, String storySecond) {
        this.objectId = objectId;
        this.first = first;
        this.second = second;
        this.storyFirst = storyFirst;
        this.storySecond = storySecond;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }


    public String getStoryFirst() {
        return storyFirst;
    }

    public void setStoryFirst(String storyFirst) {
        this.storyFirst = storyFirst;
    }

    public String getStorySecond() {
        return storySecond;
    }

    public void setStorySecond(String storySecond) {
        this.storySecond = storySecond;
    }
}
