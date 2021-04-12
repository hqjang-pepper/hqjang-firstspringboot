package com.hqjang.springboot.web;

import com.hqjang.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//final 필드 생성자를 생성하기 위해 해당 어노테이션을 추가해야한다.
@RequiredArgsConstructor
@Controller
//페이지와 관련된 컨트롤러는 모두 IndexController를 이용한다.
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
