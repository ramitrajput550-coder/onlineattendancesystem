package com.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teacher_attendance")
public class TeacherAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(nullable = false)
    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        PRESENT, ABSENT, LATE
    }

    public TeacherAttendance() {}

    public TeacherAttendance(Teacher teacher, LocalDate attendanceDate, Status status) {
        this.teacher = teacher;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
