package lionListMap.package2;

import lionListMap.role.*;
import java.util.*;

public class Main {
    private static List<Member> members = new ArrayList<>();
    private static Map<String, List<Member>> partMap = new HashMap<>(); // Map 선언
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=======   멤버 관리 시스템 (Step 2) =======");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 조회");
            System.out.println("3. 이름 검색");
            System.out.println("4. 파트별 조회");
            System.out.println("5. 종료");
            System.out.print("선택: ");
            String menu = sc.nextLine();

            if (menu.equals("1")) register();
            else if (menu.equals("2")) showAll();
            else if (menu.equals("3")) search();
            else if (menu.equals("4")) showByPart();
            else if (menu.equals("5")) break;
        }
    }

    private static void register() {
        System.out.println("\n---   멤버 등록 ---");
        System.out.print("역할 (1: 아기사자, 2: 운영진): ");
        String type = sc.nextLine();
        System.out.print("이름: "); String name = sc.nextLine();

        for (Member m : members) {
            if (m.getName().equals(name)) {
                System.out.println("  등록 실패: 이미 존재하는 이름입니다.");
                return;
            }
        }

        System.out.print("전공: "); String major = sc.nextLine();
        System.out.print("기수: "); int ordinal = Integer.parseInt(sc.nextLine());
        System.out.print("파트 (백엔드/프론트엔드/기획/디자인): "); String part = sc.nextLine();

        Member newMember;
        if (type.equals("1")) {
            System.out.print("학번: "); String id = sc.nextLine();
            newMember = new Lion(name, major, ordinal, part, id);
        } else {
            System.out.print("직책: "); String pos = sc.nextLine();
            newMember = new Staff(name, major, ordinal, part, pos);
        }

        // List와 Map에 동시 등록
        members.add(newMember);
        partMap.computeIfAbsent(part, k -> new ArrayList<>()).add(newMember);

        System.out.println("  등록 완료: " + name);
    }

    private static void showByPart() {
        System.out.println("\n---   파트별 조회 ---");
        if (partMap.isEmpty()) {
            System.out.println("등록된 데이터가 없습니다.");
            return;
        }

        System.out.println("  등록된 파트: " + partMap.keySet());
        System.out.print("조회할 파트: ");
        String part = sc.nextLine();

        if (partMap.containsKey(part)) {
            System.out.println("\n✨ [" + part + " 파트 멤버]");
            List<Member> list = partMap.get(part);
            for (int i = 0; i < list.size(); i++) {
                Member m = list.get(i);
                System.out.println((i + 1) + ". " + m.getName() + " (" + m.getRoleName() + ") - " + m.getOrdinal() + "기");
            }
        } else {
            System.out.println("  해당 파트에 등록된 멤버가 없습니다.");
        }
    }

    private static void showAll() {
        System.out.println("\n---   전체 멤버 목록 ---");
        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            System.out.println((i + 1) + ". [" + m.getRoleName() + "] " + m.getName() + " - " + m.getOrdinal() + "기");
        }
        System.out.println("  총 " + members.size() + "명");
    }

    private static void search() {
        System.out.print("\n🔍 검색할 이름: ");
        String name = sc.nextLine();
        for (Member m : members) {
            if (m.getName().equals(name)) {
                System.out.println("\n✨ [검색 결과]");
                System.out.println("  역할: " + m.getRoleName());
                System.out.println( m.getCommonInfo());
                System.out.println(  m.getExtraInfo());
                System.out.println(" 과제 제출 가능 여부: " + (m.checkSubmissionStatus() ? " 가능" : " 불가능"));
                return;
            }
        }
        System.out.println("  해당 멤버를 찾을 수 없습니다.");
    }
}