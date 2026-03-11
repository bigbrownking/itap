package com.example.new_project_challenge_15.repositorySer;

import com.example.new_project_challenge_15.modelsSer.UserSer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserSerRepository extends JpaRepository<UserSer, Long> {

}
