package lionSpringBoot.demo.config;

import lionSpringBoot.demo.repository.MemberRepository;
import lionSpringBoot.demo.repository.MemoryMemberRepository;
import lionSpringBoot.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}