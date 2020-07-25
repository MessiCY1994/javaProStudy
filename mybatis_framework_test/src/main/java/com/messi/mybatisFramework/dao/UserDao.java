package com.messi.mybatisFramework.dao;


import com.messi.mybatisFramework.po.User;

public interface UserDao {

	User queryUserById(Integer id);
}
