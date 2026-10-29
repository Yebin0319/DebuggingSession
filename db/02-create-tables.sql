-- 테이블과 시드 데이터 (01 다음에 root 계정으로 실행). 다시 실행하면 처음 상태로 되돌립니다.
USE course_db;

DROP TABLE IF EXISTS enrollment;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student;

CREATE TABLE student (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE course (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    max_count   INT NOT NULL,
    day_of_week VARCHAR(10) NOT NULL
);

CREATE TABLE enrollment (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id  BIGINT NOT NULL,
    course_id   BIGINT NOT NULL,
    status      VARCHAR(20) NOT NULL,
    enrolled_at DATETIME NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

INSERT INTO student (name) VALUES ('김멋사'), ('이사자'), ('박백엔드');

INSERT INTO course (name, max_count, day_of_week) VALUES
    ('자료구조', 30, 'MON'),
    ('알고리즘', 2, 'TUE'),
    ('운영체제', 30, 'WED');
