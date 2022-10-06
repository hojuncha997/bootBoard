//PageRequestDTO.java

package com.example.bootboard.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable; //페이지 관련 임포트
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {


    private int page;
    private int size;

    //검색을 위해 추가
    private String type;
    private String keyword;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    //  이 DTO의 목적은 JPA에서 사용하는 Pageable 타입의 객체를 생성하는 것이다.
    //  JPA를 이용하는 경우에는 페이지 번호가 0부터 시작한다.
    //  따라서 1페이지의 경우 0이 될 수 있도록 아래와 같이 작성한다.
    //  정렬(Sort)는 다양한 상황에서 사용하기 위해서 별도의 파라미터로 받도록 설계.
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort );
    }
}