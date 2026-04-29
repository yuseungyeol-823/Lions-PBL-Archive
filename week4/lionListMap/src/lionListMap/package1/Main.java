package lionListMap.package1;

import lionListMap.role.*;
import java.util.*;

public class Main {
    private static List<Member> members = new ArrayList<>(); // List 선언
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=======  멤버 관리 시스템 =======");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 종료");
            System.out.print("선택: ");
            String menu = sc.nextLine();

            if (menu.equals("1")) register();
            else if (menu.equals("2")) showAll();
            else if (menu.equals("3")) search();
            else if (menu.equals("4")) break;
        }
    }

    private static void register() {
        System.out.println("\n---   멤버 등록 ---");
        System.out.print("역할 (1: 아기사자, 2: 운영진): ");
        String type = sc.nextLine();
        System.out.print("이름: "); String name = sc.nextLine();

        // 중복 이름 확인
        for (Member m : members) {
            if (m.getName().equals(name)) {
                System.out.println("  등록 실패: 이미 존재하는 이름입니다.");
                return;
            }
        }

        System.out.print("전공: "); String major = sc.nextLine();
        System.out.print("기수: "); int ordinal = Integer.parseInt(sc.nextLine());
        System.out.print("파트: "); String part = sc.nextLine();

        if (type.equals("1")) {
            System.out.print("학번: "); String id = sc.nextLine();
            members.add(new Lion(name, major, ordinal, part, id));
        } else {
            System.out.print("직책: "); String pos = sc.nextLine();
            members.add(new Staff(name, major, ordinal, part, pos));
        }
        System.out.println("✅ 등록 완료: " + name);
    }

    private static void showAll() {
        System.out.println("\n--- 📋 전체 멤버 목록 ---");
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            System.out.println((i + 1) + ". [" + m.getRoleName() + "] " + m.getName() + " - " + m.getOrdinal() + "기");
        }
        System.out.println(" 총 " + members.size() + "명");
    }

    private static void search() {
        System.out.print("\n  검색할 이름: ");
        String name = sc.nextLine();
        for (Member m : members) {
            if (m.getName().equals(name)) {
                System.out.println("\n✨ [검색 결과]");
                System.out.println("  역할: " + m.getRoleName());
                System.out.println( m.getCommonInfo());
                System.out.println( m.getExtraInfo());
                System.out.println("  과제 제출 가능 여부: " + (m.checkSubmissionStatus() ? " 가능" : "  불가능"));
                return;
            }
        }
        System.out.println(" 해당 이름의 멤버를 찾을 수 없습니다.");
    }
}