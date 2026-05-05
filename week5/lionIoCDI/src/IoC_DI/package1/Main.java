package IoC_DI.package1;

import IoC_DI.role.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Main은 오직 Service만 사용함
        MemberService service = new MemberService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=======   멤버 관리 시스템 (Step 1: 레이어 분리) =======");
            System.out.println("1. 멤버 등록\n2. 전체 멤버 조회\n3. 이름으로 검색\n4. 종료");
            System.out.print("선택: ");
            String menu = sc.nextLine();

            if (menu.equals("1")) register(service, sc);
            else if (menu.equals("2")) showAll(service);
            else if (menu.equals("3")) search(service, sc);
            else if (menu.equals("4")) break;
        }
    }

    private static void register(MemberService service, Scanner sc) {
        System.out.println("\n---   멤버 등록 ---");
        System.out.print("역할 (1: 아기사자, 2: 운영진): ");
        String type = sc.nextLine();
        System.out.print("이름: "); String name = sc.nextLine();
        System.out.print("전공: "); String major = sc.nextLine();
        System.out.print("기수: "); int ordinal = Integer.parseInt(sc.nextLine());
        System.out.print("파트: "); String part = sc.nextLine();

        if (type.equals("1")) {
            System.out.print("학번: "); String id = sc.nextLine();
            service.join(new Lion(name, major, ordinal, part, id));
        } else {
            System.out.print("직책: "); String pos = sc.nextLine();
            service.join(new Staff(name, major, ordinal, part, pos));
        }
    }

    private static void showAll(MemberService service) {
        System.out.println("\n---   전체 멤버 목록 ---");
        service.findMembers().forEach(m ->
                System.out.println("[" + m.getRoleName() + "] " + m.getName()));
    }

    private static void search(MemberService service, Scanner sc) {
        System.out.print("\n  검색할 이름: ");
        String name = sc.nextLine();
        Member m = service.findOne(name);

        if (m != null) {
            System.out.println("\n✨ [검색 결과]");
            System.out.println(m.getCommonInfo());
            System.out.println("  과제 제출 가능 여부: " + (m.checkSubmissionStatus() ? "  가능" : "  불가능"));
        } else {
            System.out.println("  해당 멤버를 찾을 수 없습니다.");
        }
    }
}