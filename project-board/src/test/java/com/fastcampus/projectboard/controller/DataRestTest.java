package com.fastcampus.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.boot.actuate.health.Health.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// 이 파일은 이 프로젝트의 비즈니스 로직으로 구현한 내용이 아니라 data rest 기능이고,
// 통합 테스트라 무거우며 db 에도 영향을 준다.
// 공부 목적으로도 의미는 있기 때문에 삭제하지는 않고 제외처리해서 전체 테스트 중에 실행되지 않게끔 처리

@Disabled("Spring Data REST 통합테스트는 불필요하므로 제외") // 해당 test class 밑에 있는 모든 unit test들은 실행되지 않는다.
@DisplayName("Data REST - API 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
// @WebMvcTest // 내부적으로 MockMvc 사용 가능하게 준비해준다. 404 Error 발생. 지우고 SpringBootTest 사용
public class DataRestTest {
    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        //Given


        // When&Then
        // get 작성 -> ctrl+space -> 맨위에 한번 클릭 후 opt+enter -> Import statically
        mvc.perform(get("/api/articles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));


    }
}
