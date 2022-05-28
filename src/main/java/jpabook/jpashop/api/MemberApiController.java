package jpabook.jpashop.api;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@ResponseBody => 데이터를 바로 json 으로 serealization하는 어노테이션
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    //@RequestBody => 받아온 데이터를 json으로 넘어온 값을 arguments에 맞는 값으로 바꿔주는 어노테이션
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
        /*arguments가 Entity로 받기때문에 문제가 많음 v1의 문제
        * 1.entity는 자주 변환된다.
        * 2.db와 닿아있는 entity를 외부로 노출한다.
        * 3.한 entity에 다른 Valid나 Logic이 필요할때 넣을 수 없다.*/

    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemverV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest {
        private String name;

        public String getName() {
            return name;
        }
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
