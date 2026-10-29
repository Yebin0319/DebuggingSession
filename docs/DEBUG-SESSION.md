# 디버깅 실습 가이드 (수강신청)

이 프로젝트에는 에러 8개가 들어 있어요. 앱을 실행하고, 에러를 읽고, 고치고, 다시 실행하면서 다음 에러를 만나 보세요.
Swagger UI: http://localhost:8080/swagger-ui/index.html

> 에러 로그는 환경에 따라 조금 다를 수 있어요. 실제 화면의 메시지를 기준으로 읽으세요.
> `application.yaml`의 비밀번호는 **절대 커밋하지 마세요.**

## 에러 읽는 법
- `APPLICATION FAILED TO START` 블록이 보이면 그 블록의 **Description / Action**부터 읽어요.
- 스택트레이스만 나오면 **처음 나온 WARN/ERROR 줄**과 **`Caused by:`** 줄을 읽어요.
- 맨 아래 `Application run failed`는 증상이고, 원인은 더 위에 있어요.

## 1. datasource 설정이 없어요
- 재현: `DebuggingApplication` 실행
- 에러: `Failed to configure a DataSource: 'url' attribute is not specified`
- 원인: `application.yaml`에 DB 접속 정보가 없어요.
- 수정: `application.yaml`
  ```yaml
  spring:
    application:
      name: debugging
    datasource:
      url: jdbc:mysql://localhost:3306/course_db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
      username: root
      password: 본인_MySQL_비밀번호
    jpa:
      open-in-view: false
      show-sql: true
      hibernate:
        ddl-auto: none
  ```

## 2. DB가 없어요
- 재현: 다시 실행
- 에러: `ErrorCode: 1049` / `Unknown database 'course_db'`
- 원인: `course_db`를 만들지 않았어요.
- 수정: MySQL에 접속해서 실행해요.
  ```sql
  CREATE DATABASE course_db DEFAULT CHARACTER SET utf8mb4;
  ```

## 3. `Course`에 `@Id`가 없어요
- 재현: 다시 실행
- 에러: `Entity 'hufslikelion.debugging.domain.Course' has no identifier`
- 원인: JPA 엔티티는 기본키(`@Id`)가 꼭 필요해요.
- 수정: `domain/Course.java`
  ```java
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  ```

## 4. 테이블이 없어요
- 재현: 서버가 뜨면 Swagger에서 `GET /api/courses`(강의 목록 조회) 실행
- 에러: `Table 'course_db.course' doesn't exist` (500)
- 시도해 볼 것: 엔티티에 `@Table(name = "...")`를 붙여 보세요. 해결되나요? (안 돼요. 이름이 아니라 **테이블 자체가 없는** 문제예요.)

## 5. 테이블을 자동으로 만들어야 해요
- 원인: `ddl-auto: none`이라 Hibernate가 테이블을 만들지 않아요.
- 수정: `application.yaml`에서 `ddl-auto: update`로 변경
  (개발 환경용 설정이에요. 운영에서는 쓰지 않아요.)

## 6. `GET /api/courses/{id}` 가 500이에요
- 재현: 없는 id(예: `999`)로 강의 단건 조회
- 에러: `NoSuchElementException: No value present`
- 원인: `Optional.orElseThrow()`에 예외를 지정하지 않아 "없음"이 서버 오류(500)로 나와요.
- 수정: `service/CourseService.find`
  ```java
  Course course = courseRepository.findById(id)
          .orElseThrow(() -> new ResponseStatusException(
                  HttpStatus.NOT_FOUND, "강의를 찾을 수 없습니다. id=" + id));
  ```

## 7. 수강신청이 500이에요 (지연 로딩)
- 재현: 학생 1명, 강의 1개를 등록한 뒤 `POST /api/enrollments` with `{"studentId": 1, "courseId": 1}`
- 에러: `LazyInitializationException ... could not initialize proxy - no Session`
- 원인: `@OneToMany`는 기본이 지연 로딩이에요. Service가 끝나 DB 연결이 닫힌 뒤, Controller가 엔티티를 JSON으로 바꾸다가 `enrollments`를 읽으려 해서 터져요.
- 임시 수정: `domain/Course.java`
  ```java
  @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
  ```

## 8. 신청 내역 조회가 500이에요 (무한 재귀)
- 재현: `GET /api/students/1/enrollments`
- 에러: `Could not write JSON: ... Infinite recursion` 또는 `nesting depth exceeds`
- 원인: `Enrollment → Course → enrollments → Enrollment → ...`처럼 엔티티끼리 서로를 가리켜서 JSON이 끝없이 만들어져요.
- 수정: 엔티티 대신 `EnrollmentResponse`(DTO)를 반환해요.
  1. `Course.java`의 `EAGER` 제거
  2. `EnrollmentController`의 반환 타입을 `ApiResponse<EnrollmentResponse>`, `ApiResponse<List<EnrollmentResponse>>`로 변경 (컴파일 에러가 나요. 서비스도 맞춰 주세요.)
  3. `EnrollmentService`에서 `EnrollmentResponse.from(...)`으로 변환해서 반환 (`@Transactional` 안에서)
