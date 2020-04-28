package com.explore.explorecassandra.controller;

import com.explore.explorecassandra.beans.response.CoursesResponseBean;
import com.explore.explorecassandra.service.ICoursesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursesController.class);
    private ICoursesService service;

    public CoursesController(ICoursesService service) {
        this.service = service;
    }

    @GetMapping("/{courseId}")
    public CoursesResponseBean fetchAll(@PathVariable("courseId") String courseId){
        LOGGER.info("Received request {}", courseId);
        return service.fetchById(courseId);
    }
}