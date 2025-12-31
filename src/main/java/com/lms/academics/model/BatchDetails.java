package com.lms.academics.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "batch_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"batch"})
@ToString(exclude = {"batch"})
public class BatchDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchDetailId;

    @Column(nullable = false)
    private Long studentId; // External Student System

    private LocalDate joinedDate;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BatchMaster batch;
}
