package com.kjy.bbs.service;

import com.kjy.bbs.exception.BbsNotFoundException;
import com.kjy.bbs.model.BbsDto;
import com.kjy.bbs.model.BbsVo;
import com.kjy.bbs.repository.BbsRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BbsService {

    @Autowired
    private BbsRepository bbsRepository;

    public Page<BbsDto.Get> getBbses(Pageable pageable) {
        log.info("값: {}",bbsRepository.findAll(pageable));
        return BbsDto.Get.of(bbsRepository.findAll(pageable));
    }

    public BbsDto.Get getBbs(Long bbsId) {
        BbsVo bbsVo =  bbsRepository.findById(bbsId).orElseThrow(() -> new BbsNotFoundException(bbsId));
        return BbsDto.Get.of(bbsVo);
    }

    public BbsDto.Get add(BbsDto.Add resource) {
        BbsVo bbsVo =  BbsDto.Add.toBbsVo(resource);
        return BbsDto.Get.of(bbsRepository.save(bbsVo));
    }

}
