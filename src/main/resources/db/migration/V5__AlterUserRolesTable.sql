ALTER TABLE s3_user_role
DROP CONSTRAINT FK__s3_user_r__user___39E294A9;

ALTER TABLE s3_user_role
DROP CONSTRAINT UQ_User_Role;

ALTER TABLE s3_user_role
DROP COLUMN user_id;