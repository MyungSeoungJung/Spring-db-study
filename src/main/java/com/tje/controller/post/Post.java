package com.tje.controller.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //전체 필드 초기화 생성자 => 생성자의 매개변수 순서와 클래스의 필드 순서가 정확히 일치해야 해당 애너테이션으로 생성된 생성자를 사용할 수 있습니다.
@NoArgsConstructor  // 빈 생성자  => 서버에서는 매개변수의 순서를 몰라서 일단 빈생성자를 먼저 만든다음 @AllArgsConstructor로 자동으로 필드값 set해줌
//서버에서는 매개변수의 순서를 몰라서 NoArgsConstructor빈 생성자를 먼저 만든다음 AllArgsConstructor으로 필드로 set을 함?
@Builder

public class Post {
    private long no;
    private String title;
    private String content;
    private String creatorName;
    private long createdTime;
    private String image;

}
