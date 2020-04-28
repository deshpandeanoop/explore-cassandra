package com.explore.explorecassandra.beans.request;


import com.explore.explorecassandra.beans.model.Course;

import java.util.List;

public class CoursesRequestBean {
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
