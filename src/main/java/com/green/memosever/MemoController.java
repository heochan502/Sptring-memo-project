package com.green.memosever;


import com.green.memosever.model.MemoPostReq;
import com.green.memosever.model.MemoPutReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public String postMemo (@RequestBody MemoPostReq req)
    {
        log.info("req={}", req); // Slf4j 의사용법
        //System.out.println("postMemo" + req);
        //int result = memoService.insMemo(req);
        //return result == 1 ? "성공" :"실패";
        return "입력 성공";
    }

    //Read


    //쿼리스트링 으로 받는거
    @GetMapping()
    //request는 반드시 데이터는 보낸다 라는게 있어서 데이터 못보내면 에러 터짐
    // @RequestParam("search_text") 는 search_text의 키값의 파람데이터는 search 로저장한다 !
    //@RequestParam(name="search_text", required = false) required 해서 데이터 반환해서 없으면 null로 된다
    // 아무것도없이 눌렀을때 위에 위에는 null로 한다
    // 아래 @RequestParam(required = false) Integer page 요거는 리턴타입이 null이라도 들어갈수있게 만든거

    public String getMemo (@RequestParam(name="search_text", required = false) String search,
                           @RequestParam(required = false) Integer page)
    {
        log.info("search={}, page = {}", search, page);

        //memoService.getMemoList(searchText,page);
        return "메모 리스트";
    }

    @GetMapping("/{id}")
    // @PathVariable(name="id") <- 얘도 이런식으로 된다
    public String getOneMemo(@PathVariable String id)
    {
        log.info("id={}", id);
        return "메모하나";
    }



    //Update

   @PutMapping
    public String putMemo(@RequestBody MemoPutReq req)
   {
       log.info("memoId={},title={},content={} ", req.getMemoId() ,req.getTitle(), req.getTitle());
       return "입력완료";
   }

    //Delete
   @DeleteMapping()
    public String deleteMemo(@RequestParam(name="memo_id") int id)
   {
       log.info("memoid = {}", id);
       return "삭제완료";
   }

}
