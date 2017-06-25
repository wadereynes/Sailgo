package com.sailgo.model;

/**
 * Created by gewa on 6/19/2017.
 */
public class OriginModel {

    private int origin_id;
    private String origin_name;

    public OriginModel() {
    }

    public OriginModel(String origin_name) {
        this.origin_name = origin_name;
    }

    public OriginModel(int origin_id, String origin_name) {
        this.origin_id = origin_id;
        this.origin_name = origin_name;
    }

    public int getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(int origin_id) {
        this.origin_id = origin_id;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    @Override
    public String toString() {
        return "OriginModel{" +
                "origin_id=" + origin_id +
                ", origin_name='" + origin_name + '\'' +
                '}';
    }


}
