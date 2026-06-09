package lionSpringBoot.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentCreateRequest {
    private String title;
    private String description;
}