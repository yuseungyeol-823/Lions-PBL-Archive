package IoC_DI.role;

import IoC_DI.policy.Policy;

public abstract class Member {
    private String name;
    private String major;
    private int ordinal;
    private String part;

    public Member(String name, String major, int ordinal, String part) {
        this.name = name;
        this.major = major;
        this.ordinal = ordinal;
        this.part = part;
    }

    public String getName() { return name; }
    public String getCommonInfo() {
        return String.format("이름: %s | 전공: %s | 기수: %d | 파트: %s", name, major, ordinal, part);
    }

    public abstract String getRoleName();
    public abstract String getExtraInfo();
    public abstract Policy getPolicy();

    public boolean checkSubmissionStatus() {
        return getPolicy().canSubmit();
    }
}