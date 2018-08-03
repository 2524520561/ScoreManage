package com.zsq.service;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.entity.Course;

import java.util.List;

public interface CourseService {
    RetrunInfo updateCourse(Course course);
    Course getCourse(String id);
    LayUIData getCourse(int page, int rows, String name);
    RetrunInfo deleteCouAll(String a[]);
    RetrunInfo addCourse(Course course);
    List<Course> getAll();
}
