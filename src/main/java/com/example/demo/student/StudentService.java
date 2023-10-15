package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getOneStudents(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(exists){
            return studentRepository.findById(studentId);
        }else {
            throw new IllegalStateException("no record exist to that id");
        }
    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email already exists");
        }
        return studentRepository.save(student);
//        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
//        studentRepository.findAllById(studentId);
        boolean exists = studentRepository.existsById(studentId);

        if(exists){
            studentRepository.deleteById(studentId);
        }else {
            throw new IllegalStateException("id does not exist");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student updatedStudent = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("record does not exist h"));

//        !Objects.equals(updatedStudent.getName(), name) = if name provided is not the same as current one
        if(name != null && name.length() > 0 && !Objects.equals(updatedStudent.getName(), name)){
            updatedStudent.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(updatedStudent.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken cant update your crap");
            }
            updatedStudent.setEmail(email);
        }
    }

//    @Transactional
//    public void updateStudent(Long studentId, String name, String email) {
//
//        boolean exists = studentRepository.existsById(studentId);
//
//        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("record does not exist h"));
//
//        if (exists){
//            student.setName(name);
//            student.setEmail(email);
//        }else{
//            throw new IllegalStateException("record does not exist");
//        }
//    }

//    @Transactional
//    public void updateStudent(Long studentId, Student student) {
//
//        boolean exists = studentRepository.existsById(studentId);
////        Student updatedStudent = new Student();
//        Student updatedStudent = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("record does not exist h"));
//
////        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("record does not exist h"));
//
//        if (exists){
//            updatedStudent.setName(student.getName());
//            updatedStudent.setEmail(student.getEmail());
//        }else{
//            throw new IllegalStateException("record does not exist");
//        }
//    }
}
