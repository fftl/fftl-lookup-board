package fftl.lookupBoard.controller;

import fftl.lookupBoard.advice.AdviceController;
import fftl.lookupBoard.service.BoardService;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    BoardService boardService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    public void setMvc(MockMvc mvc){
        this.mvc = MockMvcBuilders.standaloneSetup(new BoardController(boardService))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .setControllerAdvice(AdviceController.class)
            .build();
    }

    @DisplayName("저장하기 테스트")
    @Order(1)
    @Test
    void save() throws Exception{
        JSONObject object = new JSONObject()
            .put("title", "제목입니다.")
            .put("content", "내용입니다.")
            .put("regdate", "2022-01-17T11:11:11")
            .put("user_id",1);

        ResultActions action = mvc.perform(
            post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(String.valueOf(object)));

        action
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true));
    }

    @DisplayName("한개 조회하기 테스트")
    @Order(2)
    @Test
    void findById() throws Exception{
        //조회할 때 마다 조회수가 올라가는 것도 확인 완료
        ResultActions action1 = mvc.perform(
            get("/board/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        action1
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.searchCnt").value(1))
        ;

        ResultActions action2 = mvc.perform(
            get("/board/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        action2
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.searchCnt").value(2));
    }

    @DisplayName("모두 조회하기 테스트")
    @Order(3)
    @Test
    void findAll() throws Exception{

        ResultActions action = mvc.perform(
            get("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        action
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.length()").value(6));
    }

    @DisplayName("제목으로 조회하기 테스트")
    @Order(4)
    @Test
    void findByTitle() throws Exception{

        ResultActions action = mvc.perform(
            get("/board/title/치킨")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        action
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.length()").value(2));
    }

    @DisplayName("내용으로 조회하기 테스트")
    @Order(5)
    @Test
    void findByContent() throws Exception{

        ResultActions action = mvc.perform(
            get("/board/content/또먹고싶네")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        action
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.length()").value(3));
    }

    @DisplayName("제목이나 내용으로 조회하기 테스트")
    @Order(6)
    @Test
    void findByTitleContent() throws Exception{

        ResultActions action = mvc.perform(
            get("/board/titleContent/치킨")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        action
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.length()").value(3));
    }

    @DisplayName("유저이름으로 테스트")
    @Order(7)
    @Test
    void findByUsername() throws Exception{

        ResultActions action = mvc.perform(
            get("/board/username/홍길동")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        action
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.length()").value(2));
    }

    @DisplayName("날짜범위로 조회 테스트")
    @Order(8)
    @Test
    void findByDates() throws Exception{

        ResultActions action = mvc.perform(
            get("/board/regdate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("startDate", "2022-01-25T01:11:11")
                .param("endDate", "2022-01-31T11:11:11"));

        action
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").exists());
    }
}