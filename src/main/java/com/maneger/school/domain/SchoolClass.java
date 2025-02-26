package com.maneger.school.domain;

import com.maneger.school.dto.request.SchoolClassRequest;
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

    private String nameClass;

    @ManyToOne
    @JoinColumn(name = "institution_cnpj", referencedColumnName = "cnpj")
    private Institution institution;

    private String dradeOfSchedules;
    private String period;
    private String yearOfSemester;
    private String semester;
    private String startTime;
    private String endTime;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean status;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SchoolGrade> schoolGrades;

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

    public SchoolClass(SchoolClassRequest request){
        this.nameClass = request.getNameClass();
        this.dradeOfSchedules = request.getDradeOfSchedules();
        this.period = request.getPeriod();
        this.startTime = request.getStartTime();
        this.endTime = request.getEndTime();
        this.semester = request.getSemester();
        this.yearOfSemester = request.getYearOfSemester();

        this.institution = new Institution();
        this.institution.setCnpj(request.getInstitutionCNPJ());
    }
}