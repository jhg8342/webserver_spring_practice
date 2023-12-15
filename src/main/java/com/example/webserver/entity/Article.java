package com.example.webserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // DB가 해당 객체를 인식 가능하게 해줌
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {

    @Getter
    @Id // 대표값 지정 PID느낌?
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1 2 3 알아서 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article){
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }

}
