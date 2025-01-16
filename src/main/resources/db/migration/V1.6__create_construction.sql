-- Construction Table (시공 테이블)
CREATE TABLE IF NOT EXISTS construction (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,                                                             -- Primary Key
    `user_sn` BIGINT NOT NULL,                                                                          -- Foreign Key - Users 테이블과 연결
    `location` VARCHAR(255) NOT NULL,                                                                   -- 시공할곳
    `total_labor_cost` INT NOT NULL,                                                                    -- 총 인건비
    `total_personnel` INT NOT NULL,                                                                     -- 총 인원
    `total_consumer_cost` INT NOT NULL,                                                                 -- 총 소비자 금액
    `total_contractor_cost` INT NOT NULL,                                                               -- 총 시공자 금액
    `total_meal_cost` INT NOT NULL,                                                                     -- 총 식비
    `start_date` DATE NOT NULL,                                                                         -- 시공 날짜
    `start_time` TIME NOT NULL,                                                                         -- 시공 시작 시간
    CONSTRAINT FK_CONSTRUCTION_USER_SN FOREIGN KEY (user_sn) REFERENCES users(`sn`) ON DELETE CASCADE,
    INDEX idx_date (`start_date`),                                                                       -- date 인덱스
    INDEX idx_location (`location`)                                                                      -- 시공할 곳 인덱스

);

-- Material Table (자재 테이블)
CREATE TABLE IF NOT EXISTS material (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,                                           -- Primary Key
    `construction_sn` BIGINT NOT NULL,                                                -- Foreign Key - Construction 테이블과 연결
    `quantity` INT NOT NULL,                                                          -- 사용량 (미터 단위)
    `consumer_price` INT NOT NULL,                                                    -- 소비자 금액 (미터당)
    `contractor_price` INT NOT NULL,                                                  -- 시공자 금액 (미터당)
    CONSTRAINT FK_MATERIAL_CONSTRUCTION_SN FOREIGN KEY (construction_sn) REFERENCES construction(`sn`) ON DELETE CASCADE
);

-- SubMaterial Table (부자재 테이블)
CREATE TABLE IF NOT EXISTS sub_material (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,                                             -- Primary Key
    `construction_sn` BIGINT NOT NULL,                                                  -- Foreign Key - Construction 테이블과 연결
    `consumer_price` INT NOT NULL,                                                      -- 소비자 금액 (미터당)
    `contractor_price` INT NOT NULL,                                                    -- 시공자 금액 (미터당)
    CONSTRAINT FK_SUB_MATERIAL_CONSTRUCTION_SN FOREIGN KEY (construction_sn) REFERENCES construction(`sn`) ON DELETE CASCADE
);
