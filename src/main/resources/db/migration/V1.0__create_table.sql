-- 브랜드 테이블 (brand)
CREATE TABLE IF NOT EXISTS brand (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 고유 ID
    `name` VARCHAR(255) NOT NULL,              -- 브랜드 이름
    `url` VARCHAR(255) NOT NULL                -- 브랜드 웹사이트 URL
);


-- 필름 테이블 (film)
CREATE TABLE IF NOT EXISTS film (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,                                         -- 고유 ID
    `brand_sn` BIGINT NOT NULL,                                                     -- 브랜드 이름
    `code` VARCHAR(255) NOT NULL,                                                   -- 필름 코드
    `name` VARCHAR(255),                                                            -- 필름 이름
    `color` VARCHAR(255),                                                           -- 필름 색상
    `image_url` VARCHAR(255),                                                       -- 필름 이미지
    `consumer_price` INT NOT NULL,                                                  -- 필름 소비자 가격
    `my_price` INT,                                                                 -- 필름 나의 가격
    UNIQUE KEY UNIQUE_FILM (`brand_sn`, `code`),
    CONSTRAINT FK_FILM_BRAND_SN FOREIGN KEY (brand_sn) REFERENCES brand(sn)
);
