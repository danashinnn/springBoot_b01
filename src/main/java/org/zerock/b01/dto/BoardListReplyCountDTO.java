package org.zerock.b01.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO { // 목록 화면에 특정한 게시물에 속한 댓글의 숫자를 같이 출력하기 위해 추가 개발
    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private Long replyCount;
}
