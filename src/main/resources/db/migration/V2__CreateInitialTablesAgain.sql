CREATE TABLE s3_user_gender
(
    userGenderId INT IDENTITY(1,1) PRIMARY KEY,
    userGender NVARCHAR(50) UNIQUE
);

CREATE TABLE s3_user
(
    userId INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) UNIQUE,
    password NVARCHAR(50),
    description NVARCHAR(500),
    userGenderId INT NOT NULL,
    FOREIGN KEY (userGenderId) REFERENCES s3_user_gender(userGenderId)
);