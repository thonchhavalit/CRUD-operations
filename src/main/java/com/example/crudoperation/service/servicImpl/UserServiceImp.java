package com.example.crudoperation.service.servicImpl;

import com.example.crudoperation.model.User;
import com.example.crudoperation.model.request.UserRequest;
import com.example.crudoperation.repository.UserRepository;
import com.example.crudoperation.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    UserRepository userRepository;
    UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public PageInfo<User> allUsers(int page, int size, String filterName) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(userRepository.allUsers(filterName));
    }

    @Override
    public int createNewUser(@Param("user") UserRequest user) {
        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(User user, int id) {
        return userRepository.updateUser(user, id);
    }

    @Override
    public int deleteUser(int id) {
        return userRepository.removeUser(id);
    }
}
