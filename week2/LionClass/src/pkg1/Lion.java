package pkg1;

public class Lion {
    public String name;
    String major;
    private int generation;

    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    public boolean isValid() {
        System.out.println("객체 생성이 완료되었습니다. 아기사자 객체의 상태를 확인합니다.");
        if(name.isEmpty()) {
            System.out.println("이름이 비어 있습니다.");
            return false;
        }
        if(major.isEmpty()) {
            System.out.println("전공이 비어 있습니다.");
            return false;
        }
        if(generation<1) {
            System.out.println("기수가 1 미만입니다.");
            return false;
        }
        return true;
    }

    public void printInfo() {
        System.out.println("=> [Lion 정보] 이름: " + name + ", 전공: " + major + ", 기수: " + generation);
    }

    public void printProfile() {
        if (this.isValid()) {
            System.out.println("아기사자 객체가 자신의 상태를 정상으로 판단했습니다.");
            this.printInfo();
        } else System.out.println("잘못된 아기사자 정보입니다.");
    }

}
