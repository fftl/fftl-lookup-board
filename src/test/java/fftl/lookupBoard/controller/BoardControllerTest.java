package fftl.lookupBoard.controller;

import fftl.lookupBoard.service.BoardService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
            .build();
    }

    @Test
    void save() throws Exception{
        JSONObject object = new JSONObject()
            .put("title", "제목입니다.")
            .put("content", "내용입니다.")
            .put("regdate", "2022-01-17 11:11:11")
            .put("user_id","1");

        ResultActions action = mvc.perform(
            post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(String.valueOf(object)));

        action
            .andDo(print());
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("success").value(true));


    }
}