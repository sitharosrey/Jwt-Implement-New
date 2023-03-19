package com.example.demo_jwt_1.repository;

import com.example.demo_jwt_1.model.UserApp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("""
            SELECT rt.user_role
            FROM role_tb rt INNER JOIN user_role_tb urt on rt.id = urt.id
            WHERE user_id = #{userId}
            """)
    List<String> getAllRolesByUserId(Integer userId);

    @Select("SELECT * FROM user_tb WHERE user_email = #{email}")
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "user_email"),
            @Result(property = "password", column = "user_password"),
            @Result(property = "roles", column = "user_id", many = @Many(
                    select = "getAllRolesByUserId"
            ))
    })
    UserApp getUserByEmail(String email);
}
