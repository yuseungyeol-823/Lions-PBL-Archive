import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //아기사자 수 입력
        Scanner scanner = new Scanner(System.in);
        int LionCount = 0;

        //5명 검증 5명이상은 while문 탈출 5명미만은 반복
        while (true) {
            System.out.print("저장할 아기사자 수를 입력하세요 : ");
            LionCount = scanner.nextInt();

            if (LionCount > 4) {
                break;
            } else {
                System.out.println("5이상의 숫자를 입력하세요");
            }
        }

        scanner.nextLine();
        //아기사자 이름 배열 저장
        String[] LionNames = new String[LionCount];
        System.out.println(LionCount + "명의 아기사자 이름을 입력받습니다.");

        //아기사자 이름 입력 반복문으로 카운트수만큼 입력
        for (int i = 0; i < LionCount; i++) {
            System.out.print((i + 1) + "번 아기사자 이름: ");
            LionNames[i] = scanner.nextLine();
        }

        //아기사자 명단 출력 배열에 저장된 이름을 반복문으로 배열 순회
        System.out.println("최종 아기사자 명단");
        for (int i = 0; i < LionNames.length; i++) {
            System.out.println((i + 1) + ". " + LionNames[i]);
        }

    }
}
