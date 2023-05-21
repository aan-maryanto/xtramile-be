package com.example.xtramile.controller;

import com.example.xtramile.dto.BaseResponse;
import com.example.xtramile.dto.PageableRequest;
import com.example.xtramile.dto.PageableResponse;
import com.example.xtramile.dto.request.FilterPatientList;
import com.example.xtramile.dto.request.SavePatient;
import com.example.xtramile.dto.response.PatientListResponse;
import com.example.xtramile.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Patient Controller Test")
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;



    @BeforeEach
    void setUp() {

    }

    @Test
    void getListPatient() {
        when(patientService.getAllPatient(any(), any())).thenReturn(getPatientListResp());
        BaseResponse<Object> patients = patientController.getListPatient(new FilterPatientList(), new PageableRequest());
        assertEquals("200", patients.getStatus());
    }

    @Test
    void addPatient() {
        when(patientService.addPatient(any())).thenReturn("Success");
        BaseResponse<Object> response = patientController.addPatient(new SavePatient());
        assertEquals("200", response.getStatus());
        assertEquals("Success", response.getData());
    }

    @Test
    void deletePatient() {
        when(patientService.deletePatientById(any())).thenReturn("Success");
        BaseResponse<Object> response = patientController.deletePatient("1");
        assertEquals("200", response.getStatus());
        assertEquals("Success", response.getData());
    }

    @Test
    void updatePatientById() {
        when(patientService.updatePatientById(any(), any())).thenReturn("Success");
        BaseResponse<Object> response = patientController.updatePatientById("1", new SavePatient());
        assertEquals("200", response.getStatus());
        assertEquals("Success", response.getData());
    }

    private PatientListResponse getPatientListResp() {
        return PatientListResponse.builder()
                .list(List.of())
                .page(new PageableResponse())
                .build();
    }


}
