package com.zsq.controller;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.TeacherInfo;
import com.zsq.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("scoreManage")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/teacher_list")
    public String teacherList(Model model, @RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum,String teaName) {
        LayUIData teacher = teacherService.getTeacher(pageNum, 8,teaName);
        teacher.setCurrentPage(pageNum);
        model.addAttribute("teacherInfo", teacher);
        model.addAttribute("teaName",teaName);
        return "teacher_list";
    }

    @RequestMapping("/teacher_edit")
    public String teacherEdit(@RequestParam Integer id, Model model) {
        TeacherInfo teacher = teacherService.getTeacher(id);
        model.addAttribute("teacherInfo", teacher);
        return "teacher_edit";
    }

    @RequestMapping("/teacherEdit")
    @ResponseBody
    public RetrunInfo toTeacherList(TeacherInfo teacherInfo) {
        RetrunInfo retrunInfo = teacherService.updateTeacher(teacherInfo);
        return retrunInfo;

    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public RetrunInfo deleteAll(@RequestParam("adds[]") Integer adds[]) {
        RetrunInfo retrunInfo = teacherService.deleteAll(adds);
        return retrunInfo;
    }

    @RequestMapping("/addTeacherInfo")
    @ResponseBody
    public RetrunInfo addTeacherInfo(TeacherInfo teacherInfo) {
        RetrunInfo retrunInfo = teacherService.addTeacherInfo(teacherInfo);
        return retrunInfo;
    }

}
