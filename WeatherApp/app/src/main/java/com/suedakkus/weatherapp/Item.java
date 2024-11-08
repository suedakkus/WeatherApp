package com.suedakkus.weatherapp;

public class Item {
    String temp;
    String description;
    String time;
    String date;
    int image;

    public Item(String temp, String description, int image) {
        this.temp = String.valueOf(temp);
        this.description = description;
        this.time = time;
        this.date = date;
        this.image = image;
    }


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
