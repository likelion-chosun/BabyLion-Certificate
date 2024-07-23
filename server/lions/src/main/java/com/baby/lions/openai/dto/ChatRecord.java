package com.baby.lions.openai.dto;


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
