package com.sailgo.service;

import com.sailgo.entity.UserEntity;
import com.sailgo.model.OriginModel;
import com.sailgo.model.UserModel;

import java.util.List;

/**
 * Created by gewa on 6/17/2017.
 */
public interface UserService {


    List<UserModel> getAll();

    UserModel findById(int user_id);

    UserModel findByName(String name);

    boolean create(UserEntity user);

    void update(UserModel userModel);

    void delete(int user_id);

    boolean exists(UserModel userModel);

    public String hashFunction(String word);

    public UserEntity login(String username, String password);


}
