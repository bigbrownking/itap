package com.example.new_project_challenge_15.modelsSer;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users", schema = "oauth")
public class UserSer {

    @Id
    private Long id;

    @Column(name = "iin")
    private String iin;

    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Column(name = "password_expiration_date")
    private LocalDateTime passwordExpDate;

    @Column(name = "status")
    private String status;

    @Column(name = "dossier_access_category")
    private String access;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}


