package com.baby.lions.openai.entity;

import com.baby.lions.login.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    public Schedule(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
