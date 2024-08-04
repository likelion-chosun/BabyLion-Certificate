package com.baby.lions.openai.entity;


import com.baby.lions.login.entity.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userMessage;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String botMessage;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}