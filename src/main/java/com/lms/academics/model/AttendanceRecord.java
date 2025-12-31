package com.lms.academics.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // ✅ Accepts input JSON, hides from output JSON
    private AttendanceSession attendanceSession;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String status; // PRESENT / ABSENT

    @Column(name = "marked_at", nullable = false, updatable = false)
    private LocalDateTime markedAt;

    @PrePersist
    protected void onCreate() {
        this.markedAt = LocalDateTime.now();
    }
}
