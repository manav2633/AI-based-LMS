package com.exam.portal.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.portal.Model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

     @Query(value = "SELECT DISTINCT c.course_category FROM course c",nativeQuery = true)
    List<String> findDistinctCourseCategory();

   // List<Course> findByCourseCategory(String category);
   List<Course> findByCourseCategory(String courseCategory);

}
