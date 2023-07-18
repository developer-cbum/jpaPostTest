package com.jpa.controller;

import com.jpa.domain.PostDTO;
import com.jpa.entity.Member;
import com.jpa.entity.Post;
import com.jpa.domain.Pagination;
import com.jpa.exception.NoPostException;
import com.jpa.repository.members.MemberRepository;
import com.jpa.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/*")
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;

    @GetMapping("/list")
    public void goToList(Model model,@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Pagination pagination) {
        JSONArray jsonArray= new JSONArray();
//                 Pagination + 기존 page랑 합쳐서 사용
        pagination.setRowCount(10);
        pagination.setTotal((int)postService.getTotal());
        pagination.progress();

        Page<Post> posts = postService.getList(pageable);
        posts.getContent().forEach(post -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", post.getId());
            jsonObject.put("postTitle", post.getPostTitle());
            jsonObject.put("postContent", post.getPostContent());
            jsonObject.put("memberName", post.getMember().getMemberName());
            jsonObject.put("createdDate", post.getCreatedDate());
            jsonObject.put("memberId", post.getMember().getId()); // 필요한 멤버 정보만 담음
            jsonArray.put(jsonObject);
        });

        model.addAttribute("posts", jsonArray);
        model.addAttribute("pagination", pagination);

    }



    @GetMapping("/write")
    public void goToWrite(){;}

    @PostMapping("/write")
    public RedirectView write(PostDTO postDTO, HttpSession session){
        if(session.getAttribute("id") != null){
            Optional<Member> foundMember = memberRepository.findById((Long) (session.getAttribute("id")));
            if(foundMember.isPresent()){
                postDTO.setMember(foundMember.get());
            }
            postService.register(postDTO);
        };
        return new RedirectView("/posts/list");
    }

    @GetMapping(value = {"/detail/{id}","/modify/{id}"})
    public String goToDetail(@PathVariable Long id, Model model, HttpServletRequest httpServletRequest){
        Post post = postService.findById(id).orElseThrow(() -> {
            throw new NoPostException("게시글 없음");
        });
         model.addAttribute("post", post);
        return "/posts/" + httpServletRequest.getRequestURI().split("/")[2];
    }


    @PostMapping("/modify/{id}")
    public RedirectView modify(@PathVariable Long id, PostDTO postDTO){
        postService.modify(postDTO);
        return new RedirectView("/posts/detail/"+ id);
    }

    @GetMapping("/remove/{id}")
    public RedirectView remove(@PathVariable Long id){
        postService.remove(id);
        return new RedirectView("/posts/list");
    }

}
