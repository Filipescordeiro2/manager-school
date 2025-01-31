package com.maneger.school.util;


import com.maneger.school.domain.Institution;
import com.maneger.school.domain.Student;
import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.response.InstitutionResponse;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.dto.response.StudentResponse;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class StudentInstitutionUtils {

    private final StudentRepository studentRepository;
    private final InstitutionRepository institutionRepository;

    public StudentInstitutionResponse convertToStudentResponse(StudentInstitution studentInstitution) {
        var student = findStudentById(studentInstitution.getStudent().getCpf());
        var institution = findInstitutionById(studentInstitution.getInstitution().getCnpj());
        var responseInstitution = buildInstitutionResponse(institution);
        var responseStudent = buildStudentResponse(student);
        return buildStudentInstitutionResponse(studentInstitution, responseStudent, responseInstitution);
    }

    private Student findStudentById(String studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudantException("Student Not Found"));
    }

    private Institution findInstitutionById(String institutionId) {
        return institutionRepository.findById(institutionId)
                .orElseThrow(() -> new InstitutionException("Institution Not Found"));
    }

    private InstitutionResponse buildInstitutionResponse(Institution institution) {
        return InstitutionResponse.builder()
                .nameInstitution(institution.getNameInstitution())
                .cnpj(institution.getCnpj())
                .cellPhone(institution.getCellPhone())
                .email(institution.getEmail())
                .typeOfInstitution(institution.getTypeOfInstitution())
                .creatAt(institution.getCreatAt())
                .uptdateAt(institution.getUptdateAt())
                .status(institution.isStatus())
                .build();
    }

    private StudentResponse buildStudentResponse(Student student) {
        return StudentResponse.builder()
                .nameStudent(student.getNameStudent())
                .cpf(student.getCpf())
                .cellPhone(student.getCellPhone())
                .email(student.getEmail())
                .typeUser(student.getTypeUser())
                .creatAt(student.getCreatAt())
                .uptdateAt(student.getUptdateAt())
                .status(student.isStatus())
                .dateOfBirth(student.getDateOfBirth())
                .surnameStudent(student.getSurnameStudent())
                .userAccess(student.getUserAccess())
                .reasonsForBlocking(student.getReasonsForBlocking())
                .reasonsForBlockingDescription(student.getReasonsForBlockingDescription())
                .build();
    }

    private StudentInstitutionResponse buildStudentInstitutionResponse(StudentInstitution studentInstitution,
                                                                       StudentResponse responseStudent,
                                                                       InstitutionResponse responseInstitution) {
        return StudentInstitutionResponse.builder()
                .studentResponse(responseStudent)
                .institutionResponses(Collections.singletonList(responseInstitution))
                .course(studentInstitution.getCourse())
                .startDate(studentInstitution.getStartDate())
                .uptdateAt(studentInstitution.getUptdateAt())
                .status(studentInstitution.isRegistration())
                .build();
    }
}
