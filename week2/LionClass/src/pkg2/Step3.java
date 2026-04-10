package pkg2;

import pkg1.Lion; // 다른 패키지이므로 import 필요

public class Step3 {
    public static void main(String[] args) {
        Lion lion = new Lion("김멋대", "컴공", 14);

        System.out.println("=== Step 3: 접근 제어자 테스트 ===");

        lion.name = "유승열";
        System.out.println("Name 수정 성공: " + lion.name);

        //System.out.println(lion.major);
        //System.out.println(lion.generation);

        System.out.println("[알림] 주석 처리된 코드는 접근 제어자 제한으로 인해 실행이 불가능합니다.");
    }
}