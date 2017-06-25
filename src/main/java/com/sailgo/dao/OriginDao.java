package com.sailgo.dao;

import com.sailgo.entity.OriginListEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by gewa on 6/19/2017.
 */
public interface OriginDao {

    public List<OriginListEntity> getAllOrigins(EntityManager pEM);

    public int addOrigin(EntityManager pEM, OriginListEntity OriginName) throws Exception;

    public OriginListEntity originFound(EntityManager pEM, String OriginName);
}
