package com.zsq.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.TeacherInfo;
import com.zsq.entity.TeacherInfoExample;
import com.zsq.mapper.TeacherInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;
    @Override
    public List<TeacherInfo> getAllTeacher() {
        List<TeacherInfo> teacherInfos = teacherInfoMapper.selectAll();
        return teacherInfos;
    }

    @Override
    public TeacherInfo getTeacher(Integer id) {
        TeacherInfo teacherInfo = teacherInfoMapper.selectByPrimaryKey(id);

        return teacherInfo;
    }

    @Override
    public RetrunInfo updateTeacher(TeacherInfo teacherInfo) {
        teacherInfoMapper.updateByPrimaryKey(teacherInfo);

        return new RetrunInfo(200,"teacher_list","修改成功");
    }

    @Override
    public TeacherInfo login(String username, String password) {
        TeacherInfoExample teacherInfoExample = new TeacherInfoExample();
        teacherInfoExample.createCriteria().andAccountEqualTo(username).andPasswordEqualTo(password);
        List<TeacherInfo> teacherInfos = teacherInfoMapper.selectByExample(teacherInfoExample);
        if(teacherInfos.size()>0){
            return teacherInfos.get(0);
        }
        return null;
    }

    @Override
    public RetrunInfo deleteAll(Integer[] a) {
        for (Integer i:a){
     teacherInfoMapper.deleteByPrimaryKey(i);
        }
        return new RetrunInfo(200,"","");
    }

    @Override
    public LayUIData getTeacher(int page, int rows,String teaName) {
        List<TeacherInfo> teacherInfos=null;
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(teaName)){
          teacherInfos = teacherInfoMapper.selectByLike(teaName);
        }else {
            TeacherInfoExample teacherInfoExample = new TeacherInfoExample();
            teacherInfos = teacherInfoMapper.selectByExample(teacherInfoExample);
        }

        LayUIData result = new LayUIData();
        result.setRows(teacherInfos);
        PageInfo<TeacherInfo> pageInfo = new PageInfo<TeacherInfo>(teacherInfos);
        //取总记录数
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        result.setTotal(total);
        result.setPaages(pages);
        return result;
    }

    @Override
    public RetrunInfo addTeacherInfo(TeacherInfo teacherInfo) {
        teacherInfoMapper.insert(teacherInfo);
        return  new RetrunInfo(200,"teacher_list","添加成功");
    }
}
