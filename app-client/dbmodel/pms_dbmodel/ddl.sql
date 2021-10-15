-- 회원
DROP TABLE IF EXISTS member RESTRICT;

-- 구매자
DROP TABLE IF EXISTS buyer RESTRICT;

-- 판매자
DROP TABLE IF EXISTS seller RESTRICT;

-- 게시판
DROP TABLE IF EXISTS board RESTRICT;

-- 댓글
DROP TABLE IF EXISTS comment RESTRICT;

-- 좋아요
DROP TABLE IF EXISTS board_like RESTRICT;

-- 메세지알림
DROP TABLE IF EXISTS Temporary RESTRICT;

-- 예약알림
DROP TABLE IF EXISTS Temporary2 RESTRICT;

-- 알림
DROP TABLE IF EXISTS Temporary3 RESTRICT;

-- 상품
DROP TABLE IF EXISTS product RESTRICT;

-- 리뷰
DROP TABLE IF EXISTS review RESTRICT;

-- 재고
DROP TABLE IF EXISTS stock RESTRICT;

-- 예약
DROP TABLE IF EXISTS booking RESTRICT;

-- 메세지
DROP TABLE IF EXISTS message RESTRICT;

-- 장바구니
DROP TABLE IF EXISTS cart RESTRICT;

-- 메세지 알림
DROP TABLE IF EXISTS message_alert RESTRICT;

-- 댓글 알림
DROP TABLE IF EXISTS comment_alert RESTRICT;

-- 예약 알림
DROP TABLE IF EXISTS booking_alert RESTRICT;

-- 태그
DROP TABLE IF EXISTS tag RESTRICT;

-- 게시글태그
DROP TABLE IF EXISTS board_tag RESTRICT;

-- 주종정보
DROP TABLE IF EXISTS product_type RESTRICT;

-- 회원등급
DROP TABLE IF EXISTS member_level RESTRICT;

-- 픽업확정
DROP TABLE IF EXISTS booking_confirm RESTRICT;

-- 회원
CREATE TABLE member (
  member_no      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  level_no       INTEGER      NULL     COMMENT '등급번호', -- 등급번호
  id             VARCHAR(50)  NOT NULL COMMENT '아이디', -- 아이디
  password       VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
  authority      INTEGER      NOT NULL COMMENT '권한', -- 권한
  name           VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  nickname       VARCHAR(50)  NOT NULL COMMENT '닉네임', -- 닉네임
  email          VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  birthday       DATETIME     NULL     COMMENT '생일', -- 생일
  photo          VARCHAR(255) NULL     COMMENT '사진', -- 사진
  phoneNumber    VARCHAR(30)  NOT NULL COMMENT '전화번호', -- 전화번호
  registeredDate DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  active         INTEGER      NULL     COMMENT '탈퇴' -- 탈퇴
)
COMMENT '회원';

-- 회원
ALTER TABLE member
  ADD CONSTRAINT PK_member -- 회원 기본키
    PRIMARY KEY (
      member_no -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_member
  ON member ( -- 회원
    email ASC, -- 이메일
    id ASC     -- 아이디
  );

ALTER TABLE member
  MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 구매자
CREATE TABLE buyer (
  member_no INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  address   VARCHAR(255) NULL     COMMENT '주소' -- 주소
)
COMMENT '구매자';

-- 구매자
ALTER TABLE buyer
  ADD CONSTRAINT PK_buyer -- 구매자 기본키
    PRIMARY KEY (
      member_no -- 회원번호
    );

-- 판매자
CREATE TABLE seller (
  member_no        INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  business_name    VARCHAR(50)  NOT NULL COMMENT '사업자명', -- 사업자명
  business_no      INTEGER      NOT NULL COMMENT '사업자번호', -- 사업자번호
  business_address VARCHAR(255) NOT NULL COMMENT '사업장주소', -- 사업장주소
  business_tel     VARCHAR(30)  NOT NULL COMMENT '사업장전화번호', -- 사업장전화번호
  openingTime      TIME         NOT NULL COMMENT '오픈시간', -- 오픈시간
  closingTime      TIME         NOT NULL COMMENT '마감시간' -- 마감시간
)
COMMENT '판매자';

-- 판매자
ALTER TABLE seller
  ADD CONSTRAINT PK_seller -- 판매자 기본키
    PRIMARY KEY (
      member_no -- 회원번호
    );

-- 판매자 유니크 인덱스
CREATE UNIQUE INDEX UIX_seller
  ON seller ( -- 판매자
    business_no ASC -- 사업자번호
  );

-- 판매자 인덱스
CREATE INDEX IX_seller
  ON seller( -- 판매자
    business_name ASC -- 사업자명
  );

-- 게시판
CREATE TABLE board (
  board_no       INTEGER     NOT NULL COMMENT '게시글번호', -- 게시글번호
  member_no      INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  title          VARCHAR(50) NOT NULL COMMENT '제목', -- 제목
  content        TEXT        NOT NULL COMMENT '내용', -- 내용
  registeredDate DATETIME    NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  views          INTEGER     NULL     DEFAULT 0 COMMENT '조회수' -- 조회수
)
COMMENT '게시판';

-- 게시판
ALTER TABLE board
  ADD CONSTRAINT PK_board -- 게시판 기본키
    PRIMARY KEY (
      board_no -- 게시글번호
    );

-- 게시판 인덱스
CREATE INDEX IX_board
  ON board( -- 게시판
    title ASC -- 제목
  );

ALTER TABLE board
  MODIFY COLUMN board_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 댓글
CREATE TABLE comment (
  comment_no     INTEGER  NOT NULL COMMENT '댓글번호', -- 댓글번호
  content        TEXT     NOT NULL COMMENT '내용', -- 내용
  registeredDate DATETIME NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  board_no       INTEGER  NOT NULL COMMENT '게시글번호', -- 게시글번호
  member_no      INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  comment_alert  INTEGER  NOT NULL COMMENT '알림번호' -- 알림번호
)
COMMENT '댓글';

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT PK_comment -- 댓글 기본키
    PRIMARY KEY (
      comment_no -- 댓글번호
    );

ALTER TABLE comment
  MODIFY COLUMN comment_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 좋아요
CREATE TABLE board_like (
  member_no INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  board_no  INTEGER NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '좋아요';

-- 좋아요
ALTER TABLE board_like
  ADD CONSTRAINT PK_board_like -- 좋아요 기본키
    PRIMARY KEY (
      member_no, -- 회원번호
      board_no   -- 게시글번호
    );

-- 메세지알림
CREATE TABLE Temporary (
  COL   <데이터 타입 없음> NOT NULL COMMENT '메세지알림번호', -- 메세지알림번호
  COL15 <데이터 타입 없음> NULL     COMMENT '상태' -- 상태
)
COMMENT '메세지알림';

-- 메세지알림
ALTER TABLE Temporary
  ADD CONSTRAINT PK_Temporary -- 메세지알림 기본키
    PRIMARY KEY (
      COL -- 메세지알림번호
    );

-- 예약알림
CREATE TABLE Temporary2 (
  COL   <데이터 타입 없음> NOT NULL COMMENT '예약알림번호', -- 예약알림번호
  COL13 <데이터 타입 없음> NULL     COMMENT '상태' -- 상태
)
COMMENT '예약알림';

-- 예약알림
ALTER TABLE Temporary2
  ADD CONSTRAINT PK_Temporary2 -- 예약알림 기본키
    PRIMARY KEY (
      COL -- 예약알림번호
    );

-- 알림
CREATE TABLE Temporary3 (
  member_no  INTEGER            NOT NULL COMMENT '회원번호', -- 회원번호
  comment_no INTEGER            NOT NULL COMMENT '댓글번호', -- 댓글번호
  COL14      <데이터 타입 없음> NULL     COMMENT '상태' -- 상태
)
COMMENT '알림';

-- 알림
ALTER TABLE Temporary3
  ADD CONSTRAINT PK_Temporary3 -- 알림 기본키
    PRIMARY KEY (
      member_no -- 회원번호
    );

-- 알림 유니크 인덱스
CREATE UNIQUE INDEX UIX_Temporary3
  ON Temporary3 ( -- 알림
    comment_no ASC -- 댓글번호
  );

-- 상품
CREATE TABLE product (
  product_no   INTEGER      NOT NULL COMMENT '상품번호', -- 상품번호
  type_no      INTEGER      NOT NULL COMMENT '주종번호', -- 주종번호
  name         VARCHAR(50)  NOT NULL COMMENT '상품명', -- 상품명
  origin       VARCHAR(255) NOT NULL COMMENT '원산지', -- 원산지
  volume       INTEGER      NOT NULL COMMENT '용량', -- 용량
  alcoholLevel FLOAT        NOT NULL COMMENT '알콜도수', -- 알콜도수
  sugarLevel   INTEGER      NOT NULL COMMENT '당도', -- 당도
  acidity      INTEGER      NOT NULL COMMENT '산도', -- 산도
  weight       INTEGER      NOT NULL COMMENT '바디감', -- 바디감
  rate         FLOAT        NOT NULL DEFAULT 0 COMMENT '평점' -- 평점
)
COMMENT '상품';

-- 상품
ALTER TABLE product
  ADD CONSTRAINT PK_product -- 상품 기본키
    PRIMARY KEY (
      product_no -- 상품번호
    );

-- 상품 인덱스
CREATE INDEX IX_product
  ON product( -- 상품
    name ASC -- 상품명
  );

ALTER TABLE product
  MODIFY COLUMN product_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '상품번호';

-- 리뷰
CREATE TABLE review (
  review_no      INTEGER  NOT NULL COMMENT '리뷰번호', -- 리뷰번호
  product_no     INTEGER  NOT NULL COMMENT '상품번호', -- 상품번호
  member_no      INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  confirm_no     INTEGER  NOT NULL COMMENT '픽업확정번호', -- 픽업확정번호
  score          FLOAT    NOT NULL COMMENT '평점', -- 평점
  comment        TEXT     NULL     COMMENT '코멘트', -- 코멘트
  registeredDate DATETIME NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '리뷰';

-- 리뷰
ALTER TABLE review
  ADD CONSTRAINT PK_review -- 리뷰 기본키
    PRIMARY KEY (
      review_no -- 리뷰번호
    );

ALTER TABLE review
  MODIFY COLUMN review_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '리뷰번호';

-- 재고
CREATE TABLE stock (
  stock_no   INTEGER NOT NULL COMMENT '재고번호', -- 재고번호
  member_no  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  product_no INTEGER NOT NULL COMMENT '상품번호', -- 상품번호
  amount     INTEGER NOT NULL COMMENT '재고수량', -- 재고수량
  price      INTEGER NOT NULL COMMENT '가격' -- 가격
)
COMMENT '재고';

-- 재고
ALTER TABLE stock
  ADD CONSTRAINT PK_stock -- 재고 기본키
    PRIMARY KEY (
      stock_no -- 재고번호
    );

ALTER TABLE stock
  MODIFY COLUMN stock_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '재고번호';

-- 예약
CREATE TABLE booking (
  booking_no     INTEGER  NOT NULL COMMENT '예약번호', -- 예약번호
  member_no      INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  cart_no        INTEGER  NOT NULL COMMENT '장바구니번호', -- 장바구니번호
  booking_alert  INTEGER  NOT NULL COMMENT '알림번호', -- 알림번호
  booking_date   DATE     NOT NULL COMMENT '예약날짜', -- 예약날짜
  booking_time   TIME     NOT NULL COMMENT '예약시간', -- 예약시간
  registeredDate DATETIME NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  amount         INTEGER  NOT NULL COMMENT '예약상품의갯수', -- 예약상품의갯수
  total_price    INTEGER  NOT NULL COMMENT '총액', -- 총액
  confirm_no     INTEGER  NOT NULL COMMENT '픽업확정번호' -- 픽업확정번호
)
COMMENT '예약';

-- 예약
ALTER TABLE booking
  ADD CONSTRAINT PK_booking -- 예약 기본키
    PRIMARY KEY (
      booking_no -- 예약번호
    );

ALTER TABLE booking
  MODIFY COLUMN booking_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '예약번호';

-- 메세지
CREATE TABLE message (
  message_no     INTEGER     NOT NULL COMMENT '메세지번호', -- 메세지번호
  member_no      INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  message_alert  INTEGER     NOT NULL COMMENT '알림번호', -- 알림번호
  registeredDate DATETIME    NOT NULL DEFAULT curdate() COMMENT '작성시간', -- 작성시간
  reciver        VARCHAR(50) NOT NULL COMMENT '대화상대', -- 대화상대
  content        TEXT        NOT NULL COMMENT '내용' -- 내용
)
COMMENT '메세지';

-- 메세지
ALTER TABLE message
  ADD CONSTRAINT PK_message -- 메세지 기본키
    PRIMARY KEY (
      message_no -- 메세지번호
    );

-- 장바구니
CREATE TABLE cart (
  cart_no        INTEGER  NOT NULL COMMENT '장바구니번호', -- 장바구니번호
  member_no      INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  stock_no       INTEGER  NOT NULL COMMENT '재고번호', -- 재고번호
  amount         INTEGER  NOT NULL COMMENT '갯수', -- 갯수
  total_price    INTEGER  NOT NULL COMMENT '총액', -- 총액
  registeredDate DATETIME NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '장바구니';

-- 장바구니
ALTER TABLE cart
  ADD CONSTRAINT PK_cart -- 장바구니 기본키
    PRIMARY KEY (
      cart_no -- 장바구니번호
    );

-- 메세지 알림
CREATE TABLE message_alert (
  message_alert INTEGER NOT NULL COMMENT '알림번호', -- 알림번호
  status        INTEGER NOT NULL COMMENT '상태' -- 상태
)
COMMENT '메세지 알림';

-- 메세지 알림
ALTER TABLE message_alert
  ADD CONSTRAINT PK_message_alert -- 메세지 알림 기본키
    PRIMARY KEY (
      message_alert -- 알림번호
    );

-- 댓글 알림
CREATE TABLE comment_alert (
  comment_alert INTEGER NOT NULL COMMENT '알림번호', -- 알림번호
  status        INTEGER NOT NULL COMMENT '상태' -- 상태
)
COMMENT '댓글 알림';

-- 댓글 알림
ALTER TABLE comment_alert
  ADD CONSTRAINT PK_comment_alert -- 댓글 알림 기본키
    PRIMARY KEY (
      comment_alert -- 알림번호
    );

ALTER TABLE comment_alert
  MODIFY COLUMN comment_alert INTEGER NOT NULL AUTO_INCREMENT COMMENT '알림번호';

-- 예약 알림
CREATE TABLE booking_alert (
  booking_alert INTEGER NOT NULL COMMENT '알림번호', -- 알림번호
  status        INTEGER NOT NULL DEFAULT 0 COMMENT '상태' -- 상태
)
COMMENT '예약 알림';

-- 예약 알림
ALTER TABLE booking_alert
  ADD CONSTRAINT PK_booking_alert -- 예약 알림 기본키
    PRIMARY KEY (
      booking_alert -- 알림번호
    );

-- 태그
CREATE TABLE tag (
  tag_no INTEGER NOT NULL COMMENT '태그번호', -- 태그번호
  tag    TEXT    NULL     COMMENT '태그' -- 태그
)
COMMENT '태그';

-- 태그
ALTER TABLE tag
  ADD CONSTRAINT PK_tag -- 태그 기본키
    PRIMARY KEY (
      tag_no -- 태그번호
    );

-- 태그 인덱스
CREATE INDEX IX_tag
  ON tag( -- 태그
    tag ASC -- 태그
  );

-- 게시글태그
CREATE TABLE board_tag (
  board_no INTEGER NOT NULL COMMENT '게시글번호', -- 게시글번호
  tag_no   INTEGER NOT NULL COMMENT '태그번호' -- 태그번호
)
COMMENT '게시글태그';

-- 게시글태그
ALTER TABLE board_tag
  ADD CONSTRAINT PK_board_tag -- 게시글태그 기본키
    PRIMARY KEY (
      board_no, -- 게시글번호
      tag_no    -- 태그번호
    );

-- 주종정보
CREATE TABLE product_type (
  type_no INTEGER      NOT NULL COMMENT '주종번호', -- 주종번호
  type    VARCHAR(255) NOT NULL COMMENT '주종', -- 주종
  variety VARCHAR(255) NOT NULL COMMENT '품종', -- 품종
  subType VARCHAR(255) NOT NULL COMMENT '세부주종' -- 세부주종
)
COMMENT '주종정보';

-- 주종정보
ALTER TABLE product_type
  ADD CONSTRAINT PK_product_type -- 주종정보 기본키
    PRIMARY KEY (
      type_no -- 주종번호
    );

ALTER TABLE product_type
  MODIFY COLUMN type_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '주종번호';

-- 회원등급
CREATE TABLE member_level (
  level_no INTEGER NOT NULL COMMENT '등급번호', -- 등급번호
  level    INTEGER NOT NULL DEFAULT 0 COMMENT '등급' -- 등급
)
COMMENT '회원등급';

-- 회원등급
ALTER TABLE member_level
  ADD CONSTRAINT PK_member_level -- 회원등급 기본키
    PRIMARY KEY (
      level_no -- 등급번호
    );

ALTER TABLE member_level
  MODIFY COLUMN level_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '등급번호';

-- 픽업확정
CREATE TABLE booking_confirm (
  confirm_no INTEGER NOT NULL COMMENT '픽업확정번호', -- 픽업확정번호
  status     INTEGER NOT NULL DEFAULT 0 COMMENT '상태' -- 상태
)
COMMENT '픽업확정';

-- 픽업확정
ALTER TABLE booking_confirm
  ADD CONSTRAINT PK_booking_confirm -- 픽업확정 기본키
    PRIMARY KEY (
      confirm_no -- 픽업확정번호
    );

ALTER TABLE booking_confirm
  MODIFY COLUMN confirm_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '픽업확정번호';

-- 회원
ALTER TABLE member
  ADD CONSTRAINT FK_member_level_TO_member -- 회원등급 -> 회원
    FOREIGN KEY (
      level_no -- 등급번호
    )
    REFERENCES member_level ( -- 회원등급
      level_no -- 등급번호
    );

-- 구매자
ALTER TABLE buyer
  ADD CONSTRAINT FK_member_TO_buyer -- 회원 -> 구매자
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 판매자
ALTER TABLE seller
  ADD CONSTRAINT FK_member_TO_seller -- 회원 -> 판매자
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 게시판
ALTER TABLE board
  ADD CONSTRAINT FK_member_TO_board -- 회원 -> 게시판
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT FK_board_TO_comment -- 게시판 -> 댓글
    FOREIGN KEY (
      board_no -- 게시글번호
    )
    REFERENCES board ( -- 게시판
      board_no -- 게시글번호
    );

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT FK_member_TO_comment -- 회원 -> 댓글
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 댓글
ALTER TABLE comment
  ADD CONSTRAINT FK_comment_alert_TO_comment -- 댓글 알림 -> 댓글
    FOREIGN KEY (
      comment_alert -- 알림번호
    )
    REFERENCES comment_alert ( -- 댓글 알림
      comment_alert -- 알림번호
    );

-- 좋아요
ALTER TABLE board_like
  ADD CONSTRAINT FK_member_TO_board_like -- 회원 -> 좋아요
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 좋아요
ALTER TABLE board_like
  ADD CONSTRAINT FK_board_TO_board_like -- 게시판 -> 좋아요
    FOREIGN KEY (
      board_no -- 게시글번호
    )
    REFERENCES board ( -- 게시판
      board_no -- 게시글번호
    );

-- 알림
ALTER TABLE Temporary3
  ADD CONSTRAINT FK_member_TO_Temporary3 -- 회원 -> 알림
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 알림
ALTER TABLE Temporary3
  ADD CONSTRAINT FK_comment_TO_Temporary3 -- 댓글 -> 알림
    FOREIGN KEY (
      comment_no -- 댓글번호
    )
    REFERENCES comment ( -- 댓글
      comment_no -- 댓글번호
    );

-- 상품
ALTER TABLE product
  ADD CONSTRAINT FK_product_type_TO_product -- 주종정보 -> 상품
    FOREIGN KEY (
      type_no -- 주종번호
    )
    REFERENCES product_type ( -- 주종정보
      type_no -- 주종번호
    );

-- 리뷰
ALTER TABLE review
  ADD CONSTRAINT FK_product_TO_review -- 상품 -> 리뷰
    FOREIGN KEY (
      product_no -- 상품번호
    )
    REFERENCES product ( -- 상품
      product_no -- 상품번호
    );

-- 리뷰
ALTER TABLE review
  ADD CONSTRAINT FK_buyer_TO_review -- 구매자 -> 리뷰
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES buyer ( -- 구매자
      member_no -- 회원번호
    );

-- 리뷰
ALTER TABLE review
  ADD CONSTRAINT FK_booking_confirm_TO_review -- 픽업확정 -> 리뷰
    FOREIGN KEY (
      confirm_no -- 픽업확정번호
    )
    REFERENCES booking_confirm ( -- 픽업확정
      confirm_no -- 픽업확정번호
    );

-- 재고
ALTER TABLE stock
  ADD CONSTRAINT FK_seller_TO_stock -- 판매자 -> 재고
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES seller ( -- 판매자
      member_no -- 회원번호
    );

-- 재고
ALTER TABLE stock
  ADD CONSTRAINT FK_product_TO_stock -- 상품 -> 재고
    FOREIGN KEY (
      product_no -- 상품번호
    )
    REFERENCES product ( -- 상품
      product_no -- 상품번호
    );

-- 예약
ALTER TABLE booking
  ADD CONSTRAINT FK_buyer_TO_booking -- 구매자 -> 예약
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES buyer ( -- 구매자
      member_no -- 회원번호
    );

-- 예약
ALTER TABLE booking
  ADD CONSTRAINT FK_seller_TO_booking -- 판매자 -> 예약
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES seller ( -- 판매자
      member_no -- 회원번호
    );

-- 예약
ALTER TABLE booking
  ADD CONSTRAINT FK_cart_TO_booking -- 장바구니 -> 예약
    FOREIGN KEY (
      cart_no -- 장바구니번호
    )
    REFERENCES cart ( -- 장바구니
      cart_no -- 장바구니번호
    );

-- 예약
ALTER TABLE booking
  ADD CONSTRAINT FK_booking_alert_TO_booking -- 예약 알림 -> 예약
    FOREIGN KEY (
      booking_alert -- 알림번호
    )
    REFERENCES booking_alert ( -- 예약 알림
      booking_alert -- 알림번호
    );

-- 예약
ALTER TABLE booking
  ADD CONSTRAINT FK_booking_confirm_TO_booking -- 픽업확정 -> 예약
    FOREIGN KEY (
      confirm_no -- 픽업확정번호
    )
    REFERENCES booking_confirm ( -- 픽업확정
      confirm_no -- 픽업확정번호
    );

-- 메세지
ALTER TABLE message
  ADD CONSTRAINT FK_member_TO_message -- 회원 -> 메세지
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 메세지
ALTER TABLE message
  ADD CONSTRAINT FK_message_alert_TO_message -- 메세지 알림 -> 메세지
    FOREIGN KEY (
      message_alert -- 알림번호
    )
    REFERENCES message_alert ( -- 메세지 알림
      message_alert -- 알림번호
    );

-- 장바구니
ALTER TABLE cart
  ADD CONSTRAINT FK_buyer_TO_cart -- 구매자 -> 장바구니
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES buyer ( -- 구매자
      member_no -- 회원번호
    );

-- 장바구니
ALTER TABLE cart
  ADD CONSTRAINT FK_stock_TO_cart -- 재고 -> 장바구니
    FOREIGN KEY (
      stock_no -- 재고번호
    )
    REFERENCES stock ( -- 재고
      stock_no -- 재고번호
    );

-- 게시글태그
ALTER TABLE board_tag
  ADD CONSTRAINT FK_tag_TO_board_tag -- 태그 -> 게시글태그
    FOREIGN KEY (
      tag_no -- 태그번호
    )
    REFERENCES tag ( -- 태그
      tag_no -- 태그번호
    );

-- 게시글태그
ALTER TABLE board_tag
  ADD CONSTRAINT FK_board_TO_board_tag -- 게시판 -> 게시글태그
    FOREIGN KEY (
      board_no -- 게시글번호
    )
    REFERENCES board ( -- 게시판
      board_no -- 게시글번호
    );