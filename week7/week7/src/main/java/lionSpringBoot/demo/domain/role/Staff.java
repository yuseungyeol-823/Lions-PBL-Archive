package lionSpringBoot.demo.domain.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Staff extends Member {
    private String position;
}