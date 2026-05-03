package com.attendance.controller;

import com.attendance.dto.TeacherAttendanceDto;
import com.attendance.model.Teacher;
import com.attendance.model.TeacherAttendance;
import com.attendance.repository.TeacherAttendanceRepository;
import com.attendance.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherAttendanceRepository teacherAttendanceRepository;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/face")
    public ResponseEntity<?> updateFaceDescriptor(@PathVariable Long id, @RequestBody java.util.Map<String, String> payload) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setFaceDescriptor(payload.get("faceDescriptor"));
            teacherRepository.save(teacher);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/attendance/mark")
    public ResponseEntity<String> markTeacherAttendance(@RequestBody TeacherAttendanceDto dto) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(dto.getTeacherId());
        if (teacherOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Teacher not found");
        }
        
        LocalDate date = dto.getDate() != null ? LocalDate.parse(dto.getDate()) : LocalDate.now();
        TeacherAttendance.Status status = TeacherAttendance.Status.valueOf(dto.getStatus().toUpperCase());

        Optional<TeacherAttendance> existing = teacherAttendanceRepository.findByTeacherIdAndAttendanceDate(dto.getTeacherId(), date);
        TeacherAttendance att;
        if (existing.isPresent()) {
            att = existing.get();
            att.setStatus(status);
        } else {
            att = new TeacherAttendance(teacherOpt.get(), date, status);
        }
        teacherAttendanceRepository.save(att);
        
        return ResponseEntity.ok("Teacher attendance marked successfully");
    }

    @GetMapping("/attendance/{teacherId}")
    public ResponseEntity<List<TeacherAttendance>> getTeacherAttendance(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherAttendanceRepository.findByTeacherIdOrderByAttendanceDateDesc(teacherId));
    }

    @GetMapping("/attendance")
    public ResponseEntity<List<TeacherAttendance>> getAllTeacherAttendance(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(teacherAttendanceRepository.findByAttendanceDate(localDate));
    }
}
