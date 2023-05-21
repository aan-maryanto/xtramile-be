package com.example.xtramile.service;

import com.example.xtramile.common.constant.ConstantUtil;
import com.example.xtramile.common.helper.PageableHelper;
import com.example.xtramile.dto.PageableRequest;
import com.example.xtramile.dto.PatientDto;
import com.example.xtramile.dto.request.FilterPatientList;
import com.example.xtramile.dto.request.SavePatient;
import com.example.xtramile.dto.response.PatientListResponse;
import com.example.xtramile.entity.PatientEntity;
import com.example.xtramile.enums.ResponseEnum;
import com.example.xtramile.enums.Status;
import com.example.xtramile.exception.BussinessException;
import com.example.xtramile.repository.PatientRepository;
import com.example.xtramile.repository.specification.PatientSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientListResponse getAllPatient(FilterPatientList filterPatientList, PageableRequest pageableRequest) {
        Page<PatientEntity> getPatient = getPatientList(filterPatientList, pageableRequest);
        List<PatientDto> patientDtoList = getPatientDTO(getPatient);
        return PatientListResponse.builder()
                .list(patientDtoList)
                .page(PageableHelper.getResponse(getPatient))
                .build();
    }

    public String addPatient(SavePatient request) {
        try{
            String pid = generatePid();
            PatientEntity patient = PatientEntity.builder()
                    .id(pid)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .dateOfBirth(request.getDob())
                    .gender(request.getGender())
                    .australianAddress(request.getAustralianAddress())
                    .phone(request.getPhoneNo())
                    .createdBy("System")
                    .createdDate(ZonedDateTime.now())
                    .status(Status.ACTIVE)
                    .isDeleted(Boolean.FALSE)
                    .build();

            patientRepository.save(patient);
            return "Success";

        } catch (Exception ex) {
            log.error("Error caused : {} ", ex.getMessage());
            throw new BussinessException(ResponseEnum.INTERNAL_ERROR, ex.getLocalizedMessage());
        }
    }

    private String generatePid() {
        long time = Instant.now().toEpochMilli();
        return "PID-"+time;
    }

    private List<PatientDto> getPatientDTO(Page<PatientEntity> patients) {
        return Optional.ofNullable(patients)
                .map(Slice::getContent)
                .filter(CollectionUtils::isNotEmpty)
                .orElse(Collections.emptyList())
                .stream()
                .map(patient -> PatientDto.builder()
                        .pid(patient.getId())
                        .firstName(patient.getFirstName())
                        .lastName(patient.getLastName())
                        .dob(patient.getDateOfBirth())
                        .gender(patient.getGender())
                        .australianAddress(patient.getAustralianAddress())
                        .phoneNo(patient.getPhone())
                        .build()
                ).toList();
    }

    public String deletePatientById(String id) {
        patientRepository.findById(id).ifPresent(patientRepository::delete);
        return "Success";
    }

    public String updatePatientById(SavePatient request) {
        patientRepository.findById(request.getPid())
                .ifPresentOrElse(patient -> {
                    patient.setFirstName(request.getFirstName());
                    patient.setLastName(request.getLastName());
                    patient.setDateOfBirth(request.getDob());
                    patient.setGender(request.getGender());
                    patient.setAustralianAddress(request.getAustralianAddress());
                    patient.setPhone(request.getPhoneNo());
                    patientRepository.save(patient);
                }, () -> {
                    throw new BussinessException(ResponseEnum.PATIENT_NOT_FOUND, "Patient not found");
                });
        return "Success";
    }

    private Page<PatientEntity> getPatientList(FilterPatientList filterPatientList, PageableRequest pageableRequest) {
        PageRequest pageRequest = PageableHelper.getRequest(pageableRequest);
        FilterPatientList patientRequest = Optional.ofNullable(filterPatientList).orElse(new FilterPatientList());
        Specification<PatientEntity> patientEntitySpecification = PatientSpecification.filter(patientRequest);
        return patientRepository.findAll(patientEntitySpecification, pageRequest);

    }

}
