package com.sailgo.controller;

import com.sailgo.entity.OriginListEntity;
import com.sailgo.model.JSONResponse.OriginResponse;
import com.sailgo.service.OriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gewa on 6/19/2017.
 */
@RestController
@RequestMapping("/api/origin")
public class OriginController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OriginService originService;



    @RequestMapping(value = "/get-originlist", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<OriginListEntity>> getOriginlistAPI() {
        LOG.info("getting all originModels");
        List<OriginListEntity> originModels = originService.getAllOrigins();

        if (originModels == null || originModels.isEmpty()) {
            LOG.info("no userModels found");
            return new ResponseEntity<List<OriginListEntity>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<OriginListEntity>>(originModels, HttpStatus.OK);
    }


    @RequestMapping(value = "/add-origin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<OriginResponse> addOriginAPI(
            @RequestParam(value = "origin_name") String origin_name){

        OriginListEntity origin = new OriginListEntity();
        origin.setOriginName(origin_name);


        try {
            boolean success = originService.create(origin);
            if (success) {
                System.out.println("A OriginModel with origin_name " + origin + " is successfully added!");
                return new ResponseEntity<>(new OriginResponse(200, origin), HttpStatus.OK);
            } else {
                System.out.println("A OriginModel with origin_name " + origin + " already exist!");
                return new ResponseEntity<>(new OriginResponse(200, origin), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new OriginResponse(204, origin), HttpStatus.BAD_REQUEST);
        }
    }



}
