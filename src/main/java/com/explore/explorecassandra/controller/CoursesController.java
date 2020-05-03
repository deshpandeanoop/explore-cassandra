package com.explore.explorecassandra.controller;

import com.explore.explorecassandra.beans.request.CoursesRequestBean;
import com.explore.explorecassandra.beans.response.CoursesResponseBean;
import com.explore.explorecassandra.service.ICoursesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursesController.class);
    private final ICoursesService service;

    public CoursesController(ICoursesService service) {
        this.service = service;
    }

    @GetMapping("/{courseId}")
    public CoursesResponseBean fetchAll(@PathVariable("courseId") String courseId){
        LOGGER.info("Received request {}", courseId);
        return service.fetchById(courseId);
    }

    @PostMapping
    public void save(@RequestBody CoursesRequestBean request){
        LOGGER.info("Received Courses save request");
        service.save(request);
    }
}