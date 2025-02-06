package com.maneger.school.service;

import com.maneger.school.domain.StudentInstitution;
import com.maneger.school.dto.request.StudentInstitutionRequest;
import com.maneger.school.dto.response.StudentInstitutionResponse;
import com.maneger.school.exception.StudantException;
import com.maneger.school.exception.StudentInstitutionException;
import com.maneger.school.repository.StudentInstitutionRepository;
import com.maneger.school.repository.StudentRepository;
import com.maneger.school.utils.Utilitarias.StudentInstitutionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentInstitutionService {

    private final StudentInstitutionRepository studentInstitutionRepository;
    private final StudentRepository studentRepository;
    private final StudentInstitutionUtils studentInstitutionUtils;

    public StudentInstitutionResponse createLink(StudentInstitutionRequest request){
        try{
            validStatus(request.getStudentCPF());
            var link = new StudentInstitution(request);
            var linkCreated = studentInstitutionRepository.save(link);
            return studentInstitutionUtils.convertToStudentResponse(linkCreated);
        }catch (Exception e){
            throw new StudentInstitutionException("Error for link: "+e.getMessage());
        }
    }

    public StudentInstitutionResponse findAllStudentInstitution(String cpf) {
        var student = studentRepository.findById(cpf)
                .orElseThrow(() -> new StudentInstitutionException("Student not found"));

        var studentInstitutions = studentInstitutionRepository.findByStudent(student);

        if (studentInstitutions.isEmpty()) {
            throw new StudentInstitutionException("No institutions found for the student");
        }
        var responseStudent = studentInstitutionUtils.buildStudentResponse(student);

        var institutionResponses = studentInstitutions.stream()
                .map(si -> studentInstitutionUtils.buildInstitutionResponse(si.getInstitution()))
                .toList();

        return StudentInstitutionResponse.builder()
                .studentResponse(responseStudent)
                .institutionResponses(institutionResponses)
                .startDate(studentInstitutions.get(0).getStartDate()) // Pegando a data do primeiro vínculo
                .uptdateAt(studentInstitutions.get(0).getUptdateAt()) // Pegando a última atualização do primeiro vínculo
                .course(studentInstitutions.stream().map(StudentInstitution::getCourse).findFirst().orElse(null)) // Pegando o curso do primeiro vínculo
                .status(studentInstitutions.stream().anyMatch(StudentInstitution::isRegistration)) // Definindo status com base nos registros ativos
                .build();
    }


    private void validStatus(String cpf){
      var student =  studentRepository.findByCpf(cpf).orElseThrow(()->new RuntimeException("Student not Found"));
      if (!student.isStatus()){
          throw new StudantException("Student is Blocked");
      }
    }

}