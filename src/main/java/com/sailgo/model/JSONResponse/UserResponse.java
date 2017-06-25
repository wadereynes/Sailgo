package com.sailgo.model.JSONResponse;

import com.sailgo.entity.UserEntity;

/**
 * Created by gewa on 6/17/2017.
 */
public class UserResponse {

    private int code;
    private UserEntity user;

    public UserResponse() {}

    public UserResponse(int code, UserEntity user) {
        this.code = code;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "code=" + code +
                ", user=" + user +
                '}';
    }
}
