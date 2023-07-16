package com.jpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jpa.entity.Member;
import com.jpa.entity.Post;
import com.jpa.entity.dto.Pagination;
import com.jpa.repository.MemberRepository;
import com.jpa.repository.PostRepository;
import com.jpa.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/*")
@Slf4j
public class PostController implements Serializable {

    private final PostService postService;
//    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


//    @GetMapping("/list")
//    public void goToList(Model model, Pagination pagination) {
//        JSONArray jsonArray= new JSONArray();
//        // Pagination + 기존 page랑 합쳐서 사용
//        pagination.setRowCount(10);
//        pagination.setTotal((int)postRepository.count());
//        pagination.progress();
//        PageRequest pageRequest = PageRequest.of(pagination.getPage(), 10, Sort.by(Sort.Direction.DESC, "id"));
//        Page<Post> fountPages = postRepository.findAll(pageRequest);
//
//
//        fountPages.getContent().forEach(post -> {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("id", post.getId());
//            jsonObject.put("postTitle", post.getPostTitle());
//            jsonObject.put("postContent", post.getPostContent());
//            jsonObject.put("memberName", post.getMember().getMemberName());
//            jsonObject.put("createdDate", post.getCreatedDate());
//            jsonObject.put("memberId", post.getMember().getId()); // 필요한 멤버 정보만 담음
//            jsonArray.put(jsonObject);
//        });
//
//
//        model.addAttribute("posts", jsonArray);
//        model.addAttribute("pagination", pagination);
//
//    }

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



//    @GetMapping("/write")
//    public void goToWrite(){;}
//
//    @PostMapping("/write")
//    public RedirectView write(Post post, HttpSession session){
//        if(session.getAttribute("id") != null){
//            Optional<Member> foundMember = memberRepository.findById((Long) (session.getAttribute("id")));
//            if(foundMember.isPresent()){
//                post.setMember(foundMember.get());
//            }
//            postRepository.save(post);
//        };
//        return new RedirectView("/posts/list");
//    }
//
//    @GetMapping("/detail/{id}")
//    public String goToDetail(@PathVariable Long id, Model model){
//        Optional<Post> foundPost = postRepository.findById(id);
//        foundPost.ifPresent(post -> model.addAttribute("post", post));
//        return "/posts/detail";
//    }
//    @GetMapping("/modify/{id}")
//    public String goToModify(@PathVariable Long id, Model model){
//        Optional<Post> foundPost = postRepository.findById(id);
//        foundPost.ifPresent(post -> model.addAttribute("post", post));
//        return "/posts/modify";
//    }
//
//    @Transactional
//    @PostMapping("/modify/{id}")
//    public RedirectView modify(@PathVariable Long id, Post post){
//        postRepository.save(post);
//        return new RedirectView("/posts/detail/"+ id);
//    }
//
//    @GetMapping("/remove/{id}")
//    @Transactional
//    public RedirectView remove(@PathVariable Long id){
//        postRepository.deleteById(id);
//        return new RedirectView("/posts/list");
//    }

}
