CREATE TABLE s3_like
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    post_id INT,
    FOREIGN KEY (user_id) REFERENCES s3_user(user_id),
    FOREIGN KEY (post_id) REFERENCES s3_post(post_id)
);