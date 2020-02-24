package com.nguyen.repository;

import com.nguyen.model.Student;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class StudentRepository implements StudentRepositoryService{

    private static final String END_POINT_STUDENT = "https://5e5125e9f2c0d300147c04ea.mockapi.io/rest/v1/";

    private static List<Student> students;
    static {
        Client client = ClientBuilder
                .newBuilder()
                .register(JacksonFeature.class)
                .build();

        WebTarget target = client.target(END_POINT_STUDENT).path("student");


        Response response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            GenericType<List<Student>> studentGenericType = new GenericType<List<Student>>(){};
            students = response.readEntity(studentGenericType);
        } else {
            System.out.println("Connect Failed");
        }
    }

    public List<Student> doGetAllStudents() {
        return students;
    }

    public Student doGetStudentById(String id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean updateStudent(String id, Student student) {
        student.setId(id);
        Student _student = doGetStudentById(id);
        if (_student != null) {
            students.remove(_student);
            students.add(student);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        Student _student = doGetStudentById(id);
        if (_student != null) {
            students.remove(_student);
            return true;
        }

        return false;
    }

    public void createStudent(Student student) {
        students.add(student);
    }

}
