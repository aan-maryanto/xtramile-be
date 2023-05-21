package com.example.xtramile.entity;

import com.example.xtramile.enums.Gender;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Where(clause = "is_deleted = false")
@Table(name = "patient")
public class PatientEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "australian_address", columnDefinition = "json")
    @Type(JsonType.class)
    private AustralianAddress australianAddress;

    @Column(name = "phone")
    private String phone;
}
