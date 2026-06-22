package lionSpringBoot.demo.global;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String name) {
        super("이미 존재하는 이름입니다. name: " + name);
    }
}