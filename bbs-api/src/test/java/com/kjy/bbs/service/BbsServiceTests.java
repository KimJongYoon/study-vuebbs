package com.kjy.bbs.service;

import com.kjy.bbs.exception.BbsNotFoundException;
import com.kjy.bbs.model.BbsDto;
import com.kjy.bbs.model.BbsVo;
import com.kjy.bbs.repository.BbsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;


class BbsServiceTests {

    @InjectMocks
    private BbsService bbsService;

    @Mock
    private BbsRepository bbsRepository;

    @BeforeEach
    private void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 조회 테스트
     */
    @Test
    public void getBbses() {
        BbsVo bbsVo = BbsVo.builder()
                .id(1234L)
                .title("제목")
                .content("내용")
                .build();

        given(bbsRepository.findAll()).willReturn(Arrays.asList(bbsVo));

        List<BbsDto.Get> bbses = bbsService.getBbses();

        assertThat(bbses.get(0).getId(), is(1234L));


    }

    @Test
    public void getBbsWithExisted() {
        BbsVo bbsVo = BbsVo.builder()
                .id(1L)
                .title("제목2")
                .content("내용2")
                .build();

        given(bbsRepository.findById(any())).willReturn(Optional.of(bbsVo));
        BbsDto.Get get = bbsService.getBbs(1L);

        assertThat(get.getTitle(), is("제목2"));

        verify(bbsRepository).findById(any());
    }

    @Test
    public void getBbsWithNotExsited() {
        given(bbsRepository.findById(Long.MAX_VALUE)).willReturn(Optional.empty());

        assertThrows(BbsNotFoundException.class, () -> bbsService.getBbs(Long.MAX_VALUE));
    }

    @Test
    public void addBbs() {
        BbsDto.Add addDto = BbsDto.Add.builder()
                .title("제목1")
                .content("내용1")
                .registId("테스터")
                .build();
        given(bbsRepository.save(any())).will(invocation -> {
            BbsVo bbsVo = invocation.getArgument(0);
            bbsVo.setId(1234L);
            return bbsVo;
        });

        BbsDto.Get getDto = bbsService.add(addDto);

        assertThat(getDto.getId(), is(1234L));
    }
}