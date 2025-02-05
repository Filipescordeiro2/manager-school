package com.maneger.school.domain;

import com.maneger.school.dto.request.SchoolSubjectRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "school_subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolSubject {

    @Id
    private String nameSubject;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean status;

    @OneToMany(mappedBy = "schoolSubject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeacherSubject> teacherSubjects;

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

    public SchoolSubject(SchoolSubjectRequest request){
        this.nameSubject = request.getNameSubject();
    }
}
