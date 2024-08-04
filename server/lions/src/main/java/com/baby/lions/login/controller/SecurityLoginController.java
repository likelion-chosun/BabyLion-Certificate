package com.baby.lions.login.controller;

import com.baby.lions.login.dto.JoinRequest;
import com.baby.lions.login.dto.LoginRequest;
import com.baby.lions.login.entity.User;
import com.baby.lions.login.service.UserService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
@RequestMapping("/security-login")
public class SecurityLoginController {
    private final UserService userService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<String> home(Model model, Authentication auth) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");
        ModelAndView modelAndView = new ModelAndView("home");
        if(auth != null) {
            User loginUser = userService.getLoginUserByLoginId(auth.getName());
            if (loginUser != null) {
                model.addAttribute("nickname", loginUser.getNickname());
            }
        }

        return ResponseEntity.ok("home");
    }

    @GetMapping(value = "/join")
    public ModelAndView joinPage(Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        model.addAttribute("joinRequest", new JoinRequest());
        return new ModelAndView("join");
    }

    @PostMapping(value = "/join")
    public ResponseEntity<String> join(@Valid @RequestBody JoinRequest joinRequest, BindingResult bindingResult, Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");
        model.addAttribute("joinRequest",joinRequest);
        // loginId 중복 체크
        if(userService.checkLoginIdDuplicate(joinRequest.getLoginId())) {
            bindingResult.addError(new FieldError("joinRequest", "loginId", "로그인 아이디가 중복됩니다."));
        }
        // 닉네임 중복 체크
        if(userService.checkNicknameDuplicate(joinRequest.getNickname())) {
            bindingResult.addError(new FieldError("joinRequest", "nickname", "닉네임이 중복됩니다."));
        }
        // password와 passwordCheck가 같은지 체크
        if(!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "바밀번호가 일치하지 않습니다."));
        }
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()) {
            mav.setViewName("join");
            return ResponseEntity.ok("join");
        }

        userService.join2(joinRequest);
        mav.setViewName("redirect:/security-login/");
        return ResponseEntity.ok("redirect:/security-login/");
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage(Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        model.addAttribute("loginRequest", new LoginRequest());
        return new ModelAndView("login");
    }

    @GetMapping(value = "/info")
    public ResponseEntity<String> userInfo(Model model, Authentication auth) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        User loginUser = userService.getLoginUserByLoginId(auth.getName());

        if(loginUser == null) {
            return ResponseEntity.ok("fail");
        }

        model.addAttribute("user", loginUser);
        return ResponseEntity.ok("Success login");
    }

    @GetMapping(value = "/admin")
    public ModelAndView adminPage( Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");
        return new ModelAndView("admin");
    }
}
