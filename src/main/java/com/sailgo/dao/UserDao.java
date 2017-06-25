package com.sailgo.dao;

import com.sailgo.entity.UserEntity;

import javax.persistence.EntityManager;

/**
 * Created by gewa on 6/17/2017.
 */
public interface UserDao {

    public int addUser(EntityManager pEM, UserEntity pUser) throws Exception;

    public UserEntity userFound(EntityManager pEM, String pUname, String pPass);

    public UserEntity getUser(EntityManager pEM, int pUid);

    public UserEntity verifyUser(EntityManager pEM, String username, String password);
}
