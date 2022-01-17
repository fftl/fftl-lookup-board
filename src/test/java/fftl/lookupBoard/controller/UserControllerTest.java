package fftl.lookupBoard.controller;

import fftl.lookupBoard.advice.AdviceController;
import fftl.lookupBoard.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql("/data.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    public void setMvc(MockMvc mvc){
        this.mvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .setControllerAdvice(AdviceController.class)
            .build();
    }

    @Transactional
    @DisplayName("저장하기 테스트")
    @Test
    void save() throws Exception{
        ResultActions actions = mvc.perform(
            post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .param("username","fftl")
        );

        actions.andExpect(status().isOk())
            .andExpect(jsonPath("success").value(true))
            .andExpect(jsonPath("message").doesNotExist())
            .andDo(print());
    }

    @Transactional
    @DisplayName("하나 조회하기 테스트")
    @Test
    void findById() throws Exception{
        ResultActions actions = mvc.perform(
            get("/user/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
        );

        actions
            .andDo(print()
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("success").value(true))
//            .andExpect(jsonPath("message").doesNotExist())
            );
    }

    @Transactional
    @DisplayName("모두 조회하기 테스트")
    @Test
    void findAll() throws Exception{

        ResultActions actions = mvc.perform(
            get("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
        );

        actions
            .andDo(print()
            );
    }
}