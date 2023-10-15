package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Optional<Student> getOneStudent(@PathVariable("studentId") Long studentId){
        return studentService.getOneStudents(studentId);
    }

    @PostMapping()
    public Student registerNewStudent(@RequestBody Student student){
        return studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void removeStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path ="{studentId}")
    public void editStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }

//    @PutMapping(path ="{studentId}")
//    public List<Student> editStudent(
//            @PathVariable("studentId") Long studentId,
//            @RequestParam (required = false) String name,
//            @RequestParam (required = false) String email){
//        studentService.updateStudent(studentId, name, email);
//        return studentService.getStudents();
//    }

//    @PutMapping(path ="{studentId}")
//    public List<Student> editStudent(
//            @PathVariable("studentId") Long studentId,
//            @RequestBody Student student){
//        studentService.updateStudent(studentId, student);
//        return studentService.getStudents();
//    }
}
