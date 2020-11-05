package com.example.shop.util;

import org.springframework.stereotype.Component;


@Component("paging")
public class Paging {
    private final int DEFAULT_PAGING_INDEX = 1;
    private final int DEFAULT_PAGING_SIZE = 10; //page count
    private final int DEFAULT_PAGING_COUNT = 10; //1 page per row count

    private int pageCount = 0;
    private int pageSize = 0;
    private int firstPageIndex;
    private int prevPageIndex;
    private int startPageIndex;
    private int pageIndex = 1;
    private int endPageIndex;
    private int nextPageIndex;
    private int finalPageIndex;
    private int totalCount;


    public void printPageProperty() {
        System.out.println("pageCount:" + this.pageCount);
        System.out.println("pageSize:" + this.pageSize);
        System.out.println("firstPageIndx:" + this.firstPageIndex);
        System.out.println("prevPageIndex:" + this.prevPageIndex);
        System.out.println("startPageIndex:" + this.startPageIndex);
        System.out.println("pageIndex:" + this.pageIndex);
        System.out.println("endPageIndex:" + this.endPageIndex);
        System.out.println("nextPageIndex:" + this.nextPageIndex);
        System.out.println("finalPageIndex:" + this.finalPageIndex);
        System.out.println("totalCount:" + this.totalCount);
    }

    public int getPageCount() { return pageCount; }

    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getFirstPageIndex() { return firstPageIndex; }

    public void setFirstPageIndex(int firstPageIndex) { this.firstPageIndex = firstPageIndex; }

    public int getPrevPageIndex() { return prevPageIndex; }

    public void setPrevPageIndex(int prevPageIndex) { this.prevPageIndex = prevPageIndex; }

    public int getStartPageIndex() { return startPageIndex; }

    public void setStartPageIndex(int startPageIndex) { this.startPageIndex = startPageIndex; }

    public int getPageIndex() { return pageIndex; }

    public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; }

    public int getEndPageIndex() { return endPageIndex; }

    public void setEndPageIndex(int endPageIndex) { this.endPageIndex = endPageIndex; }

    public int getNextPageIndex() { return nextPageIndex; }

    public void setNextPageIndex(int nextPageIndex) { this.nextPageIndex = nextPageIndex; }

    public int getFinalPageIndex() { return finalPageIndex; }

    public void setFinalPageIndex(int finalPageIndex) { this.finalPageIndex = finalPageIndex; }

    public int getTotalCount() { return totalCount; }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        makePaging();
    }

    private void makePaging() {
        if (this.totalCount == 0) return; //전체 개수 0일경우
        if (this.pageCount == 0) this.setPageCount(DEFAULT_PAGING_COUNT); //
        if (this.pageIndex == 0) this.setPageIndex(DEFAULT_PAGING_INDEX);
        if (this.pageSize == 0) this.setPageSize(DEFAULT_PAGING_SIZE);

        //1. final 페이지 계산 및 현재가 넘어가면 final로
        int finalPage = (totalCount - 1) / pageCount + 1; //totalCount로 마지막 페이지 계산
        if (this.pageIndex > finalPage) this.setPageIndex(finalPage); //현재 페이지가 마지막 페이지를 넘어가면 마지막 페이지로 set한다.

        //2. 현재가 0이하가 되면 1로
        if (this.pageIndex <= 0 || this.pageIndex > finalPage) this.pageIndex = 1; //현재페이지가 1보다 작으면 1로 만든다.

        //3. 현재 페이지 상태 파악
        boolean isIndexwFirst = pageIndex == 1 ? true : false; //현재 페이지가 처음인지
        boolean isIndexwFinal = pageIndex == finalPage ? true : false; //현재 페이지가 마지막인지

        //4. startPage, endPage 계산 (경계)
        int startPage = ((pageIndex - 1) / this.pageSize) * this.pageSize + 1; //10이면 나머지가 0이 되므로 -1 시켜준 후 계산시켜준다.

        int endPage = startPage + this.pageSize - 1;
        if (endPage > finalPage) {
            endPage = finalPage;
        }

        //5. 처음페이지 설정
        this.setFirstPageIndex(1);

        //6. 이전페이지 설정(경계)
        if (isIndexwFirst) {
            this.setPrevPageIndex(1);
        }
        else {
            this.setPrevPageIndex((pageIndex - 1) < 1 ? 1 : (pageIndex - 1));
        }

        //7. 시작페이지 설정
        this.setStartPageIndex(startPage);
        //8. 끝페이지 설정
        this.setEndPageIndex(endPage);

        //9. 다음페이지 설정(경계)
        if (isIndexwFinal) {
            this.setNextPageIndex(finalPage);
        }
        else {
            this.setNextPageIndex((pageIndex + 1) > finalPage ? finalPage : pageIndex + 1);
        }

        //10. 마지막 페이지 설정
        this.setFinalPageIndex(finalPage);
    }

    @Override
    public String toString() {
        return "Paging{" +
               "pageCount=" + pageCount +
               ", pageSize=" + pageSize +
               ", firstPageIndex=" + firstPageIndex +
               ", prevPageIndex=" + prevPageIndex +
               ", startPageIndex=" + startPageIndex +
               ", pageIndex=" + pageIndex +
               ", endPageIndex=" + endPageIndex +
               ", nextPageIndex=" + nextPageIndex +
               ", finalPageIndex=" + finalPageIndex +
               ", totalCount=" + totalCount +
               '}';
    }
}
