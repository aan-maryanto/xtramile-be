package com.example.xtramile.controller;

import com.example.xtramile.dto.BaseResponse;
import com.example.xtramile.dto.PageableRequest;
import com.example.xtramile.dto.request.FilterPatientList;
import com.example.xtramile.dto.request.SavePatient;
import com.example.xtramile.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/patient")
public class PatientController extends BaseController {

    private final PatientService patientService;

    @GetMapping(value = "/list")
    public BaseResponse<Object> getListPatient(
            @ModelAttribute @Valid FilterPatientList filterPatientList,
            @ModelAttribute @Valid PageableRequest pageableRequest
            ) {
        return toResponse(patientService.getAllPatient(filterPatientList, pageableRequest));
    }

    @PostMapping(value = "/add")
    public BaseResponse<Object> addPatient(@RequestBody SavePatient savePatient) {
        return toResponse(patientService.addPatient(savePatient));
    }

    @DeleteMapping(value = "/delete")
    public BaseResponse<Object> deletePatient(@RequestParam("pid") String pid) {
        return toResponse(patientService.deletePatientById(pid));
    }

    @PostMapping(value = "/update")
    public BaseResponse<Object> updatePatientById(@RequestBody SavePatient savePatient) {
        return toResponse(patientService.updatePatientById(savePatient));
    }

}
