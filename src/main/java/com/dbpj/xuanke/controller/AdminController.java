package com.dbpj.xuanke.controller;

import com.dbpj.xuanke.domain.Admin;
import com.dbpj.xuanke.domain.Course;
import com.dbpj.xuanke.domain.Student;
import com.dbpj.xuanke.domain.Teacher;
import com.dbpj.xuanke.response.AdminResponse;
import com.dbpj.xuanke.service.CourseService;
import com.dbpj.xuanke.service.StudentService;
import com.dbpj.xuanke.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/uploadStudent")

    public @ResponseBody
    AdminResponse uploadStudent(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return new AdminResponse(false,"上传失败，请选择文件");
        }
        int result = -1;
        try{
            result = studentService.addStudents(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result > 0){
            return new AdminResponse(true,"学生数据导入成功！");
        }else if(result == 0){
            return new AdminResponse(false,"文件中有学生学号重复或数据库中已存在");
        }else{
            return new AdminResponse(false,"数据导入失败！");
        }
    }

    @PostMapping("/student")
    public @ResponseBody
    AdminResponse addStudent(@RequestParam String s_id, @RequestParam String s_name,
                     @RequestParam String s_dept_name, @RequestParam String s_class){
        Student student = new Student(s_id, s_name, s_dept_name, s_class);
        if (studentService.checkStudent(s_id) == null){
            studentService.addStudent(student);
            return new AdminResponse(true,"添加成功");
        }else {
            return new AdminResponse(false,"学号已存在");
        }
    }

    @GetMapping("/student")
    public @ResponseBody
    Student getStudent(@RequestParam String s_id){
        return studentService.checkStudent(s_id);
    }

    @PutMapping("/student")
    public @ResponseBody
    AdminResponse updateStudent(@RequestParam String s_id, @RequestParam String s_name,
                                @RequestParam String s_dept_name, @RequestParam String s_class){
        studentService.updateStudent(s_id, s_name, s_dept_name, s_class);
        return  new AdminResponse(true,"学生信息更新成功");
    }

    @GetMapping("/students")
    public @ResponseBody
    List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList = studentService.getAllStudents();
        return studentList;
    }

    @DeleteMapping("/student")
    public  @ResponseBody
    AdminResponse deleteStudent(@RequestParam String id){
        Student student = studentService.checkStudent(id);
        if(student != null){
            studentService.deleteStudent(id);
            return new AdminResponse(true,"删除成功");
        }else {
            return  new AdminResponse(false,"学生编号不存在");
        }

    }


    @PostMapping("/uploadCourse")
    public @ResponseBody
    AdminResponse uploadCourse(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return new AdminResponse(false,"上传失败，请选择文件");
        }
        int result = -1;
        try{
            result = courseService.addCourses(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result > 0){
            return new AdminResponse(true,"课程数据导入成功！");
        }else if(result == 0){
            return new AdminResponse(false,"文件中课程编号重复或数据库中已存在");
        }else{
            return new AdminResponse(false,"课程数据导入失败！");
        }
    }

    @PostMapping("/course")
    public @ResponseBody
    AdminResponse addCourse(@RequestParam String c_id, @RequestParam String c_title,
                             @RequestParam String c_dept_name, @RequestParam Integer c_credit){
        Course course = new Course(c_id, c_title, c_dept_name, c_credit);
        if (courseService.checkCourse(c_id) == null){
            courseService.addCourse(course);
            return new AdminResponse(true,"添加成功");
        }else {
            return new AdminResponse(false,"课程编号已存在");
        }
    }

    @GetMapping("/course")
    public @ResponseBody
    Course getCourse(@RequestParam String c_id){
        return courseService.checkCourse(c_id);
    }

    @GetMapping("/courses")
    public @ResponseBody
    List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @DeleteMapping("/course")
    public  @ResponseBody
    AdminResponse deleteCourse(@RequestParam String c_id){
        Course course = courseService.checkCourse(c_id);
        if(course != null){
            courseService.deleteCourse(c_id);
            return new AdminResponse(true,"删除成功");
        }else {
            return  new AdminResponse(false,"课程编号不存在");
        }
    }

    @PutMapping("/course")
    public @ResponseBody
    AdminResponse updateCourse(@RequestParam String c_id, @RequestParam String c_title,
                                @RequestParam String c_dept_name, @RequestParam Integer c_credit){
        courseService.updateCourse(c_id, c_title, c_dept_name, c_credit);
        return  new AdminResponse(true,"课程信息更新成功");
    }


    @PostMapping("/uploadTeacher")
    public @ResponseBody
    AdminResponse uploadTeacher(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return new AdminResponse(false,"上传失败，请选择文件");
        }
        int result = -1;
        try{
            result = teacherService.addTeachers(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result > 0){
            return new AdminResponse(true,"课程数据导入成功！");
        }else if(result == 0){
            return new AdminResponse(false,"文件中课程编号重复或数据库中已存在");
        }else{
            return new AdminResponse(false,"课程数据导入失败！");
        }
    }

    @PostMapping("/teacher")
    public @ResponseBody
    AdminResponse addTeacher(@RequestParam String t_id, @RequestParam String t_name,
                            @RequestParam String t_dept_name){
        Teacher teacher = new Teacher(t_id, t_name, t_dept_name);
        if (teacherService.checkTeacher(t_id) == null){
            teacherService.addTeacher(teacher);
            return new AdminResponse(true,"添加成功");
        }else {
            return new AdminResponse(false,"教师编号已存在");
        }
    }

    @GetMapping("/teacher")
    public @ResponseBody
    Teacher getTeacher(@RequestParam String t_id){
        return teacherService.checkTeacher(t_id);
    }

    @GetMapping("/teachers")
    public @ResponseBody
    List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @DeleteMapping("/teacher")
    public  @ResponseBody
    AdminResponse deleteTeacher(@RequestParam String tid){
        Teacher teacher = teacherService.checkTeacher(tid);
        if(teacher != null){
            teacherService.deleteTeacher(tid);
            return new AdminResponse(true,"删除成功");
        }else {
            return  new AdminResponse(false,"教师编号不存在");
        }
    }

    @PutMapping("/teacher")
    public @ResponseBody
    AdminResponse updateTeacher(@RequestParam String t_id, @RequestParam String t_name,
                                @RequestParam String t_dept_name){
        teacherService.updateTeacher(t_id, t_name, t_dept_name);
        return  new AdminResponse(true,"教师信息更新成功");
    }

}
