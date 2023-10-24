package com.plan.todo.service;

import com.plan.todo.model.TodoEntity;
import com.plan.todo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        repository.save(entity);
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    /**
     *
     * @param entity
     * @return
     * 새 Todo 생성
     */
    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);

        repository.save(entity);
        log.info("Entity Id: {} is saved.", entity.getId());

        return retrieve(entity.getUserId());

    }

    /**
     *
     * @param userId
     * @return
     * userId가 동일한 Todo 리스트 검색
     */
    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    /**
     *
     * @param entity
     * @return
     * Todo 내용 변경
     */
    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);

        final Optional<TodoEntity> original = repository.findById(entity.getId());
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        });
        return retrieve(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }
        if (entity.getUserId() == null) {
            log.warn("Unknown User.");
            throw new RuntimeException("Unknown User.");
        }
    }
}
