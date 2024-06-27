package com.busanit501.shoesproject.controller.lsjcontroller;

import com.busanit501.shoesproject.domain.lsjdomain.ShoesRole;
import com.busanit501.shoesproject.dto.lsjdto.ShoesJoinDTO;
import com.busanit501.shoesproject.service.lsjservice.ShoesService2;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class lsjShoesController {

    private final ShoesService2 shoesService2;

    @GetMapping("/signin")
    public void loginGet(String error, String logout,
                         RedirectAttributes redirectAttributes) {
        log.info("loginGet===================");
        log.info("logoutTest ===================" + logout);

        if (logout != null) {
            log.info("logoutTest user ====================");
        }
        if (error != null) {
            log.info("logoutTest error ====================" + error);
            redirectAttributes.addFlashAttribute(
                    "error", "");
        }

    }
    // 로그인 폼은 있지만, 로그인을 처리하는 로직처리가 없어요?
    // 누가 처리? 스프링의 시큐리티가 알아서 처리함.
    // 자동로그인, remember-me 이름으로 서버에 와요.

    //회원수정 폼
    @GetMapping("/update")
    public void updateGet(@AuthenticationPrincipal UserDetails user, Model model) {
        log.info("updateGet====================");
        log.info("updateGet : " + user);
        // 로그인 여부에 따라, 로그 아웃 표시하기.
        model.addAttribute("user", user);
    }


    // 회원 수정 로직 처리
    @PostMapping("/update")
    public String updatePost(ShoesJoinDTO shoesJoinDTO,
                             RedirectAttributes redirectAttributes) {
        log.info("joinPost====================");
        log.info("shoesJoinDTO = " + shoesJoinDTO);
//        log.info("memberJoinDTO = 2 profileImage " + profileImage.getOriginalFilename());
        // 회원 가입 로직 처리 없음.
        try {
            shoesService2.join(shoesJoinDTO);
        } catch (ShoesService2.IdExistException e) {
            redirectAttributes.addFlashAttribute("error", "아이디 중복입니다");
            return "redirect:/member/signup";
        }
        // 회원 가입 성공시
        redirectAttributes.addFlashAttribute("result","회원가입 성공");
        return "redirect:/member/signin";
    }


    //회원가입 폼
    @GetMapping("/signup")
    public void joinGet() {
        log.info("joinGet====================");
    }

    // 회원 가입 로직 처리
    @PostMapping("/signup")
    public String joinPost(ShoesJoinDTO shoesJoinDTO,
                           RedirectAttributes redirectAttributes) {
        log.info("joinPost====================");
        log.info("shoesJoinDTO = " + shoesJoinDTO);


        try {
            shoesService2.join(shoesJoinDTO);
        } catch (ShoesService2.IdExistException e) {
            redirectAttributes.addFlashAttribute("error", "아이디 중복입니다");
            return "redirect:/member/signup";
        }
        // 회원 가입 성공시
        redirectAttributes.addFlashAttribute("result","회원가입 성공");
        return "redirect:/member/signin";
    }

}