package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        Student a = new Student();
        a.setName(name);
        a.setAddress(address);
        for(Student s: students){
            if(email.equals(s.getEmail())){
                return null;
            }
        }
        a.setEmail(email);
       return a;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        for(Student s: students){
            if(s.getEmail().equalsIgnoreCase(email)){
                return s;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        Collection<Student> nameContains = new ArrayList<>();
        for(Student s: students){
            if(s.getName().equalsIgnoreCase(name)){
                nameContains.add(s);
            }
        }
        return nameContains;
    }

    @Override
    public Student findById(int id) {
        for(Student s: students){
            if(s.getStudentId()==id){
                return s;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    @Override
    public boolean removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
        students.clear();
    }
}
