package se.lexicon.course_manager_assignment.model;


import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Course {
    private int courseId;
    private String courseName;
    private LocalDate startDate ;
    private int weekDuration;
    private Collection<Student> students;


    public Course() {
        courseId= CourseSequencer.nextCourseId();
    }

    public Course(int courseId) {
        this();
        this.courseId = courseId;

    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent(Student student) {
        // if list is null first instantiate it then iterate on array
       if(students== null){
           students = new ArrayList<>();
       }
        if(!students.contains(student)){
            students.add(student);
            return true;
        }
        return false;
    }

    public boolean unEnrollStudent(Student student){
        if(students.contains(student)){
            students.remove(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return courseId == course.courseId && getWeekDuration() == course.getWeekDuration() && Objects.equals(getCourseName(), course.getCourseName()) && Objects.equals(getStartDate(), course.getStartDate()) && Objects.equals(getStudents(), course.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, getCourseName(), getStartDate(), getWeekDuration(), getStudents());
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }
}
