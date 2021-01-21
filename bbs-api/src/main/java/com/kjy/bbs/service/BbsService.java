package com.kjy.bbs.service;

import com.kjy.bbs.exception.BbsNotFoundException;
import com.kjy.bbs.model.BbsDto;
import com.kjy.bbs.model.BbsVo;
import com.kjy.bbs.repository.BbsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsService {

    @Autowired
    private BbsRepository bbsRepository;

    public List<BbsDto.Get> getBbses() {
        return BbsDto.Get.of(bbsRepository.findAll());
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
