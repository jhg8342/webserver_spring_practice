package com.example.webserver.repository;

import com.example.webserver.entity.Article;
import com.example.webserver.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRespositoryTest {

    @Autowired CommentRespository commentRespository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {

        /* CASE 1 : 4번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터 조회
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRespository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(4L,"444@naver.com","4444");
            Comment a = new Comment(1L,article,"KIM", "HI");
            Comment b = new Comment(2L,article,"JO", "Hello");
            Comment c = new Comment(3L,article,"PARK", "Good fight");
            List<Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(),comments.toString(),"4번 글의 모든 댓글을 출력");
        }

        /* CASE 2 : 1번 게시글의 모든 댓글 조회*/
        {
            // 입력 데이터 조회
            Long articleId = 1L;
            // 실제 수행
            List<Comment> comments = commentRespository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(1L,"111@naver.com","1111");
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(),comments.toString(),"1번 글의 모든 댓글을 출력");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글을 조회")
    void findByNickname() {

        /* CASE 1 : "park"의 모든 댓글 조회*/
        {
            // 입력 데이터 조회
            String nickname = "PARK";
            // 실제 수행
            List<Comment> comments = commentRespository.findByNickname(nickname);
            // 예상하기
            Comment a = new Comment(3L,new Article(4L,"444@naver.com","4444"),nickname, "Good fight");
            Comment b = new Comment(6L,new Article(5L,"555@naver.com","5555"),nickname, "qqq");
            Comment c = new Comment(9L,new Article(6L,"666@naver.com","6666"),nickname, "Good night");
            List<Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(),comments.toString(),"park의 모든 댓글을 출력");
        }

    }
}