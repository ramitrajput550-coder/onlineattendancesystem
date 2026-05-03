package com.attendance.dto;

public class AttendanceDto {
    private Long studentId;
    private String status; // PRESENT, ABSENT, LATE
    private String date; // YYYY-MM-DD format

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
