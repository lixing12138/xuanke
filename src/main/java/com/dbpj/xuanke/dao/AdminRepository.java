package com.dbpj.xuanke.dao;

import com.dbpj.xuanke.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAidAndApassword(String a_id, String u_password);
}
