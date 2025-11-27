package com.ssafy.newstagram.api.article.service;

import com.ssafy.newstagram.api.article.dto.ArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ArticleService articleService;

    @Test
    void test() {
        ArticleDto articleDto = ArticleDto.builder()
                .id(123L).author("testAuthor").title("testTitle").build();
        redisTemplate.opsForValue().set("testArticleDtoKey", articleDto);

        ArticleDto result = (ArticleDto) redisTemplate.opsForValue().get("testArticleDtoKey");

//        ArticleDto result = articleService.getArticleDto(123L);
        assertNotNull(result);
        assertEquals(articleDto.getId(), result.getId());
        assertEquals(articleDto.getAuthor(), result.getAuthor());
        assertEquals(articleDto.getTitle(), result.getTitle());
    }

}
