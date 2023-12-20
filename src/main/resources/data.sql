INSERT INTO article(id,title,content) VALUES (1, '111@naver.com', '1111');
INSERT INTO article(id,title,content) VALUES (2, '222@naver.com', '2222');
INSERT INTO article(id,title,content) VALUES (3, '333@naver.com', '3333');

----------------------------------------------------------------------------

INSERT INTO article(id,title,content) VALUES (4, '444@naver.com', '4444');
INSERT INTO article(id,title,content) VALUES (5, '555@naver.com', '5555');
INSERT INTO article(id,title,content) VALUES (6, '666@naver.com', '6666');

----------------------------------------------------------------------------

INSERT INTO comment(id,article_id,nickname,body) VALUES (1,4,'KIM', 'HI');
INSERT INTO comment(id,article_id,nickname,body) VALUES (2,4,'JO', 'Hello');
INSERT INTO comment(id,article_id,nickname,body) VALUES (3,4,'PARK', 'Good fight');

INSERT INTO comment(id,article_id,nickname,body) VALUES (4,5,'KIM', 'asd');
INSERT INTO comment(id,article_id,nickname,body) VALUES (5,5,'JO', 'fff');
INSERT INTO comment(id,article_id,nickname,body) VALUES (6,5,'PARK', 'qqq');

INSERT INTO comment(id,article_id,nickname,body) VALUES (7,6,'KIM', '졸려');
INSERT INTO comment(id,article_id,nickname,body) VALUES (8,6,'JO', '내일봐요');
INSERT INTO comment(id,article_id,nickname,body) VALUES (9,6,'PARK', 'Good night');