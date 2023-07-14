package com.jpa.controller;

import com.jpa.entity.Post;
import com.jpa.entity.dto.Pagination;
import com.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/*")
@Slf4j
public class PostController {

    private final PostRepository postRepository;


    @GetMapping("/list")
    public void goToList(Model model, Pagination pagination) {
        // Pagination + 기존 page랑 합쳐서 사용
        pagination.setRowCount(10);
        pagination.setTotal((int)postRepository.count());
        pagination.progress();
        PageRequest pageRequest = PageRequest.of(pagination.getPage(), 10, Sort.by(Sort.Direction.DESC, "id"));
        JSONArray jsonArray = new JSONArray();
        Page<Post> fountPages = postRepository.findAll(pageRequest);

        fountPages.getContent().stream().map(post -> new JSONObject(post)).forEach(jsonArray::put);
        model.addAttribute("posts", jsonArray);
        model.addAttribute("pagination", pagination);

    }


    @GetMapping("/write")
    public void goToWrite(){;}

    @PostMapping("/write")
    public RedirectView write(Post post){
        postRepository.save(post);
        return new RedirectView("/posts/list");
    }

    @GetMapping("/detail/{id}")
    public String goToDetail(@PathVariable Long id, Model model){
        Optional<Post> foundPost = postRepository.findById(id);
        foundPost.ifPresent(post -> model.addAttribute("post", post));
        return "/posts/detail";
    }
    @GetMapping("/modify/{id}")
    public String goToModify(@PathVariable Long id, Model model){
        Optional<Post> foundPost = postRepository.findById(id);
        foundPost.ifPresent(post -> model.addAttribute("post", post));
        return "/posts/modify";
    }

    @Transactional
    @PostMapping("/modify/{id}")
    public RedirectView modify(@PathVariable Long id, Post post){
        postRepository.save(post);
        return new RedirectView("/posts/detail/"+ id);
    }

    @GetMapping("/remove/{id}")
    @Transactional
    public RedirectView remove(@PathVariable Long id){
        postRepository.deleteById(id);
        return new RedirectView("/posts/list");
    }

}
