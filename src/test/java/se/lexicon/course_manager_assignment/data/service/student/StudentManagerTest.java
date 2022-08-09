package se.lexicon.course_manager_assignment.data.service.student;

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
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {StudentManager.class, CourseCollectionRepository.class, StudentCollectionRepository.class, ModelToDto.class})
public class StudentManagerTest {

    @Autowired
    private StudentService testObject;
    @Autowired
    private StudentDao studentDao;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(studentDao);
    }

    //Write your tests here
    @Test
    void test_create_studentView_successfully(){
        CreateStudentForm createStudentForm = new CreateStudentForm(1, "ly", "ly@mail.com", "Växjö");
        StudentView studentView = new StudentView(createStudentForm.getId(), createStudentForm.getName(), createStudentForm.getEmail(),
                                                  createStudentForm.getAddress());
        assertEquals(testObject.create(createStudentForm), studentView);
    }
    @BeforeEach
    void test_unit(){

    }
    @Test
    void test_find_by_id_successfully(){
        Student student1= new Student();
        student1.setName("ly");
        student1.setEmail("ly@mail.com");
        student1.setAddress("växjö");
        Student student2 = new Student();
        student2.setName("sarah");
        student2.setEmail("sarah@mail.com");
        student2.setAddress("växjö");

        Collection<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        studentDao = new StudentCollectionRepository(students);

        Course course1 = new Course();
        Course course2 = new Course();
        Collection<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        CourseDao courseDao = new CourseCollectionRepository(courses);
        Converters converters = new ModelToDto();
        testObject = new StudentManager(studentDao,courseDao, converters);
        assertEquals(testObject.findById(2),converters.studentToStudentView(student2));
    }
    @Test
    void test_find_by_id_return_null(){
        Student student1= new Student();
        student1.setName("ly");
        student1.setEmail("ly@mail.com");
        student1.setAddress("växjö");
        Student student2 = new Student();
        student2.setName("sarah");
        student2.setEmail("sarah@mail.com");
        student2.setAddress("växjö");

        Collection<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        studentDao = new StudentCollectionRepository(students);

        Course course1 = new Course();
        Course course2 = new Course();
        Collection<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        CourseDao courseDao = new CourseCollectionRepository(courses);
        Converters converters = new ModelToDto();
        testObject = new StudentManager(studentDao,courseDao, converters);
        assertEquals(testObject.findById(3),null);
    }
    @Test
    void test_search_by_name_successfully(){
        Student student1= new Student();
        student1.setName("ly");
        student1.setEmail("ly@mail.com");
        student1.setAddress("växjö");

        Student student2 = new Student();
        student2.setName("ly");
        student2.setEmail("ly1@mail.com");
        student2.setAddress("växjö");

        Student student3 = new Student();
        student3.setName("sarah");
        student3.setEmail("sarah@mail.com");
        student3.setAddress("växjö");

        Collection<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        studentDao = new StudentCollectionRepository(students);
        Collection<Student> students1 = new ArrayList<>();
        students1.add(student1);
        students1.add(student2);

        Course course1 = new Course();
        Course course2 = new Course();
        Collection<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        CourseDao courseDao = new CourseCollectionRepository(courses);
        Converters converters = new ModelToDto();

        testObject = new StudentManager(studentDao,courseDao, converters);
        assertEquals(testObject.searchByName("ly"),converters.studentsToStudentViews(students1));
    }
    @Test
    void test_find_all(){
        Student student1= new Student();
        student1.setName("ly");
        student1.setEmail("ly@mail.com");
        student1.setAddress("växjö");
        Student student2 = new Student();
        student2.setName("sarah");
        student2.setEmail("sarah@mail.com");
        student2.setAddress("växjö");

        Collection<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        studentDao = new StudentCollectionRepository(students);

        Course course1 = new Course();
        Course course2 = new Course();
        Collection<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        CourseDao courseDao = new CourseCollectionRepository(courses);
        Converters converters = new ModelToDto();
        testObject = new StudentManager(studentDao,courseDao, converters);
        assertEquals(testObject.findAll(),converters.studentsToStudentViews(students));
    }

    @AfterEach
    void tearDown() {
        StudentSequencer.setStudentSequencer(0);
        studentDao.clear();
    }
}
