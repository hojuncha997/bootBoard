//PageResultDTO.java

package com.example.bootboard.dto;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {
    // 다양한 곳에서 사용할 수 있도록 제네릭 타입을 이용해서 DTO와 EN 타입을 지정. (EN = Entity)

    //DTO리스트
    private List<DTO> dtoList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int page;

    //목록 사이즈
    private int size;

    //시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    //이전, 다음
    private boolean prev, next;

    //페이지 번호 목록
    private List<Integer> pageList;




    //PageResultDTO는 Page<Entity> 타입을 이용해서 생성할 수 있도록 생성자로 작성.
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        //Function: 엔티티 객체들을 DTO로 변환해 주는 기능

        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1; //0부터 시작하므로 1추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd : totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed()
                .collect(Collectors.toList());

    }



    // 이와 같은 제네릭 방식을 적용하면 추후 추가적인 클래스를 작성하지 않고도 목록 데이터를 처리할 수 있다.

    /*
     * PageResultDTO는 List<DTO> 타입으로 DTO 객체들을 보관한다.
     * 그렇기 때문에 Page<Entity>의 내용물 중에서 엔티티 객체를 DTO로 변환하는 기능이 필요하다.
     * 가장 일반적인 형태는 추상 클래스를 이용해서 이를 처리하는 방식이다.
     * 그러한 경우 매번 새로운 클래스가 필요하다는 단점이 있다.
     *
     * 이번 프로젝트의 경우 엔티티 객체의 DTO 변환은 서비스 인터페이스에 정의한
     * entityToDto() 메서드와 별도로 Function객체로 만들어서 처리한다.
     * */

}