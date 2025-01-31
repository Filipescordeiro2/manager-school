package com.maneger.school.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

   // private List<Teacher> teachers;
    //private List<SchoolSubject>schoolSubjects;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean status;


    @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = true;
    }

    @PreUpdate
    public void preUpdate(){
        this.updateAt = LocalDateTime.now();
    }


}
