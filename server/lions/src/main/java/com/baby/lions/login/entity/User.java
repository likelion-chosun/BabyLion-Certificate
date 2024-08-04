package com.baby.lions.login.entity;

import com.baby.lions.openai.entity.ChatRecord;
import com.baby.lions.openai.entity.Schedule;
import com.baby.lions.schedulemanage.entity.Calendar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

//    @OneToMany(mappedBy = "user")
//    private List<ChatRecord> chatRecords;
//
//    @OneToMany(mappedBy = "user")
//    private List<Calendar> calendars;
//
//    @OneToMany(mappedBy = "user")
//    private List<Schedule> schedules;
}
