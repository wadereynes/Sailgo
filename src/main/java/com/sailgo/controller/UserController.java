package com.sailgo.controller;

import com.sailgo.entity.UserEntity;
import com.sailgo.model.JSONResponse.UserResponse;
import com.sailgo.model.UserModel;
import com.sailgo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gewa on 6/17/2017.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;




    //    GET ALL Users

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserModel>> getAll() {
        LOG.info("getting all userModels");
        List<UserModel> userModels = userService.getAll();

        if (userModels == null || userModels.isEmpty()) {
            LOG.info("no userModels found");
            return new ResponseEntity<List<UserModel>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<UserModel>>(userModels, HttpStatus.OK);
    }

    //   GET UserModel By ID

    @RequestMapping(value = "{user_id}", method = RequestMethod.GET)
    public ResponseEntity<UserModel> get(@PathVariable("user_id") int user_id) {
        LOG.info("getting userModel with id: {}", user_id);
        UserModel userModel = userService.findById(user_id);

        if (userModel == null) {
            LOG.info("userModel with id {} not found", user_id);
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
    }




    //  Update Existing
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserModel> update(@PathVariable int user_id, @RequestBody UserModel userModel) {
        LOG.info("updating userModel: {}", userModel);
        UserModel currentUserModel = userService.findById(user_id);

        if (currentUserModel == null) {
            LOG.info("UserModel with id {} not found", user_id);
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }

        currentUserModel.setUser_id(userModel.getUser_id());
        currentUserModel.setUsername(userModel.getUsername());

        userService.update(userModel);
        return new ResponseEntity<UserModel>(currentUserModel, HttpStatus.OK);
    }

    //  Delete UserModel
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int user_id) {
        LOG.info("deleting userModel with id: {}", user_id);
        UserModel userModel = userService.findById(user_id);

        if (userModel == null) {
            LOG.info("Unable to delete. UserModel with id {} not found", user_id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        userService.delete(user_id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserResponse> signupAPI(
            @RequestParam(value = "firstName") String firstname,
            @RequestParam(value = "lastName") String lastname,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "islandCity") String city,
            @RequestParam(value = "stateRegion") String region,
            @RequestParam(value = "zipPostcode") String postcode,
            @RequestParam(value = "country") String country,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password){

        UserEntity user = new UserEntity();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setAddress(address);
        user.setIslandCity(city);
        user.setStateRegion(region);
        user.setZipPostcode(postcode);
        user.setCountry(country);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        try {
            boolean success = userService.create(user);
            if (success) {
                System.out.println("A UserModel with username " + username + " is successfully added!");
                return new ResponseEntity<>(new UserResponse(200, user), HttpStatus.OK);
            } else {
                System.out.println("A UserModel with username " + username + " already exist!");
                return new ResponseEntity<>(new UserResponse(200, user), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(204, user), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<UserResponse> loginAPI(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password){


        UserEntity user = null;
        try {

            user = userService.login(username, password);
            if (user==null) {
                System.out.println("A UserModel with username " + username + " is not found!");
                return new ResponseEntity<>(new UserResponse(200, user), HttpStatus.OK);
            } else {
                System.out.println("A UserModel with username " + username + " is found");
                return new ResponseEntity<>(new UserResponse(200, user), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(204, user), HttpStatus.BAD_REQUEST);
        }
    }




}
