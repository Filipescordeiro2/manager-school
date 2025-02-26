package com.maneger.school.utils.Validation;

import com.maneger.school.dto.request.SchoolGradeRequest;
import com.maneger.school.exception.SchoolGradeException;
import com.maneger.school.repository.SchoolClassRepository;
import com.maneger.school.repository.SchoolGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolGradeValidation {

    private final SchoolGradeRepository repository;
    private final SchoolClassRepository schoolClassRepository;


    public void validDays(SchoolGradeRequest request) {
        var schoolClass = schoolClassRepository.findById(request.getSchoolClassId())
                .orElseThrow(() -> new SchoolGradeException("School class not found"));
        String daysOfSchedules = schoolClass.getDradeOfSchedules();
        String dayOfClassroom = request.getDayOfClassroom();

        if (!daysOfSchedules.contains(dayOfClassroom)) {
            throw new SchoolGradeException("The day " + dayOfClassroom + " is not in the schedule: " + daysOfSchedules);
        }
    }

}
