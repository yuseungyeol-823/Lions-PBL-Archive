# 🦁 멋쟁이사자처럼 통합 인적 정보 및 과제 관리 시스템

## 📝 프로젝트 소개
본 프로젝트는 **멋쟁이사자처럼 아기사자(LION) 및 운영진(STAFF)**의 인적 정보와 주차별 제출 과제를 연동하여 통합 관리하는 백엔드 시스템입니다. 

10주차 미션을 통해 **전역 예외 처리 체계**를 안착시켜 예외 발생 시 일관된 JSON 응답을 보장하며, 실제 프론트엔드 UI 화면과의 풀스택 데이터 통신 연동을 완수했습니다.

---

## 🛠️ 기술 스택 (Tech Stack)

| 분류 | 기술 기술 |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.2.5 |
| **Data Access** | Spring Data JPA (Hibernate) |
| **Database** | MySQL 8.0 |
| **Build Tool** | Gradle 8.x |
| **Documentation** | Swagger UI (Springdoc OpenAPI 2.5.0) |

---

## 🚀 실행 방법 (How to Run)

### 1. 저장소 클론 (Clone Repository)
git clone <본인의 레포지토리 주소>
cd <프로젝트 폴더명>

### 2. 데이터베이스 스키마 생성
로컬 MySQL에 접속하여 본 애플리케이션이 사용할 데이터베이스 스키마를 생성합니다.
CREATE DATABASE likelion_pbl CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

### 3. 애플리케이션 구동
터미널에서 프로젝트 루트 경로로 이동한 뒤, 아래 명령어를 통해 프로젝트를 빌드하고 구동합니다.
* Linux / macOS: ./gradlew bootRun
* Windows: ./gradlew.bat bootRun

### 🔗 최종 연동 주소
* 웹 메인 화면 (Frontend UI): http://localhost:8080
* API 명세서 (Swagger UI): http://localhost:8080/swagger-ui/index.html

---

## 📋 API 목록 (API Specification)

### 1. 회원 관리 API (Member Control)

| HTTP 메서드 | URI | 설명 | 상태 코드 (성공 / 실패) |
| :--- | :--- | :--- | :--- |
| POST | /members/lions | 신규 아기사자 회원 등록 (이름 중복 체크) | 201 Created / 409 Conflict |
| POST | /members/staffs | 신규 운영진 회원 등록 (이름 중복 체크) | 201 Created / 409 Conflict |
| GET | /members | 전체 멤버 목록 조회 (또는 ?part=백엔드 파트별 필터링 조회) | 200 OK |
| GET | /members/{id} | 특정 회원 단건 식별 조회 | 200 OK / 404 Not Found |
| PUT | /members/lions/{id} | 특정 아기사자 회원 정보 수정 | 200 OK / 404 Not Found |
| PUT | /members/staffs/{id} | 특정 운영진 회원 정보 수정 | 200 OK / 404 Not Found |
| DELETE | /members/{id} | 특정 회원 정보 영구 삭제 (제출한 과제도 함께 삭제) | 204 No Content / 404 Not Found |

### 2. 과제 관리 API (Assignment Control)

| HTTP 메서드 | URI | 설명 | 상태 코드 (성공 / 실패) |
| :--- | :--- | :--- | :--- |
| POST | /members/{memberId}/assignments | 특정 회원(memberId)이 제출한 신규 과제 등록 | 201 Created / 404 Not Found |
| GET | /assignments | 시스템 내 등록된 전체 과제 통합 리스트 조회 | 200 OK |
| GET | /members/{memberId}/assignments | 특정 회원이 제출한 과제 목록 조회 | 200 OK / 404 Not Found |
| GET | /assignments/{id} | 특정 과제 단건 식별 조회 | 200 OK / 404 Not Found |
| GET | /assignments/search?keyword=1주차 | 특정 키워드가 포함된 과제 제목 검색 | 200 OK |
| PUT | /assignments/{id} | 특정 과제의 제목 및 설명 수정 | 200 OK / 404 Not Found |
| DELETE | /assignments/{id} | 특정 과제 내역 영구 삭제 | 204 No Content / 404 Not Found |

---

## 🏗️ 프로젝트 구조 (Project Structure)

```text
src/main/java/lionSpringBoot/demo/
├── 📂 member
│   ├── 📂 controller    # MemberController (HTTP 요청 매핑, 불필요한 null 분기 로직 제거)
│   ├── 📂 service       # MemberService (회원 등록·조회·수정·삭제 비즈니스 로직, 예외 발생 시 throw)
│   ├── 📂 repository    # MemberRepository (Spring Data JPA 인터페이스, findByPart 등 확장)
│   ├── 📂 domain        # Member 엔티티 객체 및 RoleType(LION/STAFF) Enum 클래스
│   └── 📂 dto           # Lion/Staff 생성 및 수정을 위한 Request DTO 및 통합 Response DTO
│
├── 📂 assignment
│   ├── 📂 controller    # AssignmentController (전체 과제 조회 및 Containing 검색 API 구현)
│   ├── 📂 service       # AssignmentService (과제 영속성 제어 및 예외 검증 로직 처리)
│   ├── 📂 repository    # AssignmentRepository (JPA 쿼리 메서드 findByTitleContaining 탑재)
│   ├── 📂 domain        # Assignment 엔티티 객체 (Member 엔티티와 N:1 다대일 외래키 연관관계 형성)
│   └── 📂 dto           # 과제 작성을 위한 Request DTO 및 회원 정보를 결합한 AssignmentResponse
│
└── 📂 global            # 공통 에러 핸들링 및 전역 아키텍처 패키지
    ├── 📂 exception     # @RestControllerAdvice 기반 전역 관제탑(GlobalExceptionHandler) 및 커스텀 예외 클래스
    └── 📂 dto           # 예외 발생 시 클라이언트에게 일관된 서식으로 내려줄 공통 ErrorResponse 상자 
