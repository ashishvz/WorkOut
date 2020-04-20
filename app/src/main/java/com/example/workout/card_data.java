package com.example.workout;


public class card_data {
    private String name;
    private int time;
    private int thumbnail;
    private int sets;
    public card_data() {

    }

    public card_data(String name, int time, int thumbnail,int sets) {
        this.name = name;
        this.time = time;
        this.sets=sets;
        this.thumbnail = thumbnail;
    }


    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
