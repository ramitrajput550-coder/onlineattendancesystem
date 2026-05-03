package com.attendance.repository;

import com.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByAttendanceDateAndStudent_CourseClass(LocalDate attendanceDate, String courseClass);
    List<Attendance> findByStudent_CourseClass(String courseClass);
}
