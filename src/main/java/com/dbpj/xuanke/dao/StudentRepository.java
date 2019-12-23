package com.dbpj.xuanke.dao;

import com.dbpj.xuanke.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findBySid(String s_id);
    void deleteBySid(String s_id);
    @Modifying
    @Query(value = "update Student set s_name=?2, s_dept_name=?3, s_class=?4 where s_id=?1", nativeQuery=true)
    void updateStudent(String sid, String sname, String sdeptname, String sclass);
}
