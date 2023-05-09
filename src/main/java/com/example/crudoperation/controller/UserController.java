package com.example.crudoperation.controller;

import com.example.crudoperation.model.User;
import com.example.crudoperation.model.request.UserRequest;
import com.example.crudoperation.service.UserService;
import com.example.crudoperation.utils.Response;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public Response<PageInfo<User>> getAllUsers(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "", required = false) String username) {
        try {
            PageInfo<User> response = userService.allUsers(page, size, username);
            return Response.<PageInfo<User>>ok().setPayload(response).setMessage("Successfully retrieved all users!!");
        } catch (Exception ex) {
            return Response.<PageInfo<User>>exception().setMessage("Failed to retrieved all users!!!");
        }
    }


    @PostMapping("/new-user")
    public Response<User> createUser(@Valid @RequestBody UserRequest request) {
        try {
            int userID = userService.createNewUser(request);
            if (userID > 0) {
                User response = new User().setUsername(request.getUsername())
                        .setAddress(request.getAddress())
                        .setGender(request.getGender()).setUserID(userID);
                return Response.<User>createSuccess().setPayload(response).setMessage("Create User Successfully").setSuccess(true);
            } else {
                return Response.<User>badRequest().setMessage("Bad Request ! Failed to create user");
            }
        } catch (Exception ex) {
            return Response.<User>exception().setMessage("Exception occurs! Failed to create a new user ").setSuccess(false);
        }
    }

    @GetMapping("/updateUser/{id}")
    Response<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        try {
            int result = userService.updateUser(user, id);
            if (result > 0) {
                return Response.<User>updateSuccess().setPayload(user).setMessage("User are Updated!!");
            } else {
                return Response.<User>ok().setMessage("User not found by id : " + id);
            }
        } catch (Exception exception) {
            return Response.<User>exception().setMessage("Update Value is Failed!!").setSuccess(false);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    Response<User> deleteUser(@PathVariable("id") int id) {
        try {
            int result = userService.deleteUser(id);
            if (result > 0) {
                return Response.<User>deleteSuccess().setMessage("One Row is deleted!!");
            } else {
                return Response.<User>ok().setMessage("Not Found user by id : " + id);
            }
        } catch (Exception e) {
            return Response.<User>exception().setMessage("Remove value Fail.").setSuccess(false);
        }

    }
}
