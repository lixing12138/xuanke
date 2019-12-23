package com.dbpj.xuanke.service.impl;

import com.dbpj.xuanke.dao.AdminRepository;
import com.dbpj.xuanke.domain.Admin;
import com.dbpj.xuanke.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin checkAdmin(String name, String password) {
        Admin admin = adminRepository.findByAidAndApassword(name, password);
        return admin;
    }
}
