package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.StudentClass;
import com.maneger.school.dto.response.SchoolClassResponse;
import com.maneger.school.dto.response.StudentClassResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentClassUtils {

    public StudentClassResponse ConvertStudentClassResponse(StudentClass studentClass) {
        return StudentClassResponse
                .builder()
                .message("Student registered in class")
                .createAt(LocalDateTime.now())
                .build();
    }

    public List<SchoolClassResponse> mapStudentClasses(List<StudentClass> studentClasses) {
        return studentClasses.stream()
                .filter(StudentClass ::isRegistration)
                .map(si -> {
                    var classrom = si.getSchoolClass();
                    return SchoolClassResponse.builder()
                            .nameClass(classrom.getNameClass())
                            .dradeOfSchedules(classrom.getDradeOfSchedules())
                            .institutionCnpj(classrom.getInstitution().getCnpj())
                            .period(classrom.getPeriod())
                            .startDate(classrom.getStartTime())
                            .endDate(classrom.getEndTime())
                            .status(classrom.isStatus())
                            .createAt(classrom.getCreateAt())
                            .updateAt(classrom.getUpdateAt())
                            .build();
                })
                .toList();
    }
}
