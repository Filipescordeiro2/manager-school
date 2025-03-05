package com.maneger.school;

import com.maneger.school.domain.Bulletin;
import com.maneger.school.dto.request.BulletinRequest;
import com.maneger.school.dto.response.BulletinResponse;
import com.maneger.school.dto.response.BulletinStudentResponse;
import com.maneger.school.exception.BulletinException;
import com.maneger.school.repository.BulletinRepository;
import com.maneger.school.service.BulletinService;
import com.maneger.school.utils.Utilitarias.BulletinUtils;
import com.maneger.school.utils.Validation.BulletinValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BulletinServiceTest {

    @Mock
    private BulletinValidation validation;

    @Mock
    private BulletinRepository bulletinRepository;

    @Mock
    private BulletinUtils bulletinUtils;

    @InjectMocks
    private BulletinService bulletinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBulletin_Success() {
        BulletinRequest request = new BulletinRequest();
        request.setStudentClassId(UUID.randomUUID());
        request.setTeacherSubjectId(UUID.randomUUID());
        request.setNoteValue(95.0); // Initialize noteValue with a valid value

        Bulletin bulletin = new Bulletin(request);
        BulletinResponse response = new BulletinResponse(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                95.0,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        doNothing().when(validation).validateBulletinRequest(request);
        doNothing().when(validation).validateDuplicateBulletin(request);
        when(bulletinRepository.save(any(Bulletin.class))).thenReturn(bulletin);
        when(bulletinUtils.convertToResponse(bulletin)).thenReturn(response);

        BulletinResponse result = bulletinService.createBulletin(request);

        System.out.println("Expected: " + response);
        System.out.println("Actual: " + result);

        assertNotNull(result);
        assertEquals(response, result);
        verify(validation).validateBulletinRequest(request);
        verify(validation).validateDuplicateBulletin(request);
        verify(bulletinRepository).save(any(Bulletin.class));
        verify(bulletinUtils).convertToResponse(bulletin);
    }

    @Test
    void testCreateBulletin_Exception() {
        BulletinRequest request = new BulletinRequest();

        doThrow(new RuntimeException("Validation error")).when(validation).validateBulletinRequest(request);

        assertThrows(BulletinException.class, () -> bulletinService.createBulletin(request));
        verify(validation).validateBulletinRequest(request);
        verify(validation, never()).validateDuplicateBulletin(request);
        verify(bulletinRepository, never()).save(any(Bulletin.class));
        verify(bulletinUtils, never()).convertToResponse(any(Bulletin.class));
    }

    @Test
    void testGetBulletinsByStudentCpf() {
        String studentCpf = "12345678900";
        List<BulletinStudentResponse> responses = List.of(
                new BulletinStudentResponse(
                        UUID.randomUUID(),
                        "John Doe",
                        "Jane Smith",
                        "Mathematics",
                        "Fall",
                        "2023",
                        95.0,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );

        when(bulletinRepository.findBulletinsByStudentCpf(studentCpf)).thenReturn(responses);

        List<BulletinStudentResponse> result = bulletinService.getBulletinsByStudentCpf(studentCpf);

        System.out.println("Expected: " + responses);
        System.out.println("Actual: " + result);

        assertNotNull(result);
        assertEquals(responses, result);
        verify(bulletinRepository).findBulletinsByStudentCpf(studentCpf);
    }
}