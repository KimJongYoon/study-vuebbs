package com.kjy.bbs.repository;


import com.kjy.bbs.model.BbsVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BbsRepository extends JpaRepository<BbsVo, Long> {

    List<BbsVo> findAll();

    BbsVo save(BbsVo bbsVo);
}
