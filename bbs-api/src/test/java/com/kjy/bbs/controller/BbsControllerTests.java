package com.kjy.bbs.controller;

import com.kjy.bbs.model.BbsDto;
import com.kjy.bbs.model.BbsVo;
import com.kjy.bbs.service.BbsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BbsController.class)
class BbsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BbsService bbsService;

    /**
     * 목록 테스트
     */
    @Test
    public void list() throws Exception {
        BbsVo bbsVo = BbsVo.builder()
                .id(1L)
                .title("제목1")
                .content("내용1")
                .registId("KJY")
                .build();
        List<BbsDto.Get> response = new ArrayList<>();
        response.add( BbsDto.Get.of(bbsVo) );

        given(bbsService.getBbses()).willReturn(response);

        mvc.perform(get("/bbs")
        )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getBbs() throws Exception {
        BbsDto.Get get = BbsDto.Get.builder()
                .id(1L)
                .title("제목2")
                .content("내용2")
                .build();

        given(bbsService.getBbs(any())).willReturn(get);

        mvc.perform(get("/bbs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목2"))
                .andExpect(jsonPath("$.content").value("내용2"))
        ;
    }

    @Test
    public void add() throws Exception {
        BbsDto.Add bbsAdd = BbsDto.Add.builder()
                .title("입력 테스트")
                .content("입력 테스트 내용")
                .registId("테스터")
                .build();


        given(bbsService.add(any())).will(invocation -> {
            BbsDto.Add param = invocation.getArgument(0);
            BbsVo bbsVo = BbsVo.builder()
                    .title(param.getTitle())
                    .content(param.getContent())
                    .registId(param.getRegistId())
                    .id(1234L)
                    .build();
            return BbsDto.Get.of(bbsVo);
        });


        mvc.perform(post("/bbs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"입력 테스트\", \"content\": \"입력 테스트 내용\"}")
        )
                .andExpect(status().isOk())
                .andExpect(header().string("location", "/bbs/1234"))
                .andExpect(content().string(""))
        ;
    }
}