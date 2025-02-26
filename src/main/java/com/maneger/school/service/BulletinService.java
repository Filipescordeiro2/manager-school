package com.maneger.school.service;

import com.maneger.school.domain.Bulletin;
import com.maneger.school.dto.request.BulletinRequest;
import com.maneger.school.dto.response.BulletinResponse;
import com.maneger.school.dto.response.BulletinStudentResponse;
import com.maneger.school.exception.BulletinException;
import com.maneger.school.repository.BulletinRepository;
import com.maneger.school.repository.StudentClassRepository;
import com.maneger.school.repository.TeacherSubjectRepository;
import com.maneger.school.utils.Utilitarias.BulletinUtils;
import com.maneger.school.utils.Validation.BulletinValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BulletinService {

    private final BulletinValidation validation;
    private final BulletinRepository bulletinRepository;
    private final BulletinUtils bulletinUtils;

    @Transactional
    public BulletinResponse createBulletin(BulletinRequest request) {
        try {
            validation.validateBulletinRequest(request);
            validation.validateDuplicateBulletin(request);
            var bulletin = new Bulletin(request);
            var bulletinCreated = bulletinRepository.save(bulletin);
            return bulletinUtils.convertToResponse(bulletinCreated);
        } catch (Exception e) {
            throw new BulletinException("Error creating bulletin: " + e.getMessage());
        }
    }

    public List<BulletinStudentResponse> getBulletinsByStudentCpf(String studentCpf) {
        return bulletinRepository.findBulletinsByStudentCpf(studentCpf);
    }

}
