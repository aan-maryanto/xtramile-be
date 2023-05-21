package com.example.xtramile.repository;

import com.example.xtramile.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface PatientRepository extends JpaRepository<PatientEntity, String>, JpaSpecificationExecutor<PatientEntity> {
}
