package com.sailgo.service;

import com.sailgo.entity.OriginListEntity;

import java.util.List;

/**
 * Created by gewa on 6/19/2017.
 */
public interface OriginService {

     public List<OriginListEntity> getAllOrigins();

     boolean create(OriginListEntity origin);

}
