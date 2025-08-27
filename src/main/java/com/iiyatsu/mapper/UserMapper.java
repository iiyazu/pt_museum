package com.iiyatsu.mapper;

import com.iiyatsu.pojo.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select id, passwd, name from user u where id = #{id} and passwd = #{passwd}")
    User selectByIdAndPasswd(User user);

    @Select("Select id from user u where id = #{id}")
    User selectById(User user);

    @Insert("INSERT INTO user (id, name, passwd, admin, user_static)" +
            "VALUES (#{id}, #{name}, #{passwd}, #{admin}, #{user_static});")
    void insertNewUser(User user);
}
