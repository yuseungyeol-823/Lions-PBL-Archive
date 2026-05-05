package IoC_DI.policy;

public class StaffPolicy implements Policy {
    @Override
    public boolean canSubmit() {
        return false; // 운영진은 과제 제출 불가능
    }
}