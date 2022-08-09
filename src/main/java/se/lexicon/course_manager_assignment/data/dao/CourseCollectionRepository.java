package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        Course courseA = new Course();
        courseA.setStartDate(startDate);
        courseA.setCourseName(courseName);
        courseA.setWeekDuration(weekDuration);
        return courseA;
    }

    @Override
    public Course findById(int id) {
        for(Course c: courses){
            if(c.getCourseId()==id){
                return c;
            }
        }
        return null;

    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        Collection<Course> nameContains = new ArrayList<>();
        for(Course c: courses){
            if(c.getCourseName().equalsIgnoreCase(name)){
                nameContains.add(c);
            }
        }
        return nameContains;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        Collection<Course> dateBeforeEnd= new ArrayList<>();
        for(Course c: courses){
            if(c.getStartDate().isBefore(end)){
                dateBeforeEnd.add(c);
            }
        }
        return dateBeforeEnd;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Collection<Course> dateAfterEnd= new ArrayList<>();
        for(Course c: courses){
            if(c.getStartDate().isAfter(start)){
                dateAfterEnd.add(c);
            }
        }
        return dateAfterEnd;
    }

    @Override
    public Collection<Course> findAll() {
        return courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> studentCourses= new ArrayList<>();
        for(Course c: courses){
            if(c.getStudents().contains(findByStudentId(studentId))){
                studentCourses.add(c);
            }
        }
        return studentCourses;
    }

    @Override
    public boolean removeCourse(Course course) {
        if(courses.contains(course)){
            courses.remove(course);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
        courses.clear();
    }
}
