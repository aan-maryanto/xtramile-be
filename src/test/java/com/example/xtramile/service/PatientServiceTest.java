package com.example.xtramile.service;

import com.example.xtramile.common.helper.PageableHelper;
import com.example.xtramile.dto.PageableRequest;
import com.example.xtramile.dto.request.FilterPatientList;
import com.example.xtramile.dto.request.SavePatient;
import com.example.xtramile.dto.response.PatientListResponse;
import com.example.xtramile.entity.AustralianAddress;
import com.example.xtramile.entity.PatientEntity;
import com.example.xtramile.enums.Gender;
import com.example.xtramile.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Patient Service Test")
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientService patientService;

    @Mock
    PatientRepository patientRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllPatient() {
        List<PatientEntity> patients = List.of(getPatient());
        PageRequest pageRequest = PageableHelper.getRequest(new PageableRequest());
        Page<PatientEntity> actPatient = new PageImpl<>(patients, pageRequest, patients.size());
        when(patientRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(actPatient);

        PatientListResponse response = patientService.getAllPatient(new FilterPatientList(), new PageableRequest());
        Assertions.assertNotNull(response);
    }

    @Test
    void addPatient() {
        when(patientRepository.save(any())).thenReturn(getPatient());
        var result = patientService.addPatient(new SavePatient());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Success", result);
    }

    @Test
    void deletePatientById() {
        when(patientRepository.findById(any())).thenReturn(Optional.ofNullable(getPatient()));
        var result = patientService.deletePatientById("1");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Success", result);
    }

    @Test
    void updatePatientById() {
        when(patientRepository.findById(any())).thenReturn(Optional.ofNullable(getPatient()));
        when(patientRepository.save(any())).thenReturn(getPatient());
        var result = patientService.updatePatientById(new SavePatient());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Success", result);
    }

    private PatientEntity getPatient() {
        return PatientEntity.builder()
                .id("TEST")
                .firstName("TEST")
                .lastName("TEST")
                .dateOfBirth(new Date())
                .gender(Gender.MAN)
                .australianAddress(new AustralianAddress())
                .phone("test")
                .build();

    }
}
