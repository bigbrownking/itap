package com.example.new_project_challenge_15.models;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temp_log")
@NoArgsConstructor
public class TempLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name=  "date")
    private LocalDateTime date;

    public TempLog(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }
}
