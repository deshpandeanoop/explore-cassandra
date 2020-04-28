package com.explore.explorecassandra.service;


import com.explore.explorecassandra.beans.model.Course;
import com.explore.explorecassandra.beans.request.CoursesRequestBean;
import com.explore.explorecassandra.beans.response.CoursesResponseBean;
import com.explore.explorecassandra.beans.response.ResponseStatusCode;
import com.explore.explorecassandra.beans.response.ResponseStatusMessage;
import com.explore.explorecassandra.repository.CoursesRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class CoursesService implements ICoursesService{

    private final CoursesRepository repository;

    public CoursesService(CoursesRepository repository) {
        this.repository = repository;
    }

    @Override
    public CoursesResponseBean fetchById(String courseId) {
        List<Course> courses = repository.findAllById(Collections.singletonList(courseId));

        CoursesResponseBean responseBean = new CoursesResponseBean();

        if(CollectionUtils.isEmpty(courses)){
            responseBean.setCode(ResponseStatusCode.RESOURCE_NOT_FOUND);
            responseBean.setMessage(ResponseStatusMessage.RESOURCE_NOT_FOUND);
        }else {
            responseBean.setCode(ResponseStatusCode.SUCCESS);
            responseBean.setMessage(ResponseStatusMessage.SUCCESS);
            responseBean.getCourses().addAll(courses);
        }

        return responseBean;
    }

    @Override
    public void save(CoursesRequestBean request) {
        repository.saveAll(request.getCourses());
    }
}