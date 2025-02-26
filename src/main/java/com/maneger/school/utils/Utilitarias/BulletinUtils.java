package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Bulletin;
import com.maneger.school.dto.response.BulletinResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BulletinUtils {

    public BulletinResponse convertToResponse(Bulletin bulletin) {
        return new BulletinResponse(
                bulletin.getId(),
                bulletin.getStudentClass().getId(),
                bulletin.getTeacherSubject().getId(),
                bulletin.getNoteValue(),
                bulletin.getCreatedAt(),
                bulletin.getUpdatedAt()
        );
    }

    public List<BulletinResponse> mapBulletinList(List<Bulletin> bulletins) {
        return bulletins.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}
