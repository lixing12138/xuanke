package com.dbpj.xuanke.dao;

import com.dbpj.xuanke.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCid(String c_id);
    void deleteByCid(String c_id);
    @Modifying
    @Query(value = "update Course set c_title=?2, c_dept_name=?3, c_credit=?4 where t_id=?1", nativeQuery=true)
    void updateCourse(String cid, String ctitle, String cdeptname, Integer ccredit);
}
