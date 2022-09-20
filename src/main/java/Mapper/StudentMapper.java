package Mapper;

import pojo.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> selectAll();

    void addStudent(Student student);

    void deleteById(Student student);
}
