package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {
    private Student student;

    @BeforeEach
    public void unit(){
        student = new Student();
    }
    @Test
    public void Test_student_id_get_student_id(){

        int idTest = 1;
        Assertions.assertEquals(idTest, student.getStudentId());
    }
    @Test
    public void Test_Student_name_get_student_name_successfully(){
        String name = "Ly";
        student.setName(name);
        Assertions.assertEquals(name, student.getName());
    }
    @Test
    public void Test_student_id_get_student_id_notEqual(){
        int idTest = 3;
        Assertions.assertNotEquals(idTest, student.getStudentId());
    }

}
