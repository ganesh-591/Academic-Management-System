package com.lms.academics.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batch_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_master_id")
    private Long batchMasterId;

    // ONE COURSE → ONE BATCH MASTER
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "total_batches", nullable = false)
    private Integer totalBatches;

    @Column(name = "running_batches", nullable = false)
    private Integer runningBatches;

    @Column(name = "upcoming_batches", nullable = false)
    private Integer upcomingBatches;

    @Column(name = "completed_batches", nullable = false)
    private Integer completedBatches;

    @Column(name = "total_students", nullable = false)
    private Integer totalStudents;

    @Column(name = "parallel_batches", nullable = false)
    private Integer parallelBatches;

    @Column(name = "required_trainers", nullable = false)
    private Integer requiredTrainers;

    @Column(nullable = false, length = 50)
    private String status; // ACTIVE / CLOSED

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();

        // sensible defaults
        if (totalBatches == null) totalBatches = 0;
        if (runningBatches == null) runningBatches = 0;
        if (upcomingBatches == null) upcomingBatches = 0;
        if (completedBatches == null) completedBatches = 0;
        if (totalStudents == null) totalStudents = 0;
        if (parallelBatches == null) parallelBatches = 0;
        if (requiredTrainers == null) requiredTrainers = 0;
        if (status == null) status = "ACTIVE";
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
