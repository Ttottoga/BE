-- 메뉴, 학교 카테고리 추가

-- 학교
INSERT INTO school(id, name) VALUES (1, '서울여대');
INSERT INTO school(id, name) VALUES (2, '광운대');
INSERT INTO school(id,name) VALUES (3, '한국외대');
INSERT INTO school(id,name) VALUES (4, '덕성여대');
INSERT INTO school(id,name) VALUES (5, '부산-경남');
INSERT INTO school(id,name) VALUES (6, '광주-전라');
INSERT INTO school(id,name) VALUES (7, '다른지역');

-- 메뉴
INSERT INTO menu(id, name) VALUES (1, '치킨');
INSERT INTO menu(id, name) VALUES (2, '버거');
INSERT INTO menu(id, name) VALUES (3, '한식');
INSERT INTO menu(id, name) VALUES (4, '일식/돈까스');
INSERT INTO menu(id, name) VALUES (5, '족발/보쌈');
INSERT INTO menu(id, name) VALUES (6, '중국집');
INSERT INTO menu(id, name) VALUES (7, '분식');
INSERT INTO menu(id, name) VALUES (8, '아시안');
INSERT INTO menu(id, name) VALUES (9, '피자/양식');
INSERT INTO menu(id, name) VALUES (10, '카페/디저트');
INSERT INTO menu(id, name) VALUES (11, '샐러드');
INSERT INTO menu(id, name) VALUES (12, '도시락/죽');
INSERT INTO menu(id, name) VALUES (13, '찜/탕');
INSERT INTO menu(id, name) VALUES (14, '고기/구이');
INSERT INTO menu(id, name) VALUES (15, '회/초밥');
INSERT INTO menu(id, name) VALUES (16, '샌드위치');

-- 상점 데이터
INSERT INTO store (name, title, sub_title, image, use_info, sale_info, place_info, spon_info, service_info, address, school_id, menu_id)
VALUES ('둘쓰닭', '[강남] 둘이 먹다 하나가 쓰러진닭, 둘쓰닭', '콘치즈에 퐁당 빠진 통닭이 선보이는 맛을 즐겨보세요', '/images/image1', 'use_info', 'sale_info', 'place_info','spon_info', '사리 1종(당면/떡/치즈)', 'address', 1, 1);
