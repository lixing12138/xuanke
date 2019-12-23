package com.dbpj.xuanke.service;

import com.dbpj.xuanke.domain.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherService {
    // 获取单个教师
    Teacher checkTeacher(String name);
    // 通过excel文件上传学生信息
    int addTeachers(MultipartFile file) throws Exception;
    // 获取所有教师
    List<Teacher> getAllTeachers();
    // 删除某个教师
    void deleteTeacher(String t_id);
    // 添加某个教师
    void addTeacher(Teacher teacher);
    // 修改教师信息
    void updateTeacher(String t_id, String t_name, String t_dept_name);
}
