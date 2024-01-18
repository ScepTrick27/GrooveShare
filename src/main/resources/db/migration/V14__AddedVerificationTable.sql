CREATE TABLE s3_verification (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT REFERENCES s3_user(user_id),
    photo VARBINARY(MAX),
    status NVARCHAR(255)
);