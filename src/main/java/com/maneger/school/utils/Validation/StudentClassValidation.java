package com.maneger.school.utils.Validation;

import com.maneger.school.dto.request.StudentClassRequest;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.exception.InstitutionException;
import com.maneger.school.exception.SchoolClassException;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.StudentClassException;
import com.maneger.school.repository.SchoolClassRepository;
import com.maneger.school.repository.StudentClassRepository;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentClassValidation {

    private final SchoolClassRepository schoolClassRepository;
    private final StudentRepository studentRepository;
    private final StudentClassRepository studentClassRepository;
    private final StudentInstitutionRepository studentInstitutionRepository;

    public void validStudentClass(StudentClassRequest request){
        validSchoolClassName(request.getSchoolClassId());
        validStudent(request.getStudentCPF());
    }

    private void validSchoolClassName(UUID classId){
        var schoolClass = schoolClassRepository.findById(classId);
        if (!schoolClass.isPresent()){
            throw new SchoolClassException("SchoolClass not registered in the system");
        }
    }

    public void validateScheduleOverlap(StudentClassRequest request) {
        var schoolClass = schoolClassRepository.findById(request.getSchoolClassId())
                .orElseThrow(() -> new StudentClassException("SchoolClass not found"));
        List<String> newClassDays = Arrays.asList(schoolClass.getDradeOfSchedules().split(","));
        List<String> existingClassDays = studentClassRepository.findByStudent_Cpf(request.getStudentCPF())
                .stream()
                .flatMap(sc -> Arrays.stream(sc.getSchoolClass().getDradeOfSchedules().split(",")))
                .collect(Collectors.toList());

        for (String day : newClassDays) {
            if (existingClassDays.contains(day)) {
                throw new StudentClassException("Student already has a class on " + day);
            }
        }
    }
    public void validateStudentClassLink(StudentClassRequest request) {
        boolean exists = studentClassRepository.existsByStudent_CpfAndSchoolClass_Id(request.getStudentCPF(), request.getSchoolClassId());
        if (exists) {
            throw new StudentClassException("Student already linked to this class");
        }
    }

    public void validStudentInstitution(StudentClassRequest request) {
        var student = studentRepository.findByCpf(request.getStudentCPF())
                .orElseThrow(() -> new StudantException("Student not registered in the system"));
        var schoolClass = schoolClassRepository.findById(request.getSchoolClassId())
                .orElseThrow(() -> new StudentClassException("SchoolClass not found"));
        var studentInstitutions = studentInstitutionRepository.findByStudent(student);
        if (studentInstitutions.isEmpty()) {
            throw new InstitutionException("Student not registered in any institution");
        }
        boolean isRegisteredInInstitution = studentInstitutions.stream()
                .anyMatch(si -> si.getInstitution().getCnpj().equals(schoolClass.getInstitution().getCnpj()));
        if (!isRegisteredInInstitution) {
            throw new InstitutionException("Student not registered in this institution");
        }
    }

    private void validStudent(String cpf){
        var student = studentRepository.findByCpf(cpf)
                .orElseThrow(() -> new StudantException("Student not registered in the system"));
        if (!student.isStatus()) {
            throw new StudantException("Student is Blocked");
        }
    }
}
