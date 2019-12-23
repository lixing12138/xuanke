package com.dbpj.xuanke.service;

import com.dbpj.xuanke.domain.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    // 获取单个课程信息
    Course checkCourse(String c_id);
    // 通过excel文件上传课程信息
    int addCourses(MultipartFile file) throws Exception;
    // 获取所有课程
    List<Course> getAllCourses();
    // 删除某个学生
    void deleteCourse(String c_id);
    // 添加某个课程
    void addCourse(Course course);
    // 更新课程
    void updateCourse(String c_id, String c_title, String c_dept_name, Integer c_credit);

}
