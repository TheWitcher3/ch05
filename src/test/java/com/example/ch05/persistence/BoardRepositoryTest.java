package com.example.ch05.persistence;

import com.example.ch05.domain.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;


    public void testInsertBoard() {
        Board board = new Board();
        board.setTitle("첫 번째 게시글");
        board.setWriter("테스터");
        board.setContent("잘 등록 됩니까?");
        board.setCreateDate(new Date());
        board.setCnt(0L);

        boardRepository.save(board);
    }


    public void testUpdateBoard() {
        System.out.println("==== 1번째 게시글 조회 ====");
        Board board = boardRepository.findById(1L).get();

        System.out.println("==== 1번째 게시글 수정 ====");
        board.setTitle("제목을 수정했습니다.");
        boardRepository.save(board);
    }


    public void testDelete() {
        System.out.println("==== 1번째 게시글 삭제 ====");
        boardRepository.deleteById(1L);
    }
}