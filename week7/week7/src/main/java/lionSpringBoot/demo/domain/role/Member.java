package lionSpringBoot.demo.domain.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Member {
    private String name;
    private String major;
    private int generation;
    private String part;
}