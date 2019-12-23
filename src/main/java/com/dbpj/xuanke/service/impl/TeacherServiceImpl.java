package com.dbpj.xuanke.service.impl;

import com.dbpj.xuanke.dao.TeacherRepository;
import com.dbpj.xuanke.dao.UserRepository;
import com.dbpj.xuanke.domain.Teacher;
import com.dbpj.xuanke.domain.User;
import com.dbpj.xuanke.service.TeacherService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Teacher checkTeacher(String name) {
        Teacher teacher = teacherRepository.findByTid(name);
        return teacher;
    }

    @Override
    public int addTeachers(MultipartFile file) throws Exception {
        int result = -1;
        List<Teacher> teacherList = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

        InputStream ins = file.getInputStream();

        Workbook wb = null;

        if(suffix.equals("xlsx")){

            wb = new XSSFWorkbook(ins);

        }else{
            wb = new HSSFWorkbook(ins);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(null != sheet){

            for(int line = 1; line <= sheet.getLastRowNum();line++){

                Teacher teacher = new Teacher();

                Row row = sheet.getRow(line);

                if(null == row){
                    continue;
                }
                /**
                 * 判断单元格类型是否为文本类型
                 */
                if(1 != row.getCell(0).getCellType()){
                    throw new Exception("单元格类型不是文本类型！");
                }

                /**
                 * 获取第一个单元格的内容
                 * 学号
                 */
                String sid = row.getCell(0).getStringCellValue();

                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                /**
                 * 获取第二个单元格的内容
                 */

                String username = row.getCell(1).getStringCellValue();
                String s_dept_name = row.getCell(2).getStringCellValue();

                teacher.setTid(sid);
                teacher.setTname(username);
                teacher.setTdeptname(s_dept_name);
                teacherList.add(teacher);

            }

            for(Teacher studentInfo:teacherList){

                /**
                 * 判断数据库表中是否存在用户记录，若存在，停止更新，不存在，则保存记录
                 */
                String sid = studentInfo.getTid();
                Teacher teacher = teacherRepository.findByTid(sid);
                if(teacher == null){
                    result ++;
                }else{
                    result = 0;
                    break;
                }
            }
            if (result+1 == teacherList.size()){
                for(Teacher studentInfo:teacherList){
                    // 学生信息
                    teacherRepository.save(studentInfo);
                    User userTmp = new User();
                    userTmp.setUid(studentInfo.getTid());
                    userTmp.setUpassword(studentInfo.getTid());
                    // 更新用户表
                    userRepository.save(userTmp);
                }
            }
        }
        return result;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteTeacher(String t_id) {
        teacherRepository.deleteByTid(t_id);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        User userTmp = new User();
        userTmp.setUid(teacher.getTid());
        userTmp.setUpassword(teacher.getTid());
        userRepository.save(userTmp);
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(String t_id, String t_name, String t_dept_name) {
        teacherRepository.updateTeacher(t_id, t_name, t_dept_name);
    }
}
