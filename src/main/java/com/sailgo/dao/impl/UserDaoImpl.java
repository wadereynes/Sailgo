package com.sailgo.dao.impl;

import com.sailgo.dao.UserDao;
import com.sailgo.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by gewa on 6/17/2017.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public int addUser(EntityManager em, UserEntity pUser) throws Exception{
        if (userFound(em, pUser.getUsername(), pUser.getPassword()) == null) {
            em.persist(pUser);
            em.flush();
            return pUser.getUserId();
        } else {
            return -1;
        }
    }

    @Override
    public UserEntity userFound(EntityManager em, String pUname, String pPass) {
        UserEntity user = null;

        try {
            Query userQuery = em.createQuery("From UserEntity WHERE username=:usernameParam AND password=:upassParam");
            userQuery.setParameter("usernameParam", pUname);
            userQuery.setParameter("upassParam", pPass);
            user = (UserEntity) userQuery.getResultList().get(0);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("UserModel Not Found");
        }

        return user;
    }

    @Override
    public UserEntity getUser(EntityManager em, int pUid) {
        UserEntity user = null;

        try {
            Query userQuery = em.createQuery("From User user WHERE user.user_Id = :uidParam ");
            userQuery.setParameter("uidParam", pUid);
            user = (UserEntity) userQuery.getSingleResult();
        } catch (IllegalArgumentException iae) {
            iae.getMessage();
        }

        return user;
    }

    @Override
    public UserEntity verifyUser(EntityManager pEM, String username, String password) {
        UserEntity user = null;
        try {
            Query userQuery = pEM.createQuery("From User user WHERE user.id.userId = :unameParam AND user.password = :passParam");
            userQuery.setParameter("unameParam", username);
            userQuery.setParameter("passParam", password);
            user = (UserEntity) userQuery.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }

        return user;
    }
}
