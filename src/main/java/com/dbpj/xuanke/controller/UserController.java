package com.dbpj.xuanke.controller;

import com.dbpj.xuanke.domain.Admin;
import com.dbpj.xuanke.domain.Student;
import com.dbpj.xuanke.domain.Teacher;
import com.dbpj.xuanke.domain.User;
import com.dbpj.xuanke.response.UserResponse;
import com.dbpj.xuanke.service.AdminService;
import com.dbpj.xuanke.service.StudentService;
import com.dbpj.xuanke.service.TeacherService;
import com.dbpj.xuanke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")

    public @ResponseBody
    UserResponse login(@RequestParam String username, @RequestParam String password,
                       HttpSession session){
        if (username.startsWith("S")){
            // 学生
            Student student = studentService.checkStudent(username);
            if (student != null){
                User user = userService.checkUser(username, password);
                if(user != null){
                    user.setUpassword(null);
                    session.setAttribute("user",user.getUid());
                    session.setAttribute("type","student");
                    return new UserResponse(true, "student","登录成功");
                }else {
                    return new UserResponse(false, "student","密码错误");
                }
            } else {
                return new UserResponse(false, "student","该学生账号不存在");
            }
        }else if (username.startsWith("T")){
            // 教师
            Teacher teacher = teacherService.checkTeacher(username);
            if (teacher != null){
                User user = userService.checkUser(username, password);
                if(user != null){
                    session.setAttribute("user",user.getUid());
                    session.setAttribute("type","teacher");
                    return new UserResponse(true, "teacher","登录成功");
                }else {
                    return new UserResponse(false, "teacher","密码错误");
                }
            } else {
                return new UserResponse(false,"teacher","该教师账号不存在");
            }
        }else if(username.equals("root")){
            // 管理员
            Admin admin = adminService.checkAdmin(username, password);
            if(admin != null){
                admin.setApassword(null);
                session.setAttribute("user", admin.getAid());
                session.setAttribute("type","root");
                return new UserResponse(true,"admin","登录成功");
            } else {
                return new UserResponse(false,"admin","密码输入错误");
            }
        }else {
            // 其他
            return new UserResponse(false,"none","账号不合法");
        }
    }

    @GetMapping("/logout")
    public  @ResponseBody UserResponse logout(HttpSession session){
        System.out.println(session.getAttribute("user"));
        if (session.getAttribute(("user")) != null){
            session.removeAttribute("user");
            session.removeAttribute("type");
            return new UserResponse(true,"登出成功");
        }
        return new UserResponse(false,"未登录");
    }

    @GetMapping("/user")
    public  @ResponseBody UserResponse user(HttpSession session){
        if (session.getAttribute(("type")) == "student"){
            Student student = studentService.checkStudent(session.getAttribute("user").toString());
            return new UserResponse(true,"student","", student.getSname());
        } else if (session.getAttribute(("type")) == "teacher"){
            Teacher teacher = teacherService.checkTeacher(session.getAttribute("user").toString());
            return new UserResponse(true,"teacher","", teacher.getTname());
        } else if (session.getAttribute(("type")) == "root"){
            return new UserResponse(true,"root","", "root");
        }
        return new UserResponse(false,"未登录");
    }
}
