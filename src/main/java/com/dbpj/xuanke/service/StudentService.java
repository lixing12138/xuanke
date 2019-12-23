package com.dbpj.xuanke.service;

import com.dbpj.xuanke.domain.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    // 获取单个学生
    Student checkStudent(String name);
    // 通过excel文件上传学生信息
    int addStudents(MultipartFile file) throws Exception;
    // 获取所有学生
    List<Student> getAllStudents();
    // 删除某个学生
    void deleteStudent(String s_id);
    // 添加某个学生
    void addStudent(Student student);
    // 更新学生信息
    void updateStudent(String sid, String sname, String sdeptname, String sclass);
}
