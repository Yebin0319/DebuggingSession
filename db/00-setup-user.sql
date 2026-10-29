-- [사전 준비] MySQL root 계정으로 한 번만 실행하세요. 앱이 쓸 계정만 만듭니다.
-- (이걸 안 하면 'Access denied for user debug_user' 가 납니다)
CREATE USER IF NOT EXISTS 'debug_user'@'%' IDENTIFIED BY 'debug1234';
GRANT ALL PRIVILEGES ON course_db.* TO 'debug_user'@'%';
FLUSH PRIVILEGES;
