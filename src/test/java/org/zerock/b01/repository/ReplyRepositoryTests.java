package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.BoardListReplyCountDTO;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {

        // 실제 DB에 있는 bno
        Long bno = 100L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글.....")
                .replyer("replyer1")
                .build();

        replyRepository.save(reply);
    }

    // Reply 클래스의 @ToString에서 exclude를 제거하면 에러가 남
    // reply 테이블에서 쿼리를 실행했지만, Board 객체를 같이 출력해야 하므로 다시 board 테이블에 쿼리를 추가로 실행해야만 하는 상황이라
    // 다시 데이터베이스를 연결해야 하는데 현재 테스트 코드는 한 번만 쿼리를 실행할 수 있기 때문임
    // 강제로 이를 실행하기 위해 @Transactional 추가
    // @Transactional 추가하면 reply 테이블에 쿼리가 실행되고 board 테이블에 추가 쿼리가 실행됨
    @Transactional
    @Test
    public void testBoardReplies() {
        Long bno = 100L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
        result.getContent().forEach(reply -> {
            log.info(reply);
        });
    }
}
