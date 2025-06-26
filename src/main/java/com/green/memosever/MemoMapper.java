package com.green.memosever;


import com.green.memosever.model.MemoGetOneRes;
import com.green.memosever.model.MemoGetReq;
import com.green.memosever.model.MemoGetRes;
import com.green.memosever.model.MemoPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


// 인터페이스가 implements한 클래스가 만들어지고 그 클래스를 객체화(주소값)을
// 스프링 컨테이너가 들고있다.(빈등록)
// 스프링 컨테이너가 객체 주소값을 들고 있을 수 있는건 빈등록이 되었기 때문
// 여기가 DB작업 하는곳 이다
@Mapper
public interface MemoMapper {
    //insert, update, delete return type가 int
    int save (MemoPostReq req);
    // 한개의 글을 하나의 res 리스트 객체를 만들어서 하나하나 모든 글의 타이틀과 시간을 그 리스트에 박아넣는다
    List<MemoGetRes> findAll(MemoGetReq req);
    MemoGetOneRes findById (int id);
    int delById (int id);
}
