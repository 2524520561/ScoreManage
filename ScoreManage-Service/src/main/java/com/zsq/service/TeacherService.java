package com.zsq.service;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.TeacherInfo;

import java.util.List;

public interface TeacherService {
    List<TeacherInfo> getAllTeacher();
    TeacherInfo getTeacher(Integer id);
    TeacherInfo login(String username, String password);
    RetrunInfo updateTeacher(TeacherInfo teacherInfo);
    LayUIData getTeacher(int page, int rows, String teaName);
   RetrunInfo deleteAll(Integer a[]);
    RetrunInfo addTeacherInfo(TeacherInfo teacherInfo);
}
