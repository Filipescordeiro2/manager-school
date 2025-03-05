package com.maneger.school.utils.Utilitarias;

import com.maneger.school.domain.Bulletin;
import com.maneger.school.dto.request.BulletinRequest;
import com.maneger.school.dto.response.BulletinResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BulletinUtils {

    public BulletinResponse convertToResponse(Bulletin bulletin) {
        return BulletinResponse.builder()
                .id(bulletin.getId())
                .studentClassId(bulletin.getStudentClass().getId())
                .teacherSubjectId(bulletin.getTeacherSubject().getId())
                .noteValue(bulletin.getNoteValue())
                .situation(validNoteValue(bulletin.getNoteValue()))
                .createdAt(bulletin.getCreatedAt())
                .updatedAt(bulletin.getUpdatedAt())
                .build();
    }

    public static String validNoteValue(Double noteValue) {
        if (noteValue >= 0 &&  noteValue <= 6) {
            return "Reprovado";
        } else if (  noteValue > 6 &&  noteValue <= 10) {
            return "Aprovado";
        }
        return "Nota invalida";
    }

    public List<BulletinResponse> mapBulletinList(List<Bulletin> bulletins) {
        return bulletins.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}
