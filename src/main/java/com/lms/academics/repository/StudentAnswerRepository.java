package com.lms.academics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.academics.model.StudentAnswer;

public interface StudentAnswerRepository
        extends JpaRepository<StudentAnswer, Long> {

    // All answers of a student for an exam
    List<StudentAnswer> findByExam_ExamIdAndStudentId(
            Long examId,
            Long studentId
    );

    // Answers for a specific question
    List<StudentAnswer> findByExam_ExamIdAndQuestion_QuestionId(
            Long examId,
            Long questionId
    );
}
