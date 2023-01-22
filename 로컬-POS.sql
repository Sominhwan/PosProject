ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

CREATE USER POS IDENTIFIED BY 1234  -- 사용자 ID : POS, 비밀번호 : 1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
GRANT connect, resource, dba TO POS; -- 권한 부여

DROP TABLE 상품;
DROP TABLE 고객;
DROP TABLE 관리자;


--상품 테이블
CREATE TABLE 상품(	
    상품명 VARCHAR2(60) NOT NULL, 
	가격 NUMBER, 
    카테고리 VARCHAR2(60) NOT NULL
); 

--고객 테이블
CREATE TABLE 고객(
    아아디 VARCHAR2(60) NOT NULL,
    이름  VARCHAR2(60) NOT NULL,
    포인트 NUMBER
);

--관리자 테이블
CREATE TABLE 관리자(
    비밀번호 VARCHAR(40) NOT NULL
);

-- [상품 테이블에 튜플 삽입]
INSERT INTO 상품 VALUES ('스팸', 2000, '가공식품');
INSERT INTO 상품 VALUES ('두부', 2200, '가공식품');
INSERT INTO 상품 VALUES ('어묵', 4500, '가공식품');
INSERT INTO 상품 VALUES ('스위트콘', 3500, '가공식품');
INSERT INTO 상품 VALUES ('식빵', 3000, '가공식품');
INSERT INTO 상품 VALUES ('신라면', 600, '가공식품');
INSERT INTO 상품 VALUES ('단무지', 1400, '가공식품');
INSERT INTO 상품 VALUES ('게맛살', 5000, '가공식품');
INSERT INTO 상품 VALUES ('베이컨', 6500, '가공식품');
INSERT INTO 상품 VALUES ('유부', 6300, '가공식품');

INSERT INTO 상품 VALUES ('꼬깔콘', 2000, '과자류');
INSERT INTO 상품 VALUES ('스윙칩', 2300, '과자류');
INSERT INTO 상품 VALUES ('콘칩', 1500, '과자류');
INSERT INTO 상품 VALUES ('오예스', 2600, '과자류');
INSERT INTO 상품 VALUES ('빼빼로', 800, '과자류');

INSERT INTO 상품 VALUES ('참이슬', 2500, '주류');
INSERT INTO 상품 VALUES ('카스', 1300, '주류');
INSERT INTO 상품 VALUES ('처음처럼', 1500, '주류');
INSERT INTO 상품 VALUES ('하이네켄', 1400, '주류');
INSERT INTO 상품 VALUES ('기네스', 2000, '주류');

INSERT INTO 상품 VALUES ('포켓티슈', 1500, '잡화');
INSERT INTO 상품 VALUES ('휴지', 1000, '잡화');
INSERT INTO 상품 VALUES ('양말', 2200, '잡화');
INSERT INTO 상품 VALUES ('머플러', 5000, '잡화');
INSERT INTO 상품 VALUES ('노트', 1500, '잡화');

INSERT INTO 상품 VALUES ('에쎄', 5000, '담배');
INSERT INTO 상품 VALUES ('말보로', 4500, '담배');
INSERT INTO 상품 VALUES ('아이코스', 6500, '담배');
INSERT INTO 상품 VALUES ('팔리아멘트', 5000, '담배');
INSERT INTO 상품 VALUES ('라크', 5500, '담배');

-- [고객 테이블에 튜플 삽입]
INSERT INTO 고객 VALUES ('morri', '김지훈', 5000);
INSERT INTO 고객 VALUES ('swine', '최운', 3000);
INSERT INTO 고객 VALUES ('julin', '박정수', 4000);
INSERT INTO 고객 VALUES ('joete', '안수진', 10000);
INSERT INTO 고객 VALUES ('gudre', '황지우', 2000);
INSERT INTO 고객 VALUES ('allos', '강창호', 1000);

-- [관리자 테이블에 튜플 삽입]
INSERT INTO 관리자 VALUES ('1234');

COMMIT;
