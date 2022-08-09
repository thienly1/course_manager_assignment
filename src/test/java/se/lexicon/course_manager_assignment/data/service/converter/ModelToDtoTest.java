package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }

    //write your tests here
    private String name;
    private String email;
    private  String address;
    private String name1;
    private String email1;
    private  String address1;
    private Student student1;
    private Student student;
    private StudentView studentView;
    private StudentView studentView1;
    private Course course;
    private Course course1;
    private CourseView courseView;
    Collection<Student> studentList;
    List<StudentView> studentViews;
    List<CourseView> courseViews;

    @BeforeEach
    void test_unit() {
        name = "ly";
        email = "ly@mail.com";
        address = "Växjö";
        student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAddress(address);
        student1 = new Student();
        name1 = "ly";
        email1 = "ly1@mail.com";
        address1 = "Växjö";
        student1.setName(name1);
        student1.setEmail(email1);
        student1.setAddress(address1);
        studentView = new StudentView(student.getStudentId(), student.getName(), student.getEmail(), student.getAddress());
        studentView1 = new StudentView(student1.getStudentId(), student1.getName(), student1.getEmail(), student1.getAddress());
        studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentViews = new ArrayList<>();
        studentViews.add(studentView);
        studentViews.add(studentView1);
        course = new Course();
        course.setCourseName("a");
        course.setWeekDuration(4);
        course.setStartDate(LocalDate.parse("2022-04-24"));
        course.setStudents(studentList);
        courseView = new CourseView(course.getCourseId(), course.getCourseName(), course.getStartDate(),
                course.getWeekDuration(), studentViews);
    }

    @Test
    void test_student_to_studentView_successfully(){
        assertEquals(testObject.studentToStudentView(student), studentView);
    }

    @Test
    void test_students_to_studentView_successfully(){
        assertEquals(testObject.studentsToStudentViews(studentList), studentViews);
    }
    @Test
    void test_course_to_courseView_successfully() {
        assertEquals(courseView, testObject.courseToCourseView(course));
    }
    @Test
    void test_courses_to_courseView_successfully() {
        course1 = new Course();
        course1.setCourseName("b");
        course1.setStartDate(LocalDate.of(2022, 6,20));
        course1.setWeekDuration(6);
        course1.setStudents(studentList);

        Collection<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);

        CourseView courseView1 = new CourseView(course1.getCourseId(), course1.getCourseName(), course1.getStartDate(),
                course1.getWeekDuration(),studentViews);
        courseViews= new ArrayList<>();
        courseViews.add(courseView);
        courseViews.add(courseView1);
        assertEquals(courseViews, testObject.coursesToCourseViews(courses));
    }

}
