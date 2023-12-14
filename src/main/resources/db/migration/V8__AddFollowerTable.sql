CREATE TABLE s3_follow (
                        follow_id INT PRIMARY KEY IDENTITY(1,1),
                        follower_id INT,
                        followee_id INT,
                        FOREIGN KEY (follower_id) REFERENCES s3_user(user_id),
                        FOREIGN KEY (followee_id) REFERENCES s3_user(user_id)
);