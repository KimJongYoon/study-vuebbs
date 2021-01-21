package com.kjy.bbs.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bbs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BbsVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
