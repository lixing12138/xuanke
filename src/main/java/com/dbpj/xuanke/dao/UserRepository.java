package com.dbpj.xuanke.dao;

import com.dbpj.xuanke.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUidAndUpassword(String u_id, String u_password);
}
