package lionSpringBoot.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffUpdateRequest {
    private String major;
    private int generation;
    private String part;
    private String position; // name 제외
}