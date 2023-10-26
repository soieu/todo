package com.plan.todo.service;

import com.plan.todo.model.UserEntity;
import com.plan.todo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 사용자 생성
    public UserEntity create(final UserEntity userEntity)
    {
        if(userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String username = userEntity.getUsername();
        if(userRepository.existsByUsername(username)) {
            log.warn("Username already exists {}", username);
            throw new RuntimeException("Username already exists");
        }

        return userRepository.save(userEntity);

    }

    // 로그인 시 인증
    public UserEntity getByCredential(final String username, final String password)
    {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
