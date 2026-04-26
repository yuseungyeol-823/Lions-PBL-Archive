package lionAbstraction;

import lionAbstraction.role.Lion;
import lionAbstraction.role.Member;
import lionAbstraction.role.Staff;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Member> members = new ArrayList<>();

        System.out.println("======= 아기사자 정보 입력 =======");
        System.out.print("이름: "); String lName = scanner.nextLine();
        System.out.print("전공: "); String lMajor = scanner.nextLine();
        System.out.print("기수: "); int lOrdinal = Integer.parseInt(scanner.nextLine());
        System.out.print("파트: "); String lPart = scanner.nextLine();
        System.out.print("학번: "); String lId = scanner.nextLine();
        members.add(new Lion(lName, lMajor, lOrdinal, lPart, lId));

        System.out.println();

        System.out.println("======= 운영진 정보 입력 =======");
        System.out.print("이름: "); String sName = scanner.nextLine();
        System.out.print("전공: "); String sMajor = scanner.nextLine();
        System.out.print("기수: "); int sOrdinal = Integer.parseInt(scanner.nextLine());
        System.out.print("파트: "); String sPart = scanner.nextLine();
        System.out.print("직책: "); String sPos = scanner.nextLine();
        members.add(new Staff(sName, sMajor, sOrdinal, sPart, sPos));

        System.out.println("\n======= 결과 출력 =======");
        for (Member m : members) {
            System.out.println(" 역할: " + m.getRoleName());
            System.out.println(" " + m.getCommonInfo());
            System.out.println(m.getExtraInfo());

            String status = m.checkSubmissionStatus() ? " 가능" : " 불가능";
            System.out.println(" 과제 제출 가능 여부: " + status);
            System.out.println("---------------------------");
        }
    }
}