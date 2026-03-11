package com.example.new_project_challenge_15.repository;

import com.example.new_project_challenge_15.models.user_roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepo extends JpaRepository<user_roles, Long> {

}
