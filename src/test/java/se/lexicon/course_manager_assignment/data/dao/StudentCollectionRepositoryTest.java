package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    @Test
    void test_create_student_successfully(){
        Collection<Student> students = new ArrayList<>();
        testObject = new StudentCollectionRepository(students);
        String name = "ly";
        String email = "ly@mail.com";
        String address= "växjö";
        Student a = testObject.createStudent(name, email, address);
        Student b = new Student(1);
        b.setEmail(email);
        b.setAddress(address);
        b.setName(name);
        assertEquals(a,b);
    }
    @Test
    void test_create_student_return_null(){
        Collection<Student> students = new ArrayList<>();
        testObject = new StudentCollectionRepository(students);
        String name = "ly";
        String email = "ly@mail.com";
        String address= "växjö";
        String email2= "ly@mail.com";

        Student b = new Student(1);
        b.setEmail(email);
        b.setAddress(address);
        b.setName(name);
        students.add(b);
        Student a1 = testObject.createStudent(name, email2, address);
        assertEquals(a1,null);
    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
