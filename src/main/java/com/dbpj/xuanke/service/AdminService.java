package com.dbpj.xuanke.service;

import com.dbpj.xuanke.domain.Admin;

public interface AdminService {
    Admin checkAdmin(String name, String password);

}
