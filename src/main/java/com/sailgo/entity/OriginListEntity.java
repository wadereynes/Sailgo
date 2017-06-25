package com.sailgo.entity;

import javax.persistence.*;

/**
 * Created by gewa on 6/20/2017.
 */
@Entity
@Table(name = "origin_list", schema = "sailgo", catalog = "")
public class OriginListEntity {
    private int originId;
    private String originName;

    @Id
    @Column(name = "origin_id")
    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    @Basic
    @Column(name = "origin_name")
    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OriginListEntity that = (OriginListEntity) o;

        if (originId != that.originId) return false;
        if (originName != null ? !originName.equals(that.originName) : that.originName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = originId;
        result = 31 * result + (originName != null ? originName.hashCode() : 0);
        return result;
    }
}
