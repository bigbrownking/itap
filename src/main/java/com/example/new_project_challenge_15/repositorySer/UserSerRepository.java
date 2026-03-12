package com.example.new_project_challenge_15.repositorySer;

import com.example.new_project_challenge_15.modelsSer.UserSer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserSerRepository extends JpaRepository<UserSer, Long> {

}
