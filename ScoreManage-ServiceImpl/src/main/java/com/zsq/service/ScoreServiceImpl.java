package com.zsq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.common.StudentScore;
import com.zsq.entity.Score;
import com.zsq.entity.ScoreExample;
import com.zsq.mapper.ScoreMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public RetrunInfo updateScore(StudentScore studentScore) {
        ScoreExample scoreExample = new ScoreExample();
        ScoreExample.Criteria criteria = scoreExample.createCriteria();
        criteria.andStuIdEqualTo(studentScore.getStuId()).andCouIdEqualTo(studentScore.getCouId());
        List<Score> scores = scoreMapper.selectByExample(scoreExample);
        if(scores.size()>0){
            Score score = scores.get(0);
            score.setScore(studentScore.getScore());
            scoreMapper.updateByPrimaryKey(score);
            return new RetrunInfo(200,"","");
        }
        return new RetrunInfo(201,"","");
    }

    @Override
    public StudentScore getScore(String sid, String cid) {
        StudentScore studentScore = scoreMapper.queryBySCId(sid, cid);

        return studentScore;
    }

    @Override
    public LayUIData getScores(int page, int rows, String name) {
        List<StudentScore> scores=new ArrayList<>();
      /*  Long llen = jedisClient.llen("" + page);
        String totalRow = jedisClient.get("totalRow");
        String totalPage = jedisClient.get("totalPage");
        List<String> lrange = jedisClient.lrange("" + page, 0, llen);
        if(lrange.size()>0&&StringUtils.isNotBlank(totalRow)&&StringUtils.isNotBlank(totalPage)){
            long totalRows = Long.valueOf(totalRow);
            int totalPages = Integer.valueOf(totalPage);
          for (String s :lrange){
              StudentScore studentScore = JsonUtils.jsonToPojo(s, StudentScore.class);
              scores.add(studentScore);
          }
            LayUIData result = new LayUIData();
            result.setRows(scores);
            result.setTotal(totalRows);
            result.setPaages(totalPages);
            return result;
        }*/
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(name)){
            scores = scoreMapper.selectByLike(name);

        }else{
            scores = scoreMapper.queryStudentScore();
        }
        LayUIData result = new LayUIData();
        result.setRows(scores);
        PageInfo<StudentScore> pageInfo = new PageInfo<>(scores);
        //取总记录数
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        result.setTotal(total);
        result.setPaages(pages);
     /*   //加载数据到redis
        String s[]=null;
         for(int i=1;i<=pages;i++){
             PageHelper.startPage(i,rows);
             scores = scoreMapper.queryStudentScore();
             for (int j=0;j<scores.size();j++ ){
                 String sco = JsonUtils.objectToJson(scores.get(j));
                 s = new String[scores.size()];
                 s[j]=sco;
             }
             jedisClient.lpush(""+i,s);
             jedisClient.expire(""+i,3600);
         }
         jedisClient.set("totalRow",""+total);
        jedisClient.set("totalPage",""+pages);
        jedisClient.expire("totalRow",3600);
        jedisClient.expire("TotalPage",3600);*/
        return result;
    }
    @Override
    public RetrunInfo deleteScoAll(Integer[] a) {
       /* String totalPage = jedisClient.get("totalPage");
        int totalp = Integer.valueOf(totalPage);
        Long llen = jedisClient.llen("" + page);
        String totalRow = jedisClient.get("totalRow");
          int totalrow = Integer.valueOf(totalRow);
        List<String> lrange = jedisClient.lrange("" + page, 0, llen);
         List<StudentScore> studentScores = new ArrayList<>();
         for (String ss : lrange){
             StudentScore score = JsonUtils.jsonToPojo(ss, StudentScore.class);
             studentScores.add(score);
         }*/
        for (Integer i : a) {
           /* StudentScore score = scoreMapper.selectStudentScoreById(i);
            studentScores.remove(score);
            String lpop = jedisClient.lpop("" + totalp);
            if(StringUtils.isNotBlank(lpop)){
                StudentScore score1 = JsonUtils.jsonToPojo(lpop, StudentScore.class);
                studentScores.add(score1);
            }else {
                int leftpage = totalp-1;
                String lpop1 = jedisClient.lpop("" + leftpage);
                StudentScore score2 = JsonUtils.jsonToPojo(lpop1, StudentScore.class);
                studentScores.add(score2);
            }
            totalrow--;*/
          scoreMapper.deleteByPrimaryKey(i);
        }

      /*  int tol = (totalrow+5)/5;
        String s = JsonUtils.objectToJson(studentScores);
        jedisClient.lpush(""+page,s);
        jedisClient.expire(""+page,3600);
        jedisClient.set("totalRow",""+totalrow);
        jedisClient.expire("totalRow",3600);
        jedisClient.set("totalPage",""+tol);
        jedisClient.expire("totalPage",3600);*/
        return new RetrunInfo(200,"","");
    }
    @Override
    public RetrunInfo addScore(Score score) {
        int insert = scoreMapper.insert(score);
        if(insert>0){
            return new RetrunInfo(200,"","");
        }
        return new RetrunInfo(201,"","");
    }
}
