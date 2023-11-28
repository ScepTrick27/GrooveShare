CREATE TABLE s3_post
(
    post_id INT IDENTITY(1,1) PRIMARY KEY,
    content NVARCHAR(250),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES s3_user(user_id)
);