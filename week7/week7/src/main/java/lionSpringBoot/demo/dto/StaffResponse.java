package lionSpringBoot.demo.dto;

import lionSpringBoot.demo.domain.role.Staff;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String position;

    // Staff 객체를 주면 Response 상자로 뚝딱 변환해주는 메서드
    public static StaffResponse from(Staff staff) {
        StaffResponse response = new StaffResponse();
        response.setName(staff.getName());
        response.setMajor(staff.getMajor());
        response.setGeneration(staff.getGeneration());
        response.setPart(staff.getPart());
        response.setRoleName("운영진");
        response.setPosition(staff.getPosition());
        return response;
    }
}