package pkg1;
import java.util.Scanner;

public class Step2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Step 2: 객체 내부 검증 시작 ===");

        System.out.print("이름: "); String name = scanner.nextLine();
        System.out.print("전공: "); String major = scanner.nextLine();
        System.out.print("기수: "); int generation = scanner.nextInt();

        Lion lion = new Lion(name, major, generation);
        System.out.println("[System] Lion 객체가 먼저 생성되었습니다.");

        if (lion.isValid()) {
            System.out.println("[System] 객체 검증 성공!");
            lion.printInfo();
        } else {
            System.out.println("[Error] 객체 내부 상태가 유효하지 않습니다.");
        }
    }
}