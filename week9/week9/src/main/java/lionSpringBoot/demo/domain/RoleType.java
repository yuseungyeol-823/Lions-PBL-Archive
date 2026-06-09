package lionSpringBoot.demo.domain;

import lombok.Getter;

@Getter
public enum RoleType {
    LION("아기사자"),
    STAFF("운영진");

    private final String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }
}