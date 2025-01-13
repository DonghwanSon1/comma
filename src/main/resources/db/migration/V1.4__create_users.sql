-- 유저 테이블 (users)
CREATE TABLE IF NOT EXISTS users (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,        -- 유저 SN
    `email` VARCHAR(30) NOT NULL,               -- 사용자 ID (고유)
    `password` VARCHAR(100) NOT NULL,              -- 비밀번호
    `name` VARCHAR(10) NOT NULL,                   -- 이름
    `phone` VARCHAR(15) NOT NULL,                  -- 전화번호
    `salary` INT DEFAULT 0,                      -- 일당
    UNIQUE KEY unique_id (`email`)                    -- 유저 테이블 유니크 키 관계
);