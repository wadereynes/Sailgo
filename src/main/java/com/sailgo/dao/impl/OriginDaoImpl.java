package com.sailgo.dao.impl;

import com.sailgo.dao.OriginDao;
import com.sailgo.entity.OriginListEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by gewa on 6/19/2017.
 */
@Repository("originDao")
public class OriginDaoImpl implements OriginDao {

    @Override
    public List<OriginListEntity> getAllOrigins(EntityManager em) {
        Query query = em.createQuery("From OriginListEntity");
        List<OriginListEntity> originList = query.getResultList();

        return originList;
    }

    @Override
    public int addOrigin(EntityManager em, OriginListEntity OriginName) throws Exception{
        if (originFound(em, OriginName.getOriginName()) == null) {
            em.persist(OriginName);
            em.flush();
            return OriginName.getOriginId();
        } else {
            return -1;
        }
    }

    @Override
    public OriginListEntity originFound(EntityManager em, String OriginName) {
        OriginListEntity origin_name = null;

        try {
            Query originQuery = em.createQuery("From OriginListEntity WHERE origin_name=:origin_nameParam");
            originQuery.setParameter("origin_nameParam", OriginName);
            origin_name = (OriginListEntity) originQuery.getResultList().get(0);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("OriginModel Not Found");
        }

        return origin_name;
    }

}
