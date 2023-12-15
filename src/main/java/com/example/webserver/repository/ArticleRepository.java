package com.example.webserver.repository;

import com.example.webserver.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Long> {  // 관리대상 entity, entity 타입값 ,

    @Override
    ArrayList<Article> findAll();

}
