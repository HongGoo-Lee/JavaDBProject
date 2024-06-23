USE kream;

-- ���̺� ����: Users (ȸ������)
CREATE TABLE Users (
    user_id VARCHAR(50) PRIMARY KEY,
    pw VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    birth_day DATE,
    phone_num VARCHAR(20),
    gender BIT, --boolean ��� ���
    state BIT, --0�� 1�� ������
    manager BIT --0�� 1�� ������
);

select *
from users

-- ���̺� ����: category (ī�װ�)
CREATE TABLE category (
    category_id BIGINT PRIMARY KEY IDENTITY(1,1),
    category_name VARCHAR(100) NOT NULL,
    image_URL VARCHAR(255)
);

-- ���̺� ����: Product (����)
CREATE TABLE Product (
    product_id BIGINT PRIMARY KEY IDENTITY(1,1),
    product_name VARCHAR(100) NOT NULL,
    image_URL VARCHAR(255),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- ���̺� ����: Sale (�Ǹ�)
CREATE TABLE Sale (
    sale_id BIGINT PRIMARY KEY IDENTITY(1,1),
    user_id VARCHAR(50),
    price BIGINT NOT NULL,
    product_id BIGINT,
    state BIT, -- 0�� 1�� ������
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- ���̺� ����: Buy (����)
CREATE TABLE Buy (
    buy_id BIGINT PRIMARY KEY IDENTITY(1,1),
    user_id VARCHAR(50),
    price BIGINT NOT NULL,
    product_id BIGINT,
    state BIT, -- 0�� 1�� ������
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- ���̺� ����: Trade (�ŷ�)
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

-- Ʈ���� ����: Buy ���̺�
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
            -- ���¸� 1�� ����
            UPDATE Buy SET state = 1 WHERE buy_id = @buy_id;
            UPDATE Sale SET state = 1 WHERE sale_id = @sale_id;

            -- �ŷ��� Trade ���̺� �߰�
            INSERT INTO Trade (buy_id, sale_id, date, price, product_id)
            VALUES (@buy_id, @sale_id, GETDATE(), @price, @product_id);
        END
    END
END
GO

-- Ʈ���� ����: Sale ���̺�
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
            -- ���¸� 1�� ����
            UPDATE Sale SET state = 1 WHERE sale_id = @sale_id;
            UPDATE Buy SET state = 1 WHERE buy_id = @buy_id;

            -- �ŷ��� Trade ���̺� �߰�
            INSERT INTO Trade (buy_id, sale_id, date, price, product_id)
            VALUES (@buy_id, @sale_id, GETDATE(), @price, @product_id);
        END
    END
END
GO
