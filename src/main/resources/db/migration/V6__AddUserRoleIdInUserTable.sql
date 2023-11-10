ALTER TABLE s3_user
    ADD user_role_id INT;

ALTER TABLE s3_user
    ADD CONSTRAINT FK_s3_user_s3_user_role
        FOREIGN KEY (user_role_id) REFERENCES s3_user_role (id);