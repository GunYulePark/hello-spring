package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
//    1. 정적 컨텐츠 방식
    @GetMapping("hello") //"hello"라고 들어오면 밑에 method를 호출
    // http의 get method
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //resources의 templates의 hello.html로 가서 랜더링.
    }

//    2. MVC 방식
    @GetMapping("hello-mvc")
//    이번엔 RequestParam으로 Parameter 사용
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

//    3-1. API 방식 : 단순 문자
    @GetMapping("hello-string")
    @ResponseBody
//    http의 body부에 이 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //if name="spring" > "hello spring"
    }
//    위에 정적 컨텐츠와 MVC와의 차이점 : view이런게 없고 그냥 문자가 그대로 내려간다.

//    3-2. API 방식 : 데이터
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
//    기본 json 방식

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
