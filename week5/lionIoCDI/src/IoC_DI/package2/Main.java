package IoC_DI.package2;

import java.util.List;
import IoC_DI.role.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository (더미 데이터)");
        System.out.print("선택: ");
        int repoChoice = Integer.parseInt(sc.nextLine());

        // 1. 객체 생성의 주도권이 외부(Main)로 이동했습니다 (IoC).
        MemberRepository repository;
        if (repoChoice == 1) {
            repository = new MemoryMemberRepository();
        } else {
            repository = new MockMemberRepository();
        }

        // 2. 생성자를 통해 사용할 객체를 전달합니다 (DI).
        MemberService service = new MemberService(repository);

        while (true) {
            System.out.println("\n----- 멋사 멤버 관리 시스템 (Step 2: DI 적용) -----");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 종료");
            System.out.print("선택: ");
            String menu = sc.nextLine();

            if (menu.equals("1")) register(service, sc);
            else if (menu.equals("2")) showAll(service);
            else if (menu.equals("3")) search(service, sc);
            else if (menu.equals("4")) break;
        }
    }

    private static void register(MemberService service, Scanner sc) {
        System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
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
        System.out.println("\n--- 전체 멤버 목록 ---");
        List<Member> members = service.findMembers();
        for (Member m : members) {
            System.out.println("[" + m.getRoleName() + "] " + m.getName());
        }
    }

    private static void search(MemberService service, Scanner sc) {
        System.out.print("검색할 이름: ");
        String name = sc.nextLine();
        Member m = service.findOne(name);

        if (m != null) {
            System.out.println("\n--- 검색 결과 ---");
            System.out.println("역할: " + m.getRoleName());
            System.out.println(m.getCommonInfo());
            System.out.println("과제 제출 가능: " + (m.checkSubmissionStatus() ? "가능" : "불가능"));
        } else {
            System.out.println("멤버를 찾을 수 없습니다.");
        }
    }
}