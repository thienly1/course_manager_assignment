package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {
        StudentView v1 = new StudentView(student.getStudentId(), student.getName(), student.getEmail(), student.getAddress());
        return v1;
    }
    @Override
    public CourseView courseToCourseView(Course course) {
        List<StudentView> studentViews = new ArrayList<>();
        for(Student s: course.getStudents()){
            studentViews.add(studentToStudentView(s));
        }
        CourseView courseView = new CourseView(course.getCourseId(), course.getCourseName(), course.getStartDate(),
                course.getWeekDuration(), studentViews);
        return courseView;
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        List<CourseView> courseViews = new ArrayList<>();
        for(Course c: courses){
            courseViews.add(courseToCourseView(c));
        }
        return courseViews;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        List<StudentView> studentViews = new ArrayList<>();
        for(Student s: students){
            studentViews.add(studentToStudentView(s));
        }
        return studentViews;
    }
}
