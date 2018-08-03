package com.zsq.controller;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.Student;
import com.zsq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("scoreManage")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/onStudent_list")
    public String studentList(Model model, @RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum, String name){
        LayUIData onStudent = studentService.getOnStudent(pageNum, 5, name);
        onStudent.setCurrentPage(pageNum);
        model.addAttribute("student",onStudent);
        model.addAttribute("name",name);
        return "student_list";
    }
    @RequestMapping("/addStudentInfo")
    @ResponseBody
    public RetrunInfo addStudentInfo(Student student) {
        RetrunInfo retrunInfo = studentService.addStudeng(student);
        return retrunInfo;
    }
    @RequestMapping("/stu_edit")
    public String showStuEdit(Model model,String id){
        Student student = studentService.getStudent(id);
        model.addAttribute("student",student);
        return "student_edit";
    }
    @RequestMapping("/editStudentInfo")
    @ResponseBody
    public RetrunInfo editStudentInfo(Student student) {
        RetrunInfo retrunInfo = studentService.updateStudent(student);
        return retrunInfo;

    }
    @RequestMapping("/deleteStuAll")
    @ResponseBody
    public RetrunInfo deleteAll(@RequestParam("adds[]") String adds[]) {
        RetrunInfo retrunInfo = studentService.deleteStuAll(adds);
        return retrunInfo;
    }
}
