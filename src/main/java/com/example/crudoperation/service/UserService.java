package com.example.crudoperation.service;

import com.example.crudoperation.model.User;
import com.example.crudoperation.model.request.UserRequest;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    PageInfo<User> allUsers(int page, int size, String filterName);

    int createNewUser(UserRequest user);
    int updateUser(User user, int id);
    int deleteUser(int id);


}
