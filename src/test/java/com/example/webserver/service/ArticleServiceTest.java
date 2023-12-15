package com.example.webserver.service;

import com.example.webserver.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleServiceTest {

    @Autowired ArticleService articleService; // di

    @Test
    void index() {
        //예상
        Article a = new Article(1L,"111@naver.com","1111");
        Article b = new Article(2L,"222@naver.com","2222");
        Article c = new Article(3L,"333@naver.com","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show() {


    }
}