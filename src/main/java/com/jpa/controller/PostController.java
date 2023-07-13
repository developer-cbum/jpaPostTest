package com.jpa.controller;

import com.jpa.entity.Post;
import com.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/*")
public class PostController {

    private final PostRepository postRepository;


    @GetMapping("/list")
    public void goToList(Model model, Integer page) {
        if(page == null){ page=0;}
        JSONArray jsonArray = new JSONArray();
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> fountPages = postRepository.findAll(pageRequest);
        fountPages.get().map(post -> new JSONObject(post)).forEach(jsonArray::put);
        model.addAttribute("posts", jsonArray);
        model.
    }

}
