package com.example.webserver.service;

import com.example.webserver.dto.CommentDto;
import com.example.webserver.entity.Article;
import com.example.webserver.entity.Comment;
import com.example.webserver.repository.ArticleRepository;
import com.example.webserver.repository.CommentRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRespository commentRespository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
/*
        //조회 : 댓글목록
        List<Comment> comments = commentRespository.findByArticleId(articleId);
        //변환 : 엔티티 -> DTO 형태로 반환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
*/

/*        for(int i = 0; i < comments.size(); i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/



        //반환
        return commentRespository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional // db를 건드리기때문에 transaction 처리해야함
    public CommentDto create(Long articleId, CommentDto dto) {

        // 게시글 조회 및 예외발생

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 대상 게시글이 없습니다"));

        // 댓글 엔티티 생성

        Comment comment =  Comment.createComment(dto,article);

        // 댓글 엔티티를 DB로 저장

        Comment created = commentRespository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 댓글 조회 및 예외발생
        Comment target = commentRespository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패, 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRespository.save(target);

        // 댓글 엔티티 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {

        //댓글 조회(및 예외발생)
        Comment target = commentRespository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패, 대상이 없습니다."));
        //댓글 DB에서 삭제
        commentRespository.delete(target);

        //삭제 댓글 DTO로 변환
        return CommentDto.createCommentDto(target);
    }
}
