package com.nguyen.serviceImpl;

import com.nguyen.exception.UserNotFoundException;
import com.nguyen.model.Student;
import com.nguyen.repository.StudentRepositoryService;
import com.nguyen.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentRepositoryService studentRepositoryService;

    @Override
    public Response doGetAllStudent() {
        List<Student> students = studentRepositoryService.doGetAllStudents();
        return Response.ok(students).build();
    }

    @Override
    public Response doGetStudentById(String id) {
        Student student = studentRepositoryService.doGetStudentById(id);
        if (student == null) {
            throw new UserNotFoundException(UserNotFoundException.class.getSimpleName());
        }
        return Response.ok(student).build();
    }

    @Override
    public Response doPostStudent(Student student) {
        studentRepositoryService.createStudent(student);
        return Response.ok("Create Student Success").type(MediaType.TEXT_PLAIN).build();
    }

    @Override
    public Response doDeleteStudentById(String id) {
        boolean flag = studentRepositoryService.deleteStudent(id);
        if (flag) {
            return Response.ok("Delete Student Success").type(MediaType.TEXT_PLAIN).build();
        }
        throw new UserNotFoundException(UserNotFoundException.class.getSimpleName());
    }

    @Override
    public Response doPutStudent(String id, Student student) {
        boolean flag = studentRepositoryService.updateStudent(id, student);
        if (flag) {
            return Response.ok("Update Student Success").type(MediaType.TEXT_PLAIN).build();
        }
        throw new UserNotFoundException(UserNotFoundException.class.getSimpleName());
    }
}
