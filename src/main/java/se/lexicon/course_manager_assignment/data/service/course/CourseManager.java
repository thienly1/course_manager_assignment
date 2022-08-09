package se.lexicon.course_manager_assignment.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {
        CourseView createCourseView = new CourseView(form.getId(), form.getCourseName(), form.getStartDate(),
                form.getWeekDuration(), converters.studentsToStudentViews(studentDao.findAll()));
        return createCourseView;
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        CourseView courseViewUpdate = new CourseView(form.getId(), form.getCourseName(), form.getStartDate(),
                form.getWeekDuration(), converters.studentsToStudentViews(studentDao.findAll()));
        return courseViewUpdate;
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        List<CourseView> courseViews = new ArrayList<>();
        for (CourseView c : converters.coursesToCourseViews(courseDao.findAll())) {
            if (c.getCourseName().equalsIgnoreCase(courseName)) {
                courseViews.add(c);
            }
        }
        return courseViews;
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        List<CourseView> courseViews = new ArrayList<>();
        for(CourseView c : converters.coursesToCourseViews(courseDao.findAll())){
            if(c.getStartDate().isBefore(end)){
                courseViews.add(c);
            }
        }
        return courseViews;
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        List<CourseView> courseViews = new ArrayList<>();
        for(CourseView c : converters.coursesToCourseViews(courseDao.findAll())){
            if(c.getStartDate().isAfter(start)){
                courseViews.add(c);
            }
        }
        return courseViews;
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        Student student = studentDao.findById(studentId);
        Course course = courseDao.findById(courseId);
        if(!course.getStudents().contains(student)) {
            course.getStudents().add(student);
            return true;
        }
        return false;
    }
    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        Student student = studentDao.findById(studentId);
        Course course = courseDao.findById(courseId);
        if(course.getStudents().contains(student)){
           course.getStudents().remove(student);
                return true;
            }
        return false;
    }

    @Override
    public CourseView findById(int id) {
        for(CourseView c: converters.coursesToCourseViews(courseDao.findAll())){
            if(c.getId()== id){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<CourseView> findAll() {
        return converters.coursesToCourseViews(courseDao.findAll());
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        List<CourseView> courseViews = new ArrayList<>();
        Student student = studentDao.findById(studentId);
        for(CourseView c: converters.coursesToCourseViews(courseDao.findAll())){
            if(c.getStudents().contains(converters.studentToStudentView(student))){
                courseViews.add(c);
            }
        }
        return courseViews;
    }

    @Override
    public boolean deleteCourse(int id) {
            if (courseDao.findAll().contains(courseDao.findById(id))){
                courseDao.findAll().remove(courseDao.findById(id));
                return true;
            }
        return false;
    }
}
