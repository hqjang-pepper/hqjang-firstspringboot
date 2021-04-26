package com.hqjang.springboot.web;

import com.hqjang.springboot.service.posts.PostsService;
import com.hqjang.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//final 필드 생성자를 생성하기 위해 해당 어노테이션을 추가해야한다.
@RequiredArgsConstructor
@Controller
//페이지와 관련된 컨트롤러는 모두 IndexController를 이용한다.
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        //model은 서버 템플릿 엔진(mustache)에서 사용할 수 있는 객체를 저장할 수 있음
        //posts로 postsService.findallDesc()의 결과를 전달함
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
