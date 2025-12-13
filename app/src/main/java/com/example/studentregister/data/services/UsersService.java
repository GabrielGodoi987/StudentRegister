package com.example.studentregister.data.services;

import com.example.studentregister.data.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class UsersService {
    private static UsersService instance;


    public static UsersService getInstance(){
        if (instance == null) {
            instance = new UsersService();
        }
        return instance;
    }

    private final List<Student> students;
    private int nextId = 1;

    private UsersService() {
        this.students = new ArrayList<>();
    }

    public Student create(Student student) {
        student.setId(nextId++);
        students.add(student);
        return student;
    }


    public List<Student> findAll() {
        return new ArrayList<>(students);
    }



    public Student findById(Integer id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }


    public boolean update(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);

            if (current.getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Integer id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}
