package org.zerock.b01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardListReplyCountDTO;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
    
    // 목록 처리는 Querydsl을 이용하는 구조이므로 추가 메소드 구현 
    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithAll(String[] types, String keyword, Pageable pageable);
}
