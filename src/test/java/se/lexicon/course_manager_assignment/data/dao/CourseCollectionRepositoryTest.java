package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @Test
    void test_create_course_successfully(){
        String courseName = "Advanced";
        int weekDuration = 4;
        LocalDate startDate = LocalDate.of(2022,9,12);
        Course test = testObject.createCourse(courseName,startDate,weekDuration);
        Course courseA = new Course(1);
        courseA.setStartDate(startDate);
        courseA.setCourseName(courseName);
        courseA.setWeekDuration(weekDuration);
        assertEquals(test, courseA);
    }
    @Test
    void test_find_by_id_successfully(){
        Collection<Course> courses= new ArrayList<>();
        Course test1 = testObject.createCourse("advanced", LocalDate.of(2022,5,14), 5);
        Course test2 = testObject.createCourse(".net", LocalDate.of(2022,7,1), 4);
        courses.add(test1);
        courses.add(test2);
        testObject = new CourseCollectionRepository(courses);
        int id = 2;
        assertEquals(testObject.findById(id), test2);
    }
    @Test
    void test_find_by_id_return_null(){
        Collection<Course> courses= new ArrayList<>();
        Course test1 = testObject.createCourse("advanced", LocalDate.of(2022,5,14), 5);
        Course test2 = testObject.createCourse(".net", LocalDate.of(2022,7,1), 4);
        courses.add(test1);
        courses.add(test2);
        testObject = new CourseCollectionRepository(courses);
        int id = 3;
        assertEquals(testObject.findById(id), null);
    }


    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
