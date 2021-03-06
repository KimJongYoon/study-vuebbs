package com.kjy.bbs.model;

import lombok.*;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BbsDto {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Add {

        private String title;

        private String content;

        private String registId;

        public static BbsVo toBbsVo(BbsDto.Add addDto) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()); // null 값 제외하고 맵핑

            return modelMapper.map(addDto, BbsVo.class);
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class search {
        private Long id;

        private String title;

        private String content;

        private String registId;

        private LocalDateTime registDtm;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Get {

        private Long id;

        private String title;

        private String content;

        private String registId;

        private LocalDateTime registDtm;

        private String updtId;

        private LocalDateTime updtDtm;


        public static Get of(BbsVo bbsVo) {
            ModelMapper modelMapper = new ModelMapper();
            final Get dto = modelMapper.map(bbsVo, Get.class);
            return dto;
        }

        // jpa 페이징 사용
        public static Page<Get> of(Page<BbsVo> pageBbs) {
            return pageBbs.map(bbsVo -> {
               return Get.builder()
                       .id(bbsVo.getId())
                       .title(bbsVo.getTitle())
                       .registId(bbsVo.getRegistId())
                       .registDtm(bbsVo.getRegistDtm())
                       .build();
            });
        }

        // jpa 페이징 사용하지 않을 때...
//        public static List<Get> of(List<BbsVo> bbsList) {
//            return bbsList.stream().map(Get::of).collect(Collectors.toList());
//        }
    }
}
