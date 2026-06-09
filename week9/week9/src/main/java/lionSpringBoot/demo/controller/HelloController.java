package lionSpringBoot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스가 REST API를 처리하는 컨트롤러임을 알립니다.
public class HelloController {

    @GetMapping("/hello") // localhost:8080/hello 로 접속하면 실행됩니다.
    public String hello() {
        return "Hello, Likelion!";
    }
}
