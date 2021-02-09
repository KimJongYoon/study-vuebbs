package com.kjy.bbs.controller;

import com.kjy.bbs.model.BbsDto;
import com.kjy.bbs.model.BbsVo;
import com.kjy.bbs.service.BbsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
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
        Pageable pageable = PageRequest.of(1, 10);
        BbsDto.Get mockGet = BbsDto.Get.builder()
                .id(1004L)
                .title("제목")
                .registId("테스터1")
                .build();
        Page<BbsDto.Get> response = new PageImpl<>( Arrays.asList(mockGet ));
//        response.add( BbsDto.Get.of(bbsVo) );

        given(bbsService.getBbses(pageable)).willReturn(response);

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