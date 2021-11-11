-- 외래키 설정 해제
set foreign_key_checks =0;

-- 관리자 등록
insert into member(id, password,authority,name,nickname,email,phoneNumber,level) 
values("admin",password("0000"),8,"admin","admin","admin@test.com","1111",5);

-- 회원 등록
insert into member(authority,id,name,nickname,email,birthday,password,photo,phoneNumber,zipcode,address,detail_address) 
values(2, 'aaa', 'aaa', 'aaa', 'aaa@test.com', '2021-1-1', '1111', 'aaa.gif', '1111', '1111', '1111', '1111');

-- 상품 등록
 insert into product(type_no, name, variety, origin, volume, alcoholLevel, sugarLevel, acidity, weight, photo)
    values(2, "화이트와인", "와인", "1", 1, 
    1.0, 1, 2, 2, #{photo}) 
-- 재고 등록
insert into stock(member_no, product_no, amount, price) 
values((select member_no from member where id='s1'), 1, 3, 12000);

--재고 수정
update stock set amount=6, price=20 where stock_no=2;

--예약 등록
 insert into purchase(
 member_no, 
 cart_no, 
 payment_no, 
 date, 
 payment_status, 
 pickup_booking_date, 
 pickup_time) 
    values((select member_no from member where id = 'b1'), 
    5,
    1,
    '2019-1-1',
    1,
    '2019-1-3',
    1)
    
--구매 타입 등록
insert into payment_type(payment_no, type) values(1,1);

insert into board_tag(board_no, tag_no) values(31, 1);

--태그 등록
insert into tag(tag) values ('');

--auto_increment
ALTER TABEL tag MODIFY tag_no INT NOT NULL AUTO_INCREMENT;

insert into product_type(type, subType) values("와인","레드와인");
insert into product_type(type, subType) values("와인","화이트와인");
insert into product_type(type, subType) values("와인","로제와인");
insert into product_type(type, subType) values("와인","스위트와인");
insert into product_type(type, subType) values("와인","스파클링");

insert into product_type(type, subType) values("위스키","아메리칸위스키");
insert into product_type(type, subType) values("위스키","스카치위스키");
insert into product_type(type, subType) values("위스키","아이리쉬위스키");
insert into product_type(type, subType) values("위스키","캐나다위스키");

insert into product_type(type, subType) values("브랜디,꼬냑","브랜디");
insert into product_type(type, subType) values("브랜디,꼬냑","꼬냑");
insert into product_type(type, subType) values("브랜디,꼬냑","알마냑");

insert into product_type(type, subType) values(“리큐르,보드카”,”리큐르”);
insert into product_type(type, subType) values(“리큐르,보드카”,”진”);
insert into product_type(type, subType) values(“리큐르,보드카”,”럼”);
insert into product_type(type, subType) values(“리큐르,보드카”,”보드카”);
insert into product_type(type, subType) values(“리큐르,보드카”,”데낄라”);
insert into product_type(type, subType) values(“리큐르,보드카”,”음료/시럽”);

insert into product_type(type, subType) values(“전통주”,”한국전통주”);
insert into product_type(type, subType) values(“전통주”,”중국전통주”);
insert into product_type(type, subType) values(“전통주”,”일본전통주”);
insert into product_type(type, subType) values(“전통주”,”기타전통주”);