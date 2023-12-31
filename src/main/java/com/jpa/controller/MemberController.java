package com.jpa.controller;

import com.jpa.domain.MemberDTO;
import com.jpa.entity.Member;
import com.jpa.repository.members.MemberRepository;
import com.jpa.service.members.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members/*")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final HttpServletRequest servletRequest;
    private final HttpSession session;

    @GetMapping("/login")
    public String goToLogin(HttpSession session){
        // 로그인 되어있으면 바로 메인 화면으로 이동
        if(session.getAttribute("id") != null){
            // 포워드 에서 리다이렉트 쓰는 법
            return "redirect:" + "/posts/list";
        }
        return "members/login";
    }

    @PostMapping("/login")
    public RedirectView login(String memberEmail, String memberPassword, RedirectAttributes redirectAttributes){
        Optional<Long> foundId = memberService.login(memberEmail, memberPassword);

        if(foundId.isPresent()){
            foundId.ifPresent(aLong -> session.setAttribute("id", aLong));
            if(session.getAttribute("prev") != null){
                return new RedirectView(session.getAttribute("prev").toString());
            }
            return new RedirectView("/posts/list");
        }

        redirectAttributes.addFlashAttribute("loginStatus",true);
        return new RedirectView("/members/login");
    }


    @GetMapping("/join")
    public void goToJoin(){;}

    @PostMapping
    public RedirectView join(MemberDTO memberDTO, RedirectAttributes redirectAttributes){
        memberService.join(memberDTO);
        redirectAttributes.addFlashAttribute("join", true);
        return new RedirectView("/members/login");
    }

    @PostMapping("/checkId")
    @ResponseBody
    public boolean checkId(String memberEmail){
        Optional<Long> foundId = memberService.checkId(memberEmail);
        if(foundId.isPresent()){
            return false;
        }
        return true;
    }

    @GetMapping("/logout")
    public RedirectView logout(){
        session.invalidate();
        return new RedirectView("/members/login");
    }

}
