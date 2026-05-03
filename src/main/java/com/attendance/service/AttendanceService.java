package com.attendance.service;

import com.attendance.dto.AttendanceDto;
import com.attendance.model.Attendance;
import com.attendance.model.Student;
import com.attendance.repository.AttendanceRepository;
import com.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String markAttendance(AttendanceDto dto) {
        Optional<Student> studentOpt = studentRepository.findById(Long.valueOf(dto.getStudentId()));
        
        if (studentOpt.isEmpty()) {
            return "Student not found!";
        }

        Attendance attendance = new Attendance();
        attendance.setStudent(studentOpt.get());
        attendance.setAttendanceDate(LocalDate.parse(dto.getDate()));
        attendance.setStatus(Attendance.Status.valueOf(dto.getStatus()));

        attendanceRepository.save(attendance);
        return "Attendance marked successfully!";
    }

    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceByClassAndDate(String courseClass, String date) {
        return attendanceRepository.findByAttendanceDateAndStudent_CourseClass(LocalDate.parse(date), courseClass);
    }
}
