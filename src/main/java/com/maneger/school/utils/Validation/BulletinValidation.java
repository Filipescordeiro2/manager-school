package com.maneger.school.utils.Validation;

import com.maneger.school.dto.request.BulletinRequest;
import com.maneger.school.exception.BulletinException;
import com.maneger.school.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BulletinValidation {

    private final BulletinRepository bulletinRepository;

    public void validateBulletinRequest(BulletinRequest request) {
        if (request.getStudentClassId() == null) {
            throw new BulletinException("StudentClass ID is required");
        }
        if (request.getTeacherSubjectId() == null) {
            throw new BulletinException("TeacherSubject ID is required");
        }
        if (request.getNoteValue() == null || request.getNoteValue() < 0 || request.getNoteValue() > 10) {
            throw new BulletinException("Grade must be between 0 and 10");
        }
    }

    public void validateDuplicateBulletin(BulletinRequest request) {
        boolean exists = bulletinRepository.existsByStudentClassIdAndTeacherSubjectId(
                request.getStudentClassId(), request.getTeacherSubjectId()
        );
        if (exists) {
            throw new BulletinException("A bulletin for this student in this subject already exists.");
        }
    }
}
