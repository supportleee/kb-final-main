package com.example.kbfinal.service;

import com.example.kbfinal.dto.UserCountDto;
import com.example.kbfinal.dto.UserDto;
import com.example.kbfinal.entity.User;
import com.example.kbfinal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto registerUser(User user) {
        // 비밀번호를 암호화하여 저장

        // password를 인코딩
        // user entity에 인코딩 된 password를 넣기
        userRepository.save(user);
        return UserDto.fromEntity(user);
    }

   public boolean authenticate(String username, String password) {
       // 사용자 조회
       User user = userRepository.findByUsername(username); // 직접 repo에서 구현
       if (user == null) {
           return false;
       }
       // 입력된 비밀번호와 저장된 암호화된 비밀번호를 비교
       return passwordEncoder.matches(password, user.getPassword());

   }

    // 이후 컨트롤러에서 들어오게 될  내용 추가 구현하기
    @Transactional
    public UserDto editUser(Long id, User request) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(request.getUsername());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhonenumber(request.getPhonenumber());

        userRepository.save(user);
        return UserDto.fromEntity(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public UserCountDto getUsersCount() {
        return UserCountDto.builder()
                .count(userRepository.count())
                .build();
    }
}
