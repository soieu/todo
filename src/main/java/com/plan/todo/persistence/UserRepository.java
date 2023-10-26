package com.plan.todo.persistence;

import com.plan.todo.model.TodoEntity;
import com.plan.todo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
    UserEntity findByUsernameAndPassword(String username, String password);
}
