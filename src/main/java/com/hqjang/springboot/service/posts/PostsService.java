package com.hqjang.springboot.service.posts;

import com.hqjang.springboot.domain.posts.Posts;
import com.hqjang.springboot.domain.posts.PostsRepository;
import com.hqjang.springboot.web.dto.PostsListResponseDto;
import com.hqjang.springboot.web.dto.PostsResponseDto;
import com.hqjang.springboot.web.dto.PostsSaveRequestDto;
import com.hqjang.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //이건 CRUDRepository.java라는 파일에 내장된 save 함수
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //Posts 클래스 메소드
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
