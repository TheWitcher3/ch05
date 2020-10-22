package com.example.ch05;


import com.example.ch05.domain.Board;
import com.example.ch05.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private BoardRepository boardRepository;

//    @BeforeEach
//    public void dataPrepare() {
//        for (int i = 1 ; i <= 200 ; i++) {
//            Board board = new Board();
//            board.setTitle("테스트 제목 " + i);
//            board.setWriter("테스터");
//            board.setContent("테스트 내용 " + i);
//            board.setCnt(0L);
//            board.setCreateDate(new Date());
//
//            boardRepository.save(board);
//        }
//    }

    @Test
    public void testFindByTitle() {
        List<Board> boardList = boardRepository.findByTitle("테스트 제목 10");

        System.out.println("검색 결과");
        for (Board board : boardList) {
            System.out.println("---> " + board.toString());
        }

    }

    @Test
    public void testByContentContaining() {
        List<Board> boardList = boardRepository.findByContentContaining("17");
        System.out.println("testByContentContaining 결과");
        for (Board board : boardList) {
            System.out.println("--->" + board.toString());
        }
    }

    @Test
    public void testFindByTitleContainingOrderBySeqDesc() {
        List<Board> boardList = boardRepository.findByTitleContainingOrderBySeqDesc("10");
        System.out.println("testFindByTitleContainingOrderBySeqDesc 결과");
        for (Board board : boardList) {
            System.out.println("----> " + board.toString());
        }
    }

    @Test
    public void testfindByTitleContaining() {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
        Page<Board> pageInfo = boardRepository.findByTitleContaining("제목", pageable);
//        List<Board> boardList = boardRepository.findByTitleContaining("11", pageable);+

        System.out.println("Page Size : " + pageInfo.getSize());
        System.out.println("Total Pages : " + pageInfo.getTotalPages());
        System.out.println("Total Count : " + pageInfo.getTotalElements());
        System.out.println("Next : " + pageInfo.nextPageable());
        System.out.println("Next Page Number: " + pageInfo.nextPageable().getPageNumber());

        List<Board> boardList = pageInfo.getContent();

        System.out.println("testfindByTitleContaining 결과");
        for (Board board : boardList) {
            System.out.println("----> " + board.toString());
        }
    }

    @Test
    public void testQueryAnnotation() {
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC, "seq");
        Page<Board> pageInfo = boardRepository.queryAnnotation1("제목 10", pageable);

        List<Board> boardList = pageInfo.getContent();

        System.out.println("검색 결과");
        for (Board board : boardList) {
            System.out.println("---> " + board.toString());
        }

    }

}
