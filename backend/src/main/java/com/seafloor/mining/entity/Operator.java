package com.seafloor.mining.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "operators", uniqueConstraints = @UniqueConstraint(columnNames = "operator_id"))
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_id", nullable = false, unique = true, length = 50)
    private String operatorId;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    public Operator() {
    }

    public Operator(String operatorId, String passwordHash) {
        this.operatorId = operatorId;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
