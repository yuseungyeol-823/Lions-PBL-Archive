package lionSpringBoot.demo.dto;

import lionSpringBoot.demo.domain.Assignment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentResponse {
    private Long id;
    private String title;
    private String description;
    private Long memberId;
    private String memberName;

    public static AssignmentResponse from(Assignment assignment) {
        AssignmentResponse response = new AssignmentResponse();
        response.setId(assignment.getId());
        response.setTitle(assignment.getTitle());
        response.setDescription(assignment.getDescription());

        if (assignment.getMember() != null) {
            response.setMemberId(assignment.getMember().getId());
            response.setMemberName(assignment.getMember().getName());
        }
        return response;
    }
}