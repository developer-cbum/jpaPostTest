package com.jpa.controller;

import com.jpa.entity.Member;
import com.jpa.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members/*")
public class MemberController {
    private final MemberRepository memberRepository;


    @GetMapping("/login")
    public void goToLogin(){}

    @PostMapping("/login")
    public RedirectView login(String memberEmail, String memberPassword, HttpSession session, RedirectAttributes redirectAttributes){
        Optional<Long> foundId = memberRepository.findIdByMemberEmailAndMemberPassword(memberEmail, memberPassword);

        if(foundId.isPresent()){
            foundId.ifPresent(aLong -> session.setAttribute("id", aLong));
            return new RedirectView("/posts/list");
        }

        redirectAttributes.addFlashAttribute("loginStatus",true);
        return new RedirectView("/members/login");
    }


    @GetMapping("/join")
    public void goToJoin(){;}

    @PostMapping
    public RedirectView join(Member member, RedirectAttributes redirectAttributes){
        memberRepository.save(member);
        redirectAttributes.addFlashAttribute("join", true);
        return new RedirectView("/members/login");
    }

    @PostMapping("members/checkId")
    @ResponseBody
    public boolean checkId(String memberEmail){
        Optional<Long> foundId = memberRepository.checkId(memberEmail);
        if(foundId.isPresent()){
            return false;
        }
        return true;
    }

    @GetMapping("members/logout")
    public RedirectView logout(HttpSession session){
        session.invalidate();
        return new RedirectView("/members/login");
    }

}
