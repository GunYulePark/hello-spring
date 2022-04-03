package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    //    필드 방식 @Autowired final MemberService memberService;

    //    생성자 방식 >> 대부분 이거 쓴다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    url 창에 enter
//    데이터를 조회할 때
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

//    데이터를 폼에 넣어서 전달할 때
//    데이터를 등록할 때
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
////    setter 방식
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }




}
