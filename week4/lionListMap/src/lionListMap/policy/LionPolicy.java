package lionListMap.policy;

public class LionPolicy implements Policy {
    @Override
    public boolean canSubmit() {
        return true; // 아기사자는 과제 제출 가능
    }
}