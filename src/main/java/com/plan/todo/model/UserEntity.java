package com.plan.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")}) //
public class UserEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy ="uuid")
    private String id;
    @Column(nullable = false)
    private String username;
    private String password; // SSO구현시 비밀먼호 필요 없음. SSO 구현X시 null이면 안됨!!
    private String role;
    private String authProvider;
}
