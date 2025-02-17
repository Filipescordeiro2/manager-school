package com.maneger.school.domain;

import com.maneger.school.dto.request.SecretarytInstitutionRequest;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "secretary_institution")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SecretaryInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secretary_cpf")
    private Secretary secretary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_cnpj", referencedColumnName = "cnpj")
    private Institution institution;

    private LocalDateTime startDate;
    private LocalDateTime uptdateAt;
    private boolean registration;

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now();
        this.uptdateAt = LocalDateTime.now();
        this.registration = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.uptdateAt = LocalDateTime.now();
    }

    public SecretaryInstitution(SecretarytInstitutionRequest request) {
        this.secretary = new Secretary();
        this.secretary.setCpf(request.getSecretaryCPF());
        this.institution = new Institution();
        this.institution.setCnpj(request.getInstitutionCNPJ());
    }
}
