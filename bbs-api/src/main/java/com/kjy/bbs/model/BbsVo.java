package com.kjy.bbs.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "bbs")
@Getter
public class BbsVo {

    @Id
    @Setter
    private Long id;

    private String title;

    private String content;

    private String registId;

    @CreationTimestamp
    private LocalDateTime registDtm;

    private String updtId;

    @UpdateTimestamp
    private LocalDateTime updtDtm;


}
