USE kream;

-- 테이블 생성: Users (회원정보)
CREATE TABLE Users (
    user_id VARCHAR(50) PRIMARY KEY,
    pw VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    birth_day DATE,
    phone_num VARCHAR(20),
    gender BIT, --boolean 대신 사용
    state BIT, --0과 1을 저장함
    manager BIT --0과 1을 저장함
);

select *
from users

-- 테이블 생성: category (카테고리)
CREATE TABLE category (
    category_id BIGINT PRIMARY KEY IDENTITY(1,1),
    category_name VARCHAR(100) NOT NULL,
    image_URL VARCHAR(255)
);

-- 테이블 생성: Product (물건)
CREATE TABLE Product (
    product_id BIGINT PRIMARY KEY IDENTITY(1,1),
    product_name VARCHAR(100) NOT NULL,
    image_URL VARCHAR(255),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- 테이블 생성: Sale (판매)
CREATE TABLE Sale (
    sale_id BIGINT PRIMARY KEY IDENTITY(1,1),
    user_id VARCHAR(50),
    price BIGINT NOT NULL,
    product_id BIGINT,
    state BIT, -- 0과 1을 저장함
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- 테이블 생성: Buy (구매)
CREATE TABLE Buy (
    buy_id BIGINT PRIMARY KEY IDENTITY(1,1),
    user_id VARCHAR(50),
    price BIGINT NOT NULL,
    product_id BIGINT,
    state BIT, -- 0과 1을 저장함
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- 테이블 생성: Trade (거래)
CREATE TABLE Trade (
    trade_id BIGINT PRIMARY KEY IDENTITY(1,1),
    buy_id BIGINT,
    sale_id BIGINT,
    date DATE,
    price BIGINT NOT NULL,
    product_id BIGINT,
    FOREIGN KEY (buy_id) REFERENCES Buy(buy_id),
    FOREIGN KEY (sale_id) REFERENCES Sale(sale_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
GO

-- 트리거 생성: Buy 테이블
CREATE TRIGGER trg_Buy_Insert
ON Buy
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @buy_id BIGINT, @product_id BIGINT, @price BIGINT, @user_id_buy VARCHAR(50), @sale_id BIGINT;

    SELECT @buy_id = i.buy_id, @product_id = i.product_id, @price = i.price, @user_id_buy = i.user_id
    FROM inserted i;

    IF @buy_id IS NOT NULL
    BEGIN
        SELECT TOP 1 @sale_id = s.sale_id
        FROM Sale s
        WHERE s.product_id = @product_id AND s.price = @price AND s.user_id <> @user_id_buy AND s.state = 0;

        IF @sale_id IS NOT NULL
        BEGIN
            -- 상태를 1로 변경
            UPDATE Buy SET state = 1 WHERE buy_id = @buy_id;
            UPDATE Sale SET state = 1 WHERE sale_id = @sale_id;

            -- 거래를 Trade 테이블에 추가
            INSERT INTO Trade (buy_id, sale_id, date, price, product_id)
            VALUES (@buy_id, @sale_id, GETDATE(), @price, @product_id);
        END
    END
END
GO

-- 트리거 생성: Sale 테이블
CREATE TRIGGER trg_Sale_Insert
ON Sale
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @sale_id BIGINT, @product_id BIGINT, @price BIGINT, @user_id_sale VARCHAR(50), @buy_id BIGINT;

    SELECT @sale_id = i.sale_id, @product_id = i.product_id, @price = i.price, @user_id_sale = i.user_id
    FROM inserted i;

    IF @sale_id IS NOT NULL
    BEGIN
        SELECT TOP 1 @buy_id = b.buy_id
        FROM Buy b
        WHERE b.product_id = @product_id AND b.price = @price AND b.user_id <> @user_id_sale AND b.state = 0;

        IF @buy_id IS NOT NULL
        BEGIN
            -- 상태를 1로 변경
            UPDATE Sale SET state = 1 WHERE sale_id = @sale_id;
            UPDATE Buy SET state = 1 WHERE buy_id = @buy_id;

            -- 거래를 Trade 테이블에 추가
            INSERT INTO Trade (buy_id, sale_id, date, price, product_id)
            VALUES (@buy_id, @sale_id, GETDATE(), @price, @product_id);
        END
    END
END
GO
