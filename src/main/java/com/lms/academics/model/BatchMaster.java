package com.lms.academics.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "batch_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"course", "students"})
@ToString(exclude = {"course", "students"})
public class BatchMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    @Column(nullable = false)
    private String batchName;

    @Column(nullable = false)
    private Long trainerId; // External Trainer System

    private LocalDate startDate;
    private LocalDate endDate;

    private String days;      // MON-FRI
    private String timeSlot;  // 10AM-12PM
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Course course;

    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BatchDetails> students;
    
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @JsonIgnore   // ⭐⭐ REQUIRED FOR SESSIONS
    private List<AttendanceSession> sessions;
}
