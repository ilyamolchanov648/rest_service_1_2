package dao;

import model.Course;

import java.util.List;

public interface CourseDAO {
    void addCourse(Course course);
    Course getCourseById(int id);
    List<Course> getAllCourses();
    List<Course> getCoursesByAuthorId(int authorId);
    void updateCourse(Course course);
    void deleteCourse(int id);
}
