package com.attendance.service;

import com.attendance.dto.AuthDto;
import com.attendance.model.Student;
import com.attendance.model.Teacher;
import com.attendance.model.User;
import com.attendance.repository.StudentRepository;
import com.attendance.repository.TeacherRepository;
import com.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public AuthDto.LoginResponse authenticate(AuthDto.LoginRequest request) {
        AuthDto.LoginResponse response = new AuthDto.LoginResponse();

        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(request.getPassword())) {
            User user = userOpt.get();
            response.setSuccess(true);
            response.setMessage("Login Successful");
            response.setUserId(user.getId());
            response.setUsername(user.getUsername());
            response.setRole(user.getRole());

            if (user.getRole() == User.Role.STUDENT) {
                Optional<Student> student = studentRepository.findByUserId(user.getId());
                student.ifPresent(s -> {
                    response.setProfileId(s.getId());
                    response.setName(s.getName());
                    response.setInfo(s.getCourseClass());
                });
            } else if (user.getRole() == User.Role.TEACHER) {
                Optional<Teacher> teacher = teacherRepository.findByUserId(user.getId());
                teacher.ifPresent(t -> {
                    response.setProfileId(t.getId());
                    response.setName(t.getName());
                    response.setInfo(t.getSubject());
                });
            } else {
                response.setName("Administrator");
            }
        } else {
            response.setSuccess(false);
            response.setMessage("Invalid username or password");
        }

        return response;
    }

    public AuthDto.LoginResponse registerStudent(AuthDto.RegisterRequest request) {
        AuthDto.LoginResponse response = new AuthDto.LoginResponse();
        
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            response.setSuccess(false);
            response.setMessage("Username already exists!");
            return response;
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(User.Role.STUDENT);

        Student student = new Student();
        student.setUser(user);
        student.setName(request.getName());
        student.setCourseClass(request.getCourseClass());

        studentRepository.save(student);

        response.setSuccess(true);
        response.setMessage("Registration Successful!");
        return response;
    }

    public AuthDto.LoginResponse registerTeacher(AuthDto.TeacherRegisterRequest request) {
        AuthDto.LoginResponse response = new AuthDto.LoginResponse();
        
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            response.setSuccess(false);
            response.setMessage("Username already exists!");
            return response;
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(User.Role.TEACHER);

        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setName(request.getName());
        teacher.setSubject(request.getSubject());

        teacherRepository.save(teacher);

        response.setSuccess(true);
        response.setMessage("Teacher Registration Successful!");
        return response;
    }
}
