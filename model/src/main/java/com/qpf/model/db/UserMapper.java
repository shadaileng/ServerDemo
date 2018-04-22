package com.qpf.model.db;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @SelectProvider(type = UserProvider.class, method = "selectByFullSql")
    public List<Map<String, Object>> selectUserByFullSql(@Param("fullSQL") String fullSQL);
    @SelectProvider(type = UserProvider.class, method = "selectUserById")
    public User selectUserById(@Param("user") User user);
    @SelectProvider(type = UserProvider.class, method = "selectUserByBean")
    public List<User> selectUserByBean(@Param("user") User user);
    @SelectProvider(type = UserProvider.class, method = "selectUserByConditions")
    public List<User> selectUserByConditions(@Param("paramWhere") String paramWhere, @Param("paramOrder") String ... paramOrder);
    @UpdateProvider(type = UserProvider.class, method = "updateUserById")
    public int updateUserById(@Param("user") User user);
    @DeleteProvider(type = UserProvider.class, method = "deleteUserById")
    public int deleteUserById(@Param("user") User user);
    @InsertProvider(type = UserProvider.class, method = "insertUser")
    public int insertUser(@Param("user") User user);
}
