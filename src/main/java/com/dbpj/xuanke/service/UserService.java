package com.dbpj.xuanke.service;

import com.dbpj.xuanke.domain.User;

public interface UserService {
    User checkUser(String username, String password);

}
