package com.sailgo.model.JSONResponse;

import com.sailgo.entity.OriginListEntity;

/**
 * Created by gewa on 6/19/2017.
 */
public class OriginResponse {

    private int code;
    private OriginListEntity origin;

    public OriginResponse() {
    }

    public OriginResponse(int code, OriginListEntity origin) {
        this.code = code;
        this.origin = origin;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public OriginListEntity getOrigin() {
        return origin;
    }

    public void setOrigin(OriginListEntity origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "OriginResponse{" +
                "code=" + code +
                ", origin=" + origin +
                '}';
    }
}
