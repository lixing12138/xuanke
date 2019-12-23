package com.dbpj.xuanke.service.impl;

import com.dbpj.xuanke.dao.CourseRepository;
import com.dbpj.xuanke.domain.Course;
import com.dbpj.xuanke.service.CourseService;
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
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course checkCourse(String c_id) {
        return courseRepository.findByCid(c_id);
    }

    @Override
    public int addCourses(MultipartFile file) throws Exception {
        int result = -1;
        List<Course> courseList = new ArrayList<>();
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

                Course course = new Course();

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

                String title = row.getCell(1).getStringCellValue();
                String s_dept_name = row.getCell(2).getStringCellValue();
                Integer c_credit = Integer.parseInt(row.getCell(3).getStringCellValue());

                course.setCid(sid);
                course.setCtitle(title);
                course.setCdeptname(s_dept_name);
                course.setCcredit(c_credit);
                courseList.add(course);

            }

            for(Course courseInfo:courseList){

                /**
                 * 判断数据库表中是否存在用户记录，若存在，停止更新，不存在，则保存记录
                 */
                String sid = courseInfo.getCid();
                Course student = courseRepository.findByCid(sid);
                if(student == null){
                    result ++;
                }else{
                    result = 0;
                    break;
                }
            }
            if (result+1 == courseList.size()){
                for(Course courseInfo:courseList){
                    // 课程信息
                    Course s = courseRepository.save(courseInfo);

                }
            }
        }

        return result;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteCourse(String c_id) {
        courseRepository.deleteByCid(c_id);
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(String c_id, String c_title, String c_dept_name, Integer c_credit) {
        courseRepository.updateCourse(c_id, c_title, c_dept_name, c_credit);
    }
}
