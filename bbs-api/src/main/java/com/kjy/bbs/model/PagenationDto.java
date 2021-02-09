package com.kjy.bbs.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class PagenationDto {

    private Integer totalCount; // 데이터 총 개수

    @Builder.Default
    private Integer listSize = 10; // 한 번에 몇 개 게시글을 보여줄 것인지

    private Integer totalPageCount; // 페이지 총 개수

    private Integer pageSize; // 하단 페이지 크기( 10으로 지정 시 1~10까지의 페이지 보임)

   @Builder.Default
    private Integer currentPage = 1; // 페이지네이션 시작

    private Integer firstPage; // 페이지네이션 시작

    private Integer lastPage; // 페이지네이션 끝

    public Integer getStartPage() {
        return (currentPage - 1) * listSize;
    }

    public Integer getTotalPageCount() {
        return ( ( totalCount - 1) / listSize ) + 1;
    }

    public Integer getFirstPage() {
        return ( (currentPage - 1 ) /  pageSize) * pageSize + 1;
    }

    public Integer getLastPage() {
        return ( lastPage > totalPageCount ) ? lastPage = totalPageCount : firstPage + pageSize - 1;
    }



}
