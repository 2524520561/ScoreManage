package com.zsq.service;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.Student;

import java.util.List;


public interface StudentService {
    RetrunInfo updateStudent(Student student);
    Student getStudent(String id);
    LayUIData getOnStudent(int page, int rows, String name);
    RetrunInfo deleteStuAll(String a[]);
    RetrunInfo addStudeng(Student student);
    List<Student> getStudentByName(String name);

}
