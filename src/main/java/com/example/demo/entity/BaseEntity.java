//package com.example.demo.entity;
//
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
//@Data
//@MappedSuperclass
//public abstract class BaseEntity {
//    @Column(updatable = false, name = "created_at")
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//    @Column(name = "updated_at")
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
//}
