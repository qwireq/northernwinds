package com.northernwinds.northernwinds;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Dias on 20.07.2016.
 */
public class DataHolder {
    private static DataHolder dataHolder;

    public DataHolder() {
    }

    public static DataHolder getDataHolder(){
        if(dataHolder == null){
            dataHolder = new DataHolder();
            /*
            RealmResults <Choice> choices = myRealm.where(Choice.class).findAll();
            RealmResults <Story> stories = myRealm.where(Story.class).findAll();
            RealmResults <Oku> okus = myRealm.where(Oku.class).findAll();

            if(stories.size() > 0){
                dataHolder.storyList = stories;
                dataHolder.okuList = okus;
                dataHolder.choiceList = choices;
                Log.d("DataHolder","Bar eken");
            }
            else{*/
                dataHolder.storyList = new ArrayList<>();
                dataHolder.choiceList = new ArrayList<>();
                dataHolder.okuList = new ArrayList<>();
                dataHolder.aContinue = new Continue("");
                Log.d("DataHolder","Jok eken");
            //}
        }
        return dataHolder;
    }

    private List<Choice> choiceList;
    private List<Story> storyList;
    private List<Oku> okuList;
    private Continue aContinue;

    public Continue getaContinue() {
        return aContinue;
    }

    public void setaContinue(Continue aContinue) {
        this.aContinue = aContinue;
    }

    public List<Choice> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Choice> choiceList) {
        this.choiceList = choiceList;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }

    public List<Oku> getOkuList() {
        return okuList;
    }

    public void setOkuList(List<Oku> okuList) {
        this.okuList = okuList;
    }
}
