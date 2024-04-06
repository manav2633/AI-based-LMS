package com.exam.portal.Repository;

import com.exam.portal.Model.Organiser;
import com.exam.portal.Model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    @Query(value = "SELECT * FROM user_answer u WHERE u.userexam.id = ?1 AND u.questions.id=?2", nativeQuery=true)
    UserAnswer findByUserQuestion(Long user_id,Long question_id);

    @Query(value = "SELECT count(*) FROM user_answer u WHERE u.userexam.id = ?1 AND u.answer_status=1", nativeQuery=true)
    int findCorrectAnswersCount(Long useExamId);

    @Query(value = "SELECT count(*) FROM user_answer u WHERE u.userexam.id = ?1 AND u.answer_status=0", nativeQuery=true)
    int findInCorrectAnswersCount(Long useExamId);

}
