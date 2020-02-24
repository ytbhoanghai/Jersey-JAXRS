package com.nguyen.repository;

import com.nguyen.model.Student;

import java.util.List;

public interface StudentRepositoryService {

    List<Student> doGetAllStudents();

    Student doGetStudentById(String id);

    boolean updateStudent(String id, Student student);

    boolean deleteStudent(String id);

    void createStudent(Student student);
}
