package com.green.memosever.model;


import lombok.Builder;
import lombok.Getter;

@Builder // 지금 굳이 쓸필요는 없고 작성법이 좋아서 써본다
         //맴버 필드에서 모든 경우의 수의 생성자를 만들어준다. final도 된다
@Getter
public class MemoGetReq {
    private String searchText;
    private String page;
}
