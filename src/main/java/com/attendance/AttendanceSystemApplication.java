package com.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AttendanceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceSystemApplication.class, args);
        System.out.println("==================================================");
        System.out.println("Online Attendance System Started at http://localhost:8080");
        System.out.println("==================================================");
    }

}
