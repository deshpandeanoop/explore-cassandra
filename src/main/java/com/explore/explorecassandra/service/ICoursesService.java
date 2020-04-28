package com.explore.explorecassandra.service;


import com.explore.explorecassandra.beans.response.CoursesResponseBean;

public interface ICoursesService {

    CoursesResponseBean fetchById(String courseId);
}
