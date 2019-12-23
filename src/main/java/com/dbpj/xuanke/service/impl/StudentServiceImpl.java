package com.dbpj.xuanke.service.impl;

import com.dbpj.xuanke.dao.StudentRepository;
import com.dbpj.xuanke.dao.UserRepository;
import com.dbpj.xuanke.domain.Student;
import com.dbpj.xuanke.domain.User;
import com.dbpj.xuanke.service.StudentService;
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
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Student checkStudent(String name) {
        return studentRepository.findBySid(name);
    }

    @Override
    public int addStudents(MultipartFile file) throws Exception {
        int result = -1;
        List<Student> studentList = new ArrayList<>();
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

                Student student = new Student();

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
                String s_class = row.getCell(3).getStringCellValue();

                student.setSid(sid);
                student.setSname(username);
                student.setSdeptname(s_dept_name);
                student.setSclass(s_class);

                studentList.add(student);

            }

            for(Student studentInfo:studentList){

                /**
                 * 判断数据库表中是否存在用户记录，若存在，停止更新，不存在，则保存记录
                 */
                String sid = studentInfo.getSid();
                Student student = studentRepository.findBySid(sid);
                if(student == null){
                    result ++;
                }else{
                    result = 0;
                    break;
                }
            }
            if (result+1 == studentList.size()){
                for(Student studentInfo:studentList){
                    // 学生信息
                    studentRepository.save(studentInfo);
                    User userTmp = new User();
                    userTmp.setUid(studentInfo.getSid());
                    userTmp.setUpassword(studentInfo.getSid());
                    // 更新用户表
                    userRepository.save(userTmp);
                }
            }
        }

        return result;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList = studentRepository.findAll();
        return studentList;
    }

    @Override
    @Transactional
    public void deleteStudent(String s_id) {
        studentRepository.deleteBySid(s_id);
    }

    @Override
    public void addStudent(Student student) {
        User userTmp = new User();
        userTmp.setUid(student.getSid());
        userTmp.setUpassword(student.getSid());
        userRepository.save(userTmp);
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void updateStudent(String sid, String sname, String sdeptname, String sclass) {
        studentRepository.updateStudent(sid, sname, sdeptname, sclass);
    }

}
