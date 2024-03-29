package com.hqjang.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    //게시글 저장 여부 테스트
    public void postsaveTest(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("hqjang95@gmail.com")
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    //시간 저장 여부 테스트
    public void BaseTimeEntity_enroll(){
        //given
        LocalDateTime now = LocalDateTime.of(2021,4,1,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("hqjang95@gmail.com")
                .build()
        );
        //when
        List<Posts> all = postsRepository.findAll();
        //then
        Posts post = all.get(0);
        System.out.println(">>>> createDate ="+post.getCreatedDate());
        System.out.println(">>>> Last modified date ="+post.getLastModifiedDate());
        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getLastModifiedDate()).isAfter(now);
    }
}
