package com.zsq.controller;

import com.zsq.entity.TeacherInfo;
import com.zsq.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request, Model model){
        TeacherInfo login = teacherService.login(username, password);
        if(login!=null&&login.getTeaSex()!=-1&&login.getStatus()!=0){
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            return "redirect:/scoreManage/admin_index";
        }
        else if(login!=null&&login.getTeaSex()==-1&&login.getStatus()!=0) {
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            return "redirect:/scoreManage/aadmin_index";
        }
        model.addAttribute("msg","用户名或密码错误");
        return "index";
    }
    @RequestMapping("/")
    public String index(){
        //哈哈哈哈
        String s = "index";
        return s;
    }

}
