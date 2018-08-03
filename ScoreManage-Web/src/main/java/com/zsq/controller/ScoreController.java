package com.zsq.controller;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.common.StudentScore;
import com.zsq.entity.Score;
import com.zsq.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("scoreManage")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @RequestMapping("/score_list")
    public String addStudent(Model model, @RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum, String name){
        LayUIData score = scoreService.getScores(pageNum, 5, name);
        score.setCurrentPage(pageNum);
        model.addAttribute("score",score);
        model.addAttribute("name",name);
        return "score_list";
    }

    @RequestMapping("/sco_edit")
    public String editScore(Model model,String sid,String cid){
        StudentScore score = scoreService.getScore(sid, cid);
        model.addAttribute("score",score);
        return "score_edit";
    }
    @RequestMapping("/editScoreInfo ")
    @ResponseBody
    public RetrunInfo editScoreInfo(StudentScore studentScore){
        RetrunInfo retrunInfo = scoreService.updateScore(studentScore);
        return retrunInfo;
    }
    @RequestMapping("/deleteScoreAll")
    @ResponseBody
    public RetrunInfo deleteAll(@RequestParam("adds[]") Integer adds[],Integer page) {
        RetrunInfo retrunInfo = scoreService.deleteScoAll(adds);
        return retrunInfo;
    }
    @RequestMapping("/addScoreInfo")
    @ResponseBody
    public RetrunInfo addScore(StudentScore studentScore){
        Score score = new Score();
        if(StringUtils.isBlank(studentScore.getCouId())&&StringUtils.isBlank(studentScore.getStuId())){
            return new RetrunInfo(201,"","");
        }
        score.setStuId(studentScore.getStuId());
        score.setCouId(studentScore.getCouId());
        score.setScore(studentScore.getScore());
        RetrunInfo retrunInfo = scoreService.addScore(score);


        return retrunInfo;
    }

}
