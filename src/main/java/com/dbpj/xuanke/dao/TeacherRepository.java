package com.dbpj.xuanke.dao;

import com.dbpj.xuanke.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByTid(String t_id);
    void deleteByTid(String t_id);

    @Modifying
    @Query(value = "update teacher set t_name=?2, t_dept_name=?3 where t_id=?1", nativeQuery = true)
    @Transactional
    void updateTeacher(String t_id, String t_name, String t_dept_name);
}
