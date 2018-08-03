package com.zsq.controller;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.Course;
import com.zsq.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("scoreManage")
public class CourseController {
    @Autowired
    private CourseService courseService;
   @RequestMapping("/course_list")
    public String addStudent(Model model, @RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum, String name){
       LayUIData course = courseService.getCourse(pageNum, 5, name);
       course.setCurrentPage(pageNum);
       model.addAttribute("course",course);
       model.addAttribute("name",name);
       return "course_list";
    }
    @RequestMapping("/addCourseInfo")
    @ResponseBody
    public RetrunInfo addStudentInfo(Course course) {
        RetrunInfo retrunInfo = courseService.addCourse(course);
        return retrunInfo;
    }
    @RequestMapping("/cou_edit")
    public String showStuEdit(Model model,String id){
        Course course = courseService.getCourse(id);
        model.addAttribute("cou",course);
        return "course_edit";
    }
    @RequestMapping("/editCourseInfo")
    @ResponseBody
    public RetrunInfo editStudentInfo(Course course) {
        RetrunInfo retrunInfo = courseService.updateCourse(course);
        return retrunInfo;

    }
    @RequestMapping("/deleteCouAll")
    @ResponseBody
    public RetrunInfo deleteAll(@RequestParam("adds[]") String adds[]) {
        RetrunInfo retrunInfo = courseService.deleteCouAll(adds);
        return retrunInfo;
    }
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }
}
