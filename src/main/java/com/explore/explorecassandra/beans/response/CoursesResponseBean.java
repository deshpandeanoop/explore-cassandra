package com.explore.explorecassandra.beans.response;


import com.explore.explorecassandra.beans.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesResponseBean {
    private final List<Course> courses = new ArrayList<>();
    private ResponseStatusCode code;
    private ResponseStatusMessage message;


    public List<Course> getCourses() {
        return courses;
    }

    public ResponseStatusCode getCode() {
        return code;
    }

    public void setCode(ResponseStatusCode code) {
        this.code = code;
    }

    public ResponseStatusMessage getMessage() {
        return message;
    }

    public void setMessage(ResponseStatusMessage message) {
        this.message = message;
    }
}
