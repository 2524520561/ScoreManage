package com.zsq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.Course;
import com.zsq.entity.CourseExample;
import com.zsq.entity.ScoreExample;
import com.zsq.mapper.CourseMapper;
import com.zsq.mapper.ScoreMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public RetrunInfo updateCourse(Course course) {
        try {
            courseMapper.updateByPrimaryKey(course);
        } catch (Exception e) {
            return  new RetrunInfo(201,"course_list","");
        }
        return new RetrunInfo(200,"course_list","修改成功");
    }

    @Override
    public Course getCourse(String id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        return course;
    }

    @Override
    public LayUIData getCourse(int page, int rows, String name) {
        List<Course> courses=null;
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(name)){
            courses = courseMapper.selectByLike(name);

        }else {
            CourseExample studentExample = new CourseExample();

            courses = courseMapper.selectByExample(studentExample);
        }

        LayUIData result = new LayUIData();
        result.setRows(courses);
        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        //取总记录数
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        result.setTotal(total);
        result.setPaages(pages);
        return result;
    }

    @Override
    public RetrunInfo deleteCouAll(String[] a) {
        ScoreExample scoreExample = new ScoreExample();
        ScoreExample.Criteria criteria = scoreExample.createCriteria();
        try {
            for (String i:a){
                  criteria.andCouIdEqualTo(i);
                  scoreMapper.deleteByExample(scoreExample);
                courseMapper.deleteByPrimaryKey(i);
            }
        } catch (Exception e) {
            return new RetrunInfo(201,"","删除失败");
        }
        return new RetrunInfo(200,"","");
    }

    @Override
    public RetrunInfo addCourse(Course course) {
        try {
            courseMapper.insert(course);
        } catch (Exception e) {
            return  new RetrunInfo(201,"course_list","");
        }
        return  new RetrunInfo(200,"course_list","添加成功");
    }

    @Override
    public List<Course> getAll() {
        CourseExample courseExample = new CourseExample();
        List<Course> courses = courseMapper.selectByExample(courseExample);
        return courses;
    }
}
