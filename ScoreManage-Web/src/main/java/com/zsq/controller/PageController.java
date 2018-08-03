package com.zsq.controller;


import com.zsq.common.RetrunInfo;
import com.zsq.entity.Course;
import com.zsq.entity.Student;
import com.zsq.service.CourseService;
import com.zsq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("scoreManage")
public class PageController {


@Autowired
private StudentService studentService;
@Autowired
private CourseService courseService;
 @RequestMapping("/addTeacher")
    public String addTeacher(){
        return "teacher_add";
 }
    @RequestMapping("/addStudent")
    public String addStudent(){
        return "student_add";
    }
    @RequestMapping("/addCourse")
    public String addCourse(){
        return "course_add";
    }
    @RequestMapping("/addScore")
    public String addScore(Model model){
        List<Course> all = courseService.getAll();
        model.addAttribute("cou",all);
        return "score_add";
    }
    @RequestMapping("/querySid")
    @ResponseBody
    public RetrunInfo querySid(String name) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode(name, "UTF-8");
        List<Student> studentByName = studentService.getStudentByName(decode);
        RetrunInfo retrunInfo = new RetrunInfo();
        List<String> sid = new ArrayList<>();
        if(studentByName.size()>0){
            for (int i = 0; i <studentByName.size() ; i++) {
                sid.add(studentByName.get(i).getStuId());
            }
            retrunInfo.setCode(200);
            retrunInfo.setSid(sid);
            return  retrunInfo;
        }
        retrunInfo.setCode(201);
        return retrunInfo;
    }
  @RequestMapping("/welcome")
    public String showWe(){
        return "welcome";
  }

    @RequestMapping("/admin_index")
    public String showAdin(){
        return "admin_index";
    }

    @RequestMapping("/aadmin_index")
    public String showAadin(){
        return "aadmin_index";
    }


}

