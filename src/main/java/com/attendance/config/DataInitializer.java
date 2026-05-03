package com.attendance.config;

import com.attendance.model.Student;
import com.attendance.model.Teacher;
import com.attendance.model.User;
import com.attendance.repository.StudentRepository;
import com.attendance.repository.TeacherRepository;
import com.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Default Admin
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }

        // Initialize Default Teacher
        if (userRepository.findByUsername("teacher1").isEmpty()) {
            User teacherUser = new User();
            teacherUser.setUsername("teacher1");
            teacherUser.setPassword("pass123");
            teacherUser.setRole(User.Role.TEACHER);

            Teacher teacher = new Teacher();
            teacher.setUser(teacherUser);
            teacher.setName("John Doe");
            teacher.setSubject("Java Programming");
            teacherRepository.save(teacher);
        }

        // Initialize Default Students
        if (userRepository.findByUsername("student1").isEmpty()) {
            User studentUser1 = new User();
            studentUser1.setUsername("student1");
            studentUser1.setPassword("pass123");
            studentUser1.setRole(User.Role.STUDENT);

            Student student1 = new Student();
            student1.setUser(studentUser1);
            student1.setName("Alice Smith");
            student1.setCourseClass("MCA-1");
            studentRepository.save(student1);
        }

        if (userRepository.findByUsername("student2").isEmpty()) {
            User studentUser2 = new User();
            studentUser2.setUsername("student2");
            studentUser2.setPassword("pass123");
            studentUser2.setRole(User.Role.STUDENT);

            Student student2 = new Student();
            student2.setUser(studentUser2);
            student2.setName("Bob Johnson");
            student2.setCourseClass("MCA-1");
            studentRepository.save(student2);
        }
        
        System.out.println("Default Data Initialized: admin/admin123, teacher1/pass123, student1/pass123");
    }
}
