package lionAbstraction.policy;

public class LionPolicy implements Policy {
    @Override
    public boolean canSubmit() {
        return true;
    }
}