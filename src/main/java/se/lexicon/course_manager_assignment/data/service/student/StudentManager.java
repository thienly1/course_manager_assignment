package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;


import java.util.ArrayList;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        StudentView studentView = new StudentView(form.getId(), form.getName(), form.getEmail(), form.getAddress());
        return studentView;
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        StudentView studentView = new StudentView(form.getId(), form.getName(), form.getEmail(), form.getAddress());
        return studentView;
    }

    @Override
    public StudentView findById(int id) {
        for (StudentView s : converters.studentsToStudentViews(studentDao.findAll())) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public StudentView searchByEmail(String email) {
        for(StudentView s: converters.studentsToStudentViews(studentDao.findAll())){
            if(s.getEmail().equalsIgnoreCase(email)){
                return s;
            }
        }
        return null;
    }

    @Override
    public List<StudentView> searchByName(String name) {
        List<StudentView> studentViews = new ArrayList<>();
        for( StudentView s: converters.studentsToStudentViews(studentDao.findAll())){
            if(s.getName().equalsIgnoreCase(name)){
                studentViews.add(s);
            }
        }
        return studentViews;
    }

    @Override
    public List<StudentView> findAll() {
        return converters.studentsToStudentViews(studentDao.findAll());
    }

    @Override
    public boolean deleteStudent(int id) {
        for(StudentView s: converters.studentsToStudentViews(studentDao.findAll())){
            if(s.getId()== id){
                converters.studentsToStudentViews(studentDao.findAll()).remove(s);
                return true;
            }
        }
        return false;
    }
}
