package se.lexicon.course_manager_assignment.data.service.course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {

    @Autowired
    private CourseService testObject;

    @Autowired
    private CourseDao courseDao;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(courseDao);
    }

    //Write your tests here
    private Student student1;
    private Student student2;
    private Student student3;
    private Collection<Student> students;
    private StudentDao studentDao;
    private Course course1;
    private Collection<Course> courses;
    private Converters converters;
    private  Course course2;

    @Test
    void test_update_courseView_successfully(){
        List<StudentView> studentViews = new ArrayList<>();
        UpdateCourseForm updateCourseForm = new UpdateCourseForm(5, "Java", LocalDate.parse("2022-05-23"),2);
        CourseView courseView = new CourseView(updateCourseForm.getId(), updateCourseForm.getCourseName(), updateCourseForm.getStartDate(),
                updateCourseForm.getWeekDuration(), studentViews);

        assertEquals(testObject.update(updateCourseForm),courseView);
    }
    @BeforeEach
    void test_unit(){
        student1= new Student();
        student1.setName("ly");
        student1.setEmail("ly@mail.com");
        student1.setAddress("växjö");

        student2 = new Student();
        student2.setName("ly");
        student2.setEmail("ly1@mail.com");
        student2.setAddress("växjö");

        student3 = new Student();
        student3.setName("sarah");
        student3.setEmail("sarah@mail.com");
        student3.setAddress("växjö");
        students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        studentDao = new StudentCollectionRepository(students);

        course1 = new Course();
        course1.setCourseName("a");
        course1.setWeekDuration(4);
        course1.setStartDate(LocalDate.parse("2022-04-24"));
        course1.setStudents(students);

        course2 = new Course();
        course2.setCourseName("b");
        course2.setStartDate(LocalDate.of(2022, 6,20));
        course2.setWeekDuration(6);
        course2.setStudents(students);

        courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courseDao = new CourseCollectionRepository(courses);
        converters = new ModelToDto();
        testObject = new CourseManager(courseDao,studentDao,converters);
    }
    @Test
    void test_search_by_date_before(){

        Collection<Course> courses1 = new ArrayList<>();
        courses1.add(course1);
        assertEquals(testObject.searchByDateBefore(LocalDate.of(2022,6,30)),converters.coursesToCourseViews(courses));
        assertEquals(testObject.searchByDateBefore(LocalDate.of(2022,6,15)),converters.coursesToCourseViews(courses1));
    }
    @Test
    void test_add_student_to_course_successfully(){
        assertTrue(testObject.addStudentToCourse(1, 3));
    }
    @Test
    void test_add_student_to_course_return_false(){
        assertFalse(testObject.addStudentToCourse(1, 2));
    }
    @Test
    void test_remove_student_from_course_return_true(){
        assertTrue(testObject.removeStudentFromCourse(1, 2));
    }
    @Test
    void test_find_by_id_successfully(){
       assertEquals(testObject.findById(1), converters.courseToCourseView(course1));
    }
    @Test
    void test_find_by_student_id(){
        assertEquals(testObject.findByStudentId(2),converters.coursesToCourseViews(courses));
    }
    @Test
    void test_delete_course_successfully(){
        assertTrue(testObject.deleteCourse(1));
    }
    @Test
    void test_delete_course_return_false(){
        assertFalse(testObject.deleteCourse(3));
    }

    @AfterEach
    void tearDown() {
        courseDao.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
