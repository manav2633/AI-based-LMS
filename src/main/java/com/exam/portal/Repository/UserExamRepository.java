package com.exam.portal.Repository;

import com.exam.portal.Model.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {

    @Query(value="select * from user_exams u where u.exam_id = ?1 and u.user_id = ?2",nativeQuery = true)
    UserExam findUserExamByUser(Long exam_id,Long user_id);

    @Query(value = "select count(u) from user_exams u where u.exam_id = ?1 and (u.status = 1 or u.status = 2)", nativeQuery=true)
    int findPresentUsersCount(Long exam_id);

    @Query(value = "select count(u) from user_exams u where u.exam_id = ?1 and u.status = 0", nativeQuery=true)
    int findAbsentUsersCount(Long exam_id);
}
