package com.attendance.repository;

import com.attendance.model.TeacherAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherAttendanceRepository extends JpaRepository<TeacherAttendance, Long> {
    List<TeacherAttendance> findByTeacherIdOrderByAttendanceDateDesc(Long teacherId);
    Optional<TeacherAttendance> findByTeacherIdAndAttendanceDate(Long teacherId, LocalDate date);
    List<TeacherAttendance> findByAttendanceDate(LocalDate date);
}
