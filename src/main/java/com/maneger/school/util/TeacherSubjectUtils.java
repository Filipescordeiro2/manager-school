package com.maneger.school.util;

import com.maneger.school.domain.*;
import com.maneger.school.dto.response.*;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.repository.InstitutionRepository;
import com.maneger.school.repository.SchoolSubjectRepository;
import com.maneger.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherSubjectUtils {

    private final TeacherRepository teacherRepository;
    private final InstitutionRepository institutionRepository;
    private final SchoolSubjectRepository schoolSubjectRepository;

    public TeacherSubjectResponse convertTeacherSubjectResponse(TeacherSubject teacherSubject) {
        var teacher = findTeachertById(teacherSubject.getTeacher().getTeacherCpf());
        var institution = findInstitutionById(teacherSubject.getInstitution().getCnpj());
        var schoolSubject = findSchoolSubject(teacherSubject.getSchoolSubject().getNameSubject());
        var responseSubjetct = buildSchoolSubjectResponse(schoolSubject);
        var responseInstitution = buildInstitutionResponse(institution);
        var responseTeacher = buildStudentResponse(teacher);
        return buildStudentInstitutionResponse(teacherSubject, responseTeacher, responseInstitution,responseSubjetct);
    }

    private Teacher findTeachertById(String teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new StudantException("Student Not Found"));
    }

    private Institution findInstitutionById(String institutionId) {
        return institutionRepository.findById(institutionId)
                .orElseThrow(() -> new InstitutionException("Institution Not Found"));
    }

    private SchoolSubject findSchoolSubject(String nameSubjetc){
        return schoolSubjectRepository.findById(nameSubjetc)
                .orElseThrow(()->new RuntimeException("Error"));
    }

    private SchoolSubjectResponse buildSchoolSubjectResponse(SchoolSubject schoolSubject){
        return SchoolSubjectResponse
                .builder()
                .nameSubject(schoolSubject.getNameSubject())
                .build();
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

    private TeacherResponse buildStudentResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .nameTeacher(teacher.getNameTeacher())
                .cpf(teacher.getTeacherCpf())
                .cellPhone(teacher.getCellFone())
                .email(teacher.getEmail())
                .typeUser(teacher.getTypeUser())
                .creatAt(teacher.getCreatAt())
                .uptdateAt(teacher.getUptdateAt())
                .status(teacher.isStatus())
                .dateOfBirth(teacher.getDateOfBirth())
                .surnameTeacher(teacher.getSurnameTeacher())
                .userAccess(teacher.getUserAccess())
                .reasonsForBlocking(teacher.getReasonsForBlocking())
                .reasonsForBlockingDescription(teacher.getReasonsForBlockingDescription())
                .build();
    }

    private TeacherSubjectResponse buildStudentInstitutionResponse(TeacherSubject teacherSubject,
                                                                   TeacherResponse responseTeacher,
                                                                   InstitutionResponse responseInstitution,
                                                                   SchoolSubjectResponse schoolSubjectResponse) {
        return TeacherSubjectResponse.builder()
                .message("Link created successfully")
                .teacherResponse(responseTeacher)
                .institutionResponses(responseInstitution)
                .schoolSubjectResponse(schoolSubjectResponse)
                .startDate(teacherSubject.getStartDate())
                .uptdateAt(teacherSubject.getUptdateAt())
                .bond(teacherSubject.isBond())
                .build();
    }
}
