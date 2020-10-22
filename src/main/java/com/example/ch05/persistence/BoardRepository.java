package com.example.ch05.persistence;

import com.example.ch05.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board,Long> {
    List<Board> findByTitle(String searchKeyWord);
    List<Board> findByContentContaining(String searchKeyword);
    List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

    @Query("SELECT b from Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
    Page<Board> queryAnnotation1(String searchKeyword, Pageable pageable);
}
