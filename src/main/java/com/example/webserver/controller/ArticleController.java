package com.example.webserver.controller;

import com.example.webserver.dto.ArticleForm;
import com.example.webserver.dto.CommentDto;
import com.example.webserver.entity.Article;
import com.example.webserver.repository.ArticleRepository;
import com.example.webserver.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString());

        // 1. DTO -> Entity
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        // 2. Repository에게 Entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "redirect:/articles/"+ saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        // 1: id로 데이트를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2: 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);
        // 3: 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1: 모든 articles을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // 2: 가져온 article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
        // 3: view 설정
        return "articles/index"; //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        // 수정할 데이터 가져오기
        Article articleEntity =articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("article",articleEntity);

        // view 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article articleEntity =form.toEntity();
        log.info(articleEntity.toString());
        // 2. 엔티티를 DB로 저장
        // 2-1. DB에서 기존 데이터를 가져온다
        Optional<Article> target = articleRepository.findById(articleEntity.getId());
        // 2-2. 기존 데이터에 값을 수정한다.
        if(target.isPresent()) {
            articleRepository.save(articleEntity);
        }
        // 3. 수정 결과 페이지로 리다이렉트

        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청 확인");

       Article target =  articleRepository.findById(id).orElse(null);
       log.info(target.toString());

       if(target != null) {
           articleRepository.delete(target);
           rttr.addFlashAttribute("msg","삭제완료");
       }

        return "redirect:/articles";
    }

}
