package com.example.crudoperation.repository;


import com.example.crudoperation.model.User;
import com.example.crudoperation.model.request.UserRequest;
import com.example.crudoperation.repository.Provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserRepository {
    @Result(column = "id", property = "UserID")
    @SelectProvider(type = UserProvider.class, method = "getAllUsers")
    List<User> allUsers(String filterName);

    @Insert("insert into users_tb (username, gender, address) values (#{user.username}, #{user.gender}, #{user.address})")
    int createNewUser(@Param("user") UserRequest user);

    @Update("UPDATE users_tb SET username=#{user.username},gender=#{user.gender},address=#{user.address} WHERE id=#{id}")
    int updateUser(@Param("user") User user, @Param("id")int id);

    @Delete("DELETE FROM users_tb WHERE id=#{id}")
    int removeUser(@Param("id") int id);
}
