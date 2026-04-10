package pkg1;
import java.util.Scanner;

public class Step1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Step 1: Main 검증 시작 ===");

        System.out.print("이름: "); String name = scanner.nextLine();
        System.out.print("전공: "); String major = scanner.nextLine();
        System.out.print("기수: "); int  generation= scanner.nextInt();

        System.out.println("입력값 검증을 진행합니다.");
        if(name.isEmpty()) {
            System.out.println("이름은 비어있을 수 없습니다.");
            return;
        }
        if(major.isEmpty()) {
            System.out.println("전공은 비어있을 수 없습니다.");
            return;
        }
        if(generation<1) {
            System.out.println("기수는 1 미만일 수 없습니다.");
            return;
        }
        System.out.println("입력값 검증을 통과하여 아기사자 객체 생성을 진행합니다.");
        Lion lion = new Lion(name, major,generation);
        System.out.println("아기사자 정보를 출력합니다.");
        lion.printInfo();
        }
    }