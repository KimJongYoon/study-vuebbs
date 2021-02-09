package com.kjy.bbs.controller;

import com.kjy.bbs.model.BbsDto;
import com.kjy.bbs.service.BbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bbs")
public class BbsController {

    @Autowired
    private BbsService bbsService;

    @GetMapping("")
    public Page<BbsDto.Get> get(@PageableDefault(page = 0, size = 10, sort = "registDtm") Pageable pageable){
        return bbsService.getBbses(pageable);
    }

    @GetMapping("{bbsId}")
    public BbsDto.Get get(@PathVariable("bbsId") Long bbsId){
        return bbsService.getBbs(bbsId);
    }

    @PostMapping("")
    public String add(@RequestBody BbsDto.Add resource, HttpServletResponse response){
        BbsDto.Get bbsData = bbsService.add(resource);
        response.addHeader("location", "/bbs/"+bbsData.getId());
        return "";
    }
}
