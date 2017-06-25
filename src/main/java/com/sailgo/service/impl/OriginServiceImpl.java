package com.sailgo.service.impl;

import com.sailgo.dao.OriginDao;
import com.sailgo.entity.OriginListEntity;
import com.sailgo.service.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


/**
 * Created by gewa on 6/19/2017.
 */
@Service
public class OriginServiceImpl implements OriginService {


    @Autowired
    private OriginDao originDao;

    @Autowired
    private JpaTransactionManager transactionManager;


    @Override
    public List<OriginListEntity> getAllOrigins() {
        EntityManager em = transactionManager.getEntityManagerFactory()
                .createEntityManager();

        List<OriginListEntity> originList = originDao.getAllOrigins(em);

        if (em.isOpen())
            em.close();

        return originList;
    }


    @Override
    public boolean create(OriginListEntity origin) {

        boolean success = true;


        EntityManager em = transactionManager.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            if (originDao.addOrigin(em, origin) == -1) {
                throw new Exception("Origin Name " + origin.getOriginName() + " already exists!");
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

}
