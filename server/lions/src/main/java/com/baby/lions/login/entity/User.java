package com.baby.lions.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
		@Column(name = "loginId")
    private String loginId;
		@Column(name = "password")
    private String password;
		@Column(name = "nickname")
    private String nickname;
		@Column(name = "provider")
    private String provider;
		@Column(name = "providerId")
    private String providerId;
		@Column(name = "role")
    private UserRole role;
}
