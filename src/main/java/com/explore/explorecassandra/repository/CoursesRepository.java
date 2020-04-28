package com.explore.explorecassandra.repository;


import com.explore.explorecassandra.beans.model.Course;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CassandraRepository<Course, String>{

}
