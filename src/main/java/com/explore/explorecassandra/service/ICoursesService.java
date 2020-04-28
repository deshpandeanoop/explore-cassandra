package com.explore.explorecassandra.service;


import com.explore.explorecassandra.beans.request.CoursesRequestBean;
import com.explore.explorecassandra.beans.response.CoursesResponseBean;

public interface ICoursesService {

    CoursesResponseBean fetchById(String courseId);

    void save(CoursesRequestBean request);
}
