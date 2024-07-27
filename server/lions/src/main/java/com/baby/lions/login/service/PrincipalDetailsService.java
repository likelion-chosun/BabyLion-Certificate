package com.baby.lions.login.service;

import com.baby.lions.login.config.PrincipalDetails;
import com.baby.lions.login.entity.User;
import com.baby.lions.login.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당 유저를 찾을수 없습니다."));
        return new PrincipalDetails(user);
    }


}
