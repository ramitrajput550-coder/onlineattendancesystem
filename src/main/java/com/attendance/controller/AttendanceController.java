package com.attendance.controller;

import com.attendance.dto.AttendanceDto;
import com.attendance.model.Attendance;
import com.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceDto dto) {
        String result = attendanceService.markAttendance(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getStudentAttendance(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudent(studentId));
    }

    @GetMapping("/class/{courseClass}")
    public ResponseEntity<List<Attendance>> getClassAttendance(
            @PathVariable String courseClass,
            @RequestParam String date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByClassAndDate(courseClass, date));
    }
}
