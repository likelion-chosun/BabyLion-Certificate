package com.baby.lions.login.config;


import com.baby.lions.login.entity.UserRole;
import com.baby.lions.login.service.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig   {

    private final PrincipalOauth2UserService principalOauth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/gpt/**").authenticated()
                .requestMatchers("/schedule/**").authenticated()
                .requestMatchers("/calendar/**").authenticated()
                .requestMatchers("/admin").hasAuthority(UserRole.ADMIN.name())
                .anyRequest().permitAll()
        );
        http.formLogin(form -> form
                .usernameParameter("loginId")
                .passwordParameter("password")
                .loginPage("/security-login/login")
                .defaultSuccessUrl("/security-login/info")
                .failureUrl("/security-login"));
        http.logout(logout -> logout
                .logoutUrl("/security-login/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.oauth2Login(kakao -> kakao
                .loginProcessingUrl("/spring-login")
                .defaultSuccessUrl("/security-login/gpt")
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(principalOauth2UserService)));

        return http.build();
    }

}
