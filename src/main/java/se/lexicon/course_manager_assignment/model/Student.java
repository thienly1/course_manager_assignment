package se.lexicon.course_manager_assignment.model;


import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;

import java.util.Objects;

public class Student {

    private int studentId; // unique attribute and set through constructor with StudentDAO class
    private String name;
    private String email; //unique attribute
    private String address;

    public Student() {
        studentId = StudentSequencer.nextStudentId();
    }

    public Student(int studentId) {
        this();
        this.studentId= studentId;
    }
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getStudentId() == student.getStudentId() && Objects.equals(getName(), student.getName()) && Objects.equals(getEmail(), student.getEmail()) && Objects.equals(getAddress(), student.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getName(), getEmail(), getAddress());
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
