package com.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(name = "course_class", nullable = false)
    private String courseClass;

    @Column(columnDefinition = "LONGTEXT")
    private String faceDescriptor;

    public Student() {}

    public Student(Long id, User user, String name, String courseClass, String faceDescriptor) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.courseClass = courseClass;
        this.faceDescriptor = faceDescriptor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourseClass() { return courseClass; }
    public void setCourseClass(String courseClass) { this.courseClass = courseClass; }

    public String getFaceDescriptor() { return faceDescriptor; }
    public void setFaceDescriptor(String faceDescriptor) { this.faceDescriptor = faceDescriptor; }
}
