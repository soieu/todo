package com.plan.todo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo") // 테이블 이름을 지정하기 위함 => db의 Todo 테이블에 매핑 => 1.Table name 2. Entity name 3. Class name
public class TodoEntity {
    @Id //Entity의 Private key 설정 @GeneratedValue로 자동으로 생성도 가능
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy ="uuid")
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
