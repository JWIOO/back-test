-- exams 테이블에 데이터 삽입
INSERT INTO exams (id, name, category, price, created_at, updated_at) VALUES (1, 'Exam 1', 'Category 1', 100, NOW(), NOW());
INSERT INTO exams (id, name, category, price, created_at, updated_at) VALUES (2, 'Exam 2', 'Category 2', 200, NOW(), NOW());

-- members 테이블에 데이터 삽입
INSERT INTO members (member_id, name, password, email, address, phone_number, is_anonymous) VALUES (1, 'John Doe', '1234', 'john@example.com', '123 Main St', '01012345678', FALSE);
INSERT INTO members (member_id, name, password, email, address, phone_number, is_anonymous) VALUES (2, 'Jane Smith', '5678', 'jane@example.com', '456 Oak St', '01087654321', FALSE);
