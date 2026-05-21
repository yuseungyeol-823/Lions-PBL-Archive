package lionSpringBoot.demo.dto;

import lionSpringBoot.demo.domain.role.Lion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LionResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String studentId;

    // Lion 객체를 주면 Response 상자로 뚝딱 변환해주는 메서드
    public static LionResponse from(Lion lion) {
        LionResponse response = new LionResponse();
        response.setName(lion.getName());
        response.setMajor(lion.getMajor());
        response.setGeneration(lion.getGeneration());
        response.setPart(lion.getPart());
        response.setRoleName("아기사자");
        response.setStudentId(lion.getStudentId());
        return response;
    }
}