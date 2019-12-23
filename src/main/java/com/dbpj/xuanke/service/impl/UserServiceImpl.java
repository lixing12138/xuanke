package com.dbpj.xuanke.service.impl;

import com.dbpj.xuanke.dao.UserRepository;
import com.dbpj.xuanke.domain.User;
import com.dbpj.xuanke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user =userRepository.findByUidAndUpassword(username, password);
        return user;
    }
}
