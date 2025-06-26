package com.green.memosever;

import com.green.memosever.model.MemoGetOneRes;
import com.green.memosever.model.MemoGetReq;
import com.green.memosever.model.MemoGetRes;
import com.green.memosever.model.MemoPostReq;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


//@AllArgsConstructor 요거는 모든 맴버필드 생성자 만드는거
@RequiredArgsConstructor // final이 붙은 애들의 생성자만 알아서 만들어준다.
@Service // 빈등록 // 스프링 컨테이너가 객체화를 시키고 그 주소값을 들고있는거
public class MemoService {
    //Persistance DB 작업 처리(로직+DB작업지시 )
    //생성자로 주입 받고 싶음.
    private final MemoMapper memoMapper;

    public int save (MemoPostReq req) {
        return memoMapper.save(req);
    }

    public List<MemoGetRes> findAll (MemoGetReq req) {
        return memoMapper.findAll(req);
    }
    public MemoGetOneRes findById (int id) {
        return memoMapper.findById(id);
    }





}
