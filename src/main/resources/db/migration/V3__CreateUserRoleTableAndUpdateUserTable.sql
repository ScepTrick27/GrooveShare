CREATE TABLE s3_user_role
(
    id INT IDENTITY(1,1) NOT NULL,
    user_id INT NOT NULL,
    role_name NVARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UQ_User_Role UNIQUE (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES s3_user (user_id)
);