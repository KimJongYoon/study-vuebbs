package com.kjy.bbs.repository;


import com.kjy.bbs.model.BbsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbsRepository extends JpaRepository<BbsVo, Long> {

//    List<BbsVo> findAll();
    Page<BbsVo> findAll(Pageable pageable);

    BbsVo save(BbsVo bbsVo);
}
