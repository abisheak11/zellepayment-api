package com.zellepayment.controller;

import com.zellepayment.exception.UsersException;
import com.zellepayment.model.UserRequest;
import com.zellepayment.model.UserRequestUpdate;
import com.zellepayment.model.UsersTO;
import com.zellepayment.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity <List<UsersTO>> getAllUsers()throws UsersException{
        log.info("fetching all users details....");
        List<UsersTO>usersList = null;

        usersList = userService.getUsers();
        log.info("Successfully fetched {} user details",usersList.size());
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity <UsersTO> getUserById(@PathVariable("id")int userId)throws UsersException{
        log.info("fetching user details by id....");
        UsersTO user = null;
        user=userService.getUserId(userId);

        log.info("Successfully fetched user details by id");
        return ResponseEntity.ok(user);

    }
    @GetMapping("/phone")
    public ResponseEntity <UsersTO> getUserByPhoneNumber(@RequestParam("phone")String phoneNumber)throws UsersException{
        log.info("fetching user details by PhoneNumber....");
        UsersTO user = null;
            user=userService.getUserByPhoneNumber(phoneNumber);
        log.info("Successfully fetched user details by PhoneNumber");
        return ResponseEntity.ok(user);
    }

    //insertion
    @PostMapping
    public ResponseEntity<Integer> saveUser(@RequestBody @Valid UserRequest userRequest)throws UsersException{
        log.info("inside the UserController.saveUser, UserRequest:{}",userRequest);
        int response = userService.saveUser(userRequest);
        log.info("saveUser repones:{}",response);
        log.info("End of userController.saveUser");
        return ResponseEntity.ok(response);

    }
    //update
    @PutMapping
    public ResponseEntity<Integer> updateUser(@RequestBody @Valid UserRequestUpdate userRequest1)throws UsersException{
        log.info("inside the UserController.updateUser, UserRequestUpdate:{}",userRequest1);
        int response = userService.updateUser(userRequest1);
        log.info("updateUser repones:{}",response);
        log.info("End of userController.updateUser");
        return ResponseEntity.ok(response);
    }
    //delete
    @DeleteMapping("/userId")
    public ResponseEntity<String> deleteUser(@RequestParam("userId") int userId)throws UsersException{
        log.info("inside the UserController.deleteUser, deleteUser:{}",userId);
        String response = userService.deleteUser(userId);
        log.info("deleteUser response:{}",response);
        log.info("End of UserController.deleteUser");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/name")
    public ResponseEntity<String> deleteUserByFullName(@RequestParam String name) throws UsersException {

        log.info("inside controller deleteUserByFullName: {}", name);
        String response = userService.deleteUserByFullName(name);
        return ResponseEntity.ok(response);
    }
//    @GetMapping("/multiple")
//    public ResponseEntity <List<UsersTO>> getRecords(@RequestParam("multiple")List<Integer> userid)throws UsersException {
//        log.info("fetching users details by id....");
//        List<UsersTO> user = userService.getRecords(userid);
//        log.info("Successfully fetched users details by id");
//        return ResponseEntity.ok(user);
//    }

}
