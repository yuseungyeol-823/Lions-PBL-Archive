package lionAbstraction.role;

import lionAbstraction.policy.Policy; // 다른 패키지의 Policy를 가져옴

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

    public String getCommonInfo() {
        return String.format("이름: %s | 전공: %s | 기수: %d | 파트: %s",
                name, major, ordinal, part);
    }

    public abstract String getRoleName();
    public abstract String getExtraInfo();
    public abstract Policy getPolicy();

    public boolean checkSubmissionStatus() {
        return getPolicy().canSubmit();
    }
}