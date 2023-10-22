package com.plan.todo.persistence;

import com.plan.todo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    // select * From todo where userId ='{userId}' 혹은 @Query(sql)
    List<TodoEntity> findByUserId(String userId);
}
