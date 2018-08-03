package com.zsq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.ScoreExample;
import com.zsq.entity.Student;
import com.zsq.entity.StudentExample;
import com.zsq.mapper.ScoreMapper;
import com.zsq.mapper.StudentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public RetrunInfo updateStudent(Student student) {
        try {
            studentMapper.updateByPrimaryKey(student);
        } catch (Exception e) {
            return new RetrunInfo(201,"student_list","");
        }
        return new RetrunInfo(200,"student_list","修改成功");
    }

    @Override
    public Student getStudent(String  id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }

    @Override
    public LayUIData getOnStudent(int page, int rows, String name) {
        List<Student> students=null;
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(name)){
            students = studentMapper.selectByLike(name);

        }else {
            StudentExample studentExample = new StudentExample();

            students = studentMapper.selectByExample(studentExample);
        }

        LayUIData result = new LayUIData();
        result.setRows(students);
        PageInfo<Student> pageInfo = new PageInfo<Student>(students);
        //取总记录数
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        result.setTotal(total);
        result.setPaages(pages);
        return result;
    }

    @Override
    public RetrunInfo deleteStuAll(String[] a) {
        ScoreExample scoreExample = new ScoreExample();
        ScoreExample.Criteria criteria = scoreExample.createCriteria();
        try {
            for (String i:a){
                criteria.andStuIdEqualTo(i);
                scoreMapper.deleteByExample(scoreExample);
                studentMapper.deleteByPrimaryKey(i);
            }
        } catch (Exception e) {
            return new RetrunInfo(201,"","删除失败");
        }
        return new RetrunInfo(200,"","");
    }

    @Override
    public RetrunInfo addStudeng(Student student) {
        try {
            studentMapper.insert(student);
        } catch (Exception e) {
            return  new RetrunInfo(201,"student_list","");
        }
        return  new RetrunInfo(200,"student_list","添加成功");
    }

    @Override
    public List<Student> getStudentByName(String name) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andNameEqualTo(name);
        List<Student> students = studentMapper.selectByExample(studentExample);
        return students;
    }
}
