package com.attendance.dto;

import com.attendance.model.User;

public class AuthDto {

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterRequest {
        private String name;
        private String courseClass;
        private String username;
        private String password;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getCourseClass() { return courseClass; }
        public void setCourseClass(String courseClass) { this.courseClass = courseClass; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class TeacherRegisterRequest {
        private String name;
        private String subject;
        private String username;
        private String password;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private boolean success;
        private String message;
        private Long userId;
        private String username;
        private User.Role role;
        
        // Additional Info based on role
        private Long profileId;
        private String name;
        private String info; // Class for student, Subject for teacher

        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public User.Role getRole() { return role; }
        public void setRole(User.Role role) { this.role = role; }
        
        public Long getProfileId() { return profileId; }
        public void setProfileId(Long profileId) { this.profileId = profileId; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getInfo() { return info; }
        public void setInfo(String info) { this.info = info; }
    }
}
