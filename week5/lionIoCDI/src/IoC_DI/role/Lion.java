package IoC_DI.role;

import IoC_DI.policy.Policy;
import IoC_DI.policy.LionPolicy;

public class Lion extends Member {
    private String studentId;

    public Lion(String name, String major, int ordinal, String part, String studentId) {
        super(name, major, ordinal, part);
        this.studentId = studentId;
    }

    @Override
    public String getRoleName() { return "아기사자"; }

    @Override
    public String getExtraInfo() { return "학번: " + studentId; }

    @Override
    public Policy getPolicy() { return new LionPolicy(); }
}