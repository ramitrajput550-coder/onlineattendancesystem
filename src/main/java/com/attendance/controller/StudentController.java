package com.attendance.controller;

import com.attendance.model.Student;
import com.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/class/{courseClass}")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable String courseClass) {
        return ResponseEntity.ok(studentRepository.findByCourseClass(courseClass));
    }
    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @PutMapping("/{id}/face")
    public ResponseEntity<?> updateFaceDescriptor(@PathVariable Long id, @RequestBody java.util.Map<String, String> payload) {
        return studentRepository.findById(id).map(student -> {
            student.setFaceDescriptor(payload.get("faceDescriptor"));
            studentRepository.save(student);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
