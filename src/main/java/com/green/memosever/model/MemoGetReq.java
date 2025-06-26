package com.green.memosever.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.BindParam;

import java.beans.ConstructorProperties;


//@AllArgsConstructor // 짧게 할려고할떄 이거 적고 생성자 만들어주면된다
//@Builder // 지금 굳이 쓸필요는 없고 작성법이 좋아서 써본다
         //맴버 필드에서 모든 경우의 수의 생성자를 만들어준다. final도 된다
@Getter
@ToString

public class MemoGetReq {
    private String searchText;
    private Integer page;

//    public MemoGetReq(@BindParam("search_text") String searchText, int page) {
//        this.searchText = searchText;
//        this.page = page;
//    }

    @ConstructorProperties({"search_text", "page"})
    public MemoGetReq(String searchText, Integer page) {
        this.searchText = searchText;
        this.page = page;
    }
}
