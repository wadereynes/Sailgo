package com.sailgo.service.impl;

import com.sailgo.dao.UserDao;
import com.sailgo.entity.UserEntity;
import com.sailgo.model.UserModel;
import com.sailgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gewa on 6/17/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JpaTransactionManager transactionManager;

    private static final AtomicInteger counter = new AtomicInteger();
    static List<UserModel> userModels = new ArrayList<UserModel>(
            Arrays.asList(
                    new UserModel(counter.incrementAndGet(),"Show","Show","Show","Show","Show","Show","Show","Show","Show","Show","Show"),
                    new UserModel(counter.incrementAndGet(),"Wade","Wade","Wade","Wade","Wade","Wade","Wade","Wade","Wade","Wade","Wade")));

    @Override
    public List<UserModel> getAll() {
        return userModels;
    }

    @Override
    public UserModel findById(int user_id) {
        for (UserModel userModel : userModels){
            if (userModel.getUser_id() == user_id){
                return userModel;
            }
        }
        return null;
    }

    @Override
    public UserModel findByName(String name) {
        for (UserModel userModel : userModels){
            if (userModel.getUsername().equals(name)){
                return userModel;
            }
        }
        return null;
    }

    @Override
    public boolean create(UserEntity user) {

        boolean success = true;

        String hashpass = hashFunction(user.getPassword());
        user.setPassword(hashpass);

        EntityManager em = transactionManager.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            if (userDao.addUser(em, user) == -1) {
                throw new Exception("Username " + user.getUsername() + " and Password " + user.getPassword() + " already exists!");
            }
            //Add if email is already registered....
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
            success = false;
            if (transaction.isActive())
                transaction.rollback();
        } finally {
            System.out.println("ADD USER : " + success);
        }

        if (em.isOpen())
            em.close();

        return success;
    }

    @Override
    public void update(UserModel userModel) {
        int index = userModels.indexOf(userModel);
        userModels.set(index, userModel);
    }

    @Override
    public void delete(int user_id) {
        UserModel userModel = findById(user_id);
        userModels.remove(userModel);
    }

    @Override
    public boolean exists(UserModel userModel) {
        return findByName(userModel.getUsername()) != null;
    }


    public String hashFunction(String word){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(word.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public UserEntity login(String username, String password) {
        EntityManager em = transactionManager.getEntityManagerFactory().createEntityManager();
        String hashpass = hashFunction(password);
        UserEntity user = userDao.userFound(em, username, hashpass);

        if (em.isOpen())
            em.close();

        return user;
    }


}
