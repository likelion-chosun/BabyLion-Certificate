package com.baby.lions.login.dto;

import com.baby.lions.login.entity.User;
import com.baby.lions.login.entity.UserRole;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    @NotBlank(message =  "로그인 아이디가 비어있습니다.")
    private String loginId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    //비밀번호 암호화 x
    public User toEntity(){
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .role(UserRole.ADMIN)
                .build();
    }
    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        if(this.nickname.equals("관리자")){
            return User.builder()
                    .loginId(this.loginId)
                    .password(encodedPassword)
                    .nickname(this.nickname)
                    .role(UserRole.ADMIN)
                    .build();
        }
        else {
            return User.builder()
                    .loginId(this.loginId)
                    .password(encodedPassword)
                    .nickname(this.nickname)
                    .role(UserRole.USER)
                    .build();
        }
    }

}
