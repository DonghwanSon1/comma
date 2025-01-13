-- 유저 역할 테이블 (users_role)
CREATE TABLE IF NOT EXISTS users_role (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,         	                                  -- 유저 역할 SN
    `user_sn` BIGINT NOT NULL,                      	                                  -- 사용자 SN (users 테이블의 sn 참조)
    `role` VARCHAR(30) NOT NULL,                    	                                  -- 역할
    CONSTRAINT FK_USERS_ROLE_USER_SN  FOREIGN KEY (user_sn) REFERENCES users(`sn`)      -- 유저 테이블과 외래 키 관계
);