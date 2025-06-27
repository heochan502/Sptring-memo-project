package com.green.memosever;


import com.green.memosever.config.model.ResultResponse;
import com.green.memosever.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController는 백엔드에서 데이터만 가지고올꺼니까
//백엔드에서 화면 그리면  Controller 쓰면됨
// @<- 에노테이션이라고 함

@Slf4j // 실수에서 sout 안씀 System.out.println 대체품
@RestController //빈(Bean)등록, 스프링컨테이너 객체 생성을 대리로 맡긴다. 요청 / 응답자 둘다된다
@RequiredArgsConstructor //final만 붇은애만 해준다 롬복 에노테이션 자동으로 생성자를 만들어주는것
// 공통 URL
@RequestMapping("/api/memo") // 주소 적으면 아래 안적어도됨
public class MemoController {
    private final MemoService memoService;
    // 외부에서 만든 주소 객체를 주입 하는게 DI 임
    // 다이렉트 인젝션
    //DI 받는 방법 3가지
    // 1. 필드 주입
    // 2. setter 주입( 메소드 주입)
    // 3. 생성자 주입


    //C
    // (post) /api/
    // json으로 받는거
    @PostMapping ()
    public ResultResponse<Integer> postMemo (@RequestBody MemoPostReq req)
    {
        log.info("req={}", req); // Slf4j 의사용법
        //System.out.println("postMemo" + req);
        int result = memoService.save(req);
//        return result == 1 ? "성공" :"실패";
        return new ResultResponse<>("삽입 성공", result);
//        return "입력 성공";
    }

    //Read


    //쿼리스트링 으로 받는거

    //request는 반드시 데이터는 보낸다 라는게 있어서 데이터 못보내면 에러 터짐
    // @RequestParam("search_text") 는 search_text의 키값의 파람데이터는 search 로저장한다 !
    //@RequestParam(name="search_text", required = false) required 해서 데이터 반환해서 없으면 null로 된다
    // 아무것도없이 눌렀을때 위에 위에는 null로 한다
    // 아래 @RequestParam(required = false) Integer page 요거는 리턴타입이 null이라도 들어갈수있게 만든거

//    아래의 builder랑 효과는 똑같음
//    대신 MemoGet 에 @Builder 말고 AllArgConstructor
@GetMapping
public ResultResponse<List<MemoGetRes>> getMemo (@ModelAttribute  MemoGetReq req)
{
    List<MemoGetRes> result = memoService.findAll(req);
// return memoService.findAll(req);
    String message = String.format("rows: %d", result.size());
    return new ResultResponse<>(message, result);
}



//public String getMemo(@ModelAttribute MemoGetReq req) {
//
//    log.info("req={}", req);
//    return "메모 리스트";
//}

//    public String getMemo (@RequestParam(name="search_text", required = false) String searchText,
//                           @RequestParam(required = false) Integer page)
//    {
//        log.info("search={}, page = {}", searchText, page);
//        // void 메소드면 뒤에 . 으로 연결 할 수가없다.
//        // 그리고 . 은 객체의 주소값을 연결하고 리턴해준다.  reference type을 반환 즉 주소값을 준다.
//        //builder() static 매소드다 왜냐 객체화 안해서!
//        // searchText 인스턴스 메소드
//        MemoGetReq req = MemoGetReq.builder()
//                                    .searchText(searchText)
//                                    .page(page)
//                                    .build();
//        // 객체로 받아야하는이유가 수정의 용의성 때문에 위에서 객체로받아서 객체로 전달하면 데이터가 많아지든 적어지든 상관이없어지고
//        // 코드의 수정이 불필요해지기 때문에 객체를 보낸다
//        //memoService.getMemoList(searchText,page);
//        //memoService.getMemoList(req)
//        return "메모 리스트";
//    }


    @GetMapping("/{id}")
    // @PathVariable(name="id") <- 얘도 이런식으로 된다
//    public String getOneMemo(@PathVariable int id)
    public ResultResponse<MemoGetOneRes> getOneRes(@PathVariable int id)
    {
        log.info("id={}", id);
        MemoGetOneRes result = memoService.findById(id);
//        return memoService.findById(id);
        return new ResultResponse<>("조회 성공", result);
        //return "메모하나";
    }



    //Update

   @PutMapping
    public ResultResponse<Integer> putMemo(@RequestBody MemoPutReq req)
   {
       log.info("memoId={},title={},content={} ",req.getMemoId(), req.getTitle(), req.getCtnts());
       int result = memoService.modify(req);
       // controller는 성공만 적고 실패는 다르게 through catch 로 해서 설정해서 처리한다
        return new ResultResponse<>("수정성공", result);

//       return result == 1 ? "성공" : "실패";
   }

    //Delete
   @DeleteMapping()
   public ResultResponse<Integer> deleteMemo(@RequestParam int id)
//    public String deleteMemo(@RequestParam(name="memo_id") int id)
   {
       log.info("id = {}", id);
       int result = memoService.delById(id);
      // controller는 성공만 적고 실패는 다르게 through catch 로 해서 설정해서 처리한다
       return new ResultResponse<>("삭제 성공", result);
   }

}
