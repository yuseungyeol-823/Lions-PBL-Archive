package lionListMap.role;

import lionListMap.policy.Policy;
import lionListMap.policy.StaffPolicy;

public class Staff extends Member {
    private String position;

    public Staff(String name, String major, int ordinal, String part, String position) {
        super(name, major, ordinal, part);
        this.position = position;
    }

    @Override
    public String getRoleName() { return "운영진"; }

    @Override
    public String getExtraInfo() {
        return "직책: " + position;
    }

    @Override
    public Policy getPolicy() {
        return new StaffPolicy();
    }
}