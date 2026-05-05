package IoC_DI.role;

import IoC_DI.policy.Policy;
import IoC_DI.policy.StaffPolicy;

public class Staff extends Member {
    private String position;

    public Staff(String name, String major, int ordinal, String part, String position) {
        super(name, major, ordinal, part);
        this.position = position;
    }

    @Override
    public String getRoleName() { return "운영진"; }

    @Override
    public String getExtraInfo() { return "직책: " + position; }

    @Override
    public Policy getPolicy() { return new StaffPolicy(); }
}