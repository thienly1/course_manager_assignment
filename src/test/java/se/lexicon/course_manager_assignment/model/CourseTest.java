package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseTest {
    private Course course;
    @BeforeEach
    public void unit(){
        course = new Course();
    }
    @Test
    public void test_id_course_return_id_course_successfully(){
        int id = 1;
        Assertions.assertEquals(id, course.getCourseId());
    }
    @Test
    public void test_enroll_student_return_true(){
        Student s1 = new Student();
        s1.setEmail("ly@mail.com");
        Assertions.assertTrue(course.enrollStudent(s1));
    }
}
