package com.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "LONGTEXT")
    private String faceDescriptor;

    public Teacher() {}

    public Teacher(Long id, User user, String name, String subject) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.subject = subject;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getFaceDescriptor() { return faceDescriptor; }
    public void setFaceDescriptor(String faceDescriptor) { this.faceDescriptor = faceDescriptor; }
}
