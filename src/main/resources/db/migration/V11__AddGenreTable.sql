CREATE TABLE s3_genre (
    id INT IDENTITY(1,1) PRIMARY KEY,
    genre NVARCHAR(255) NOT NULL
);

INSERT INTO s3_genre (genre) VALUES
    ('Blues'),
    ('Classical'),
    ('Country'),
    ('Christian & Gospel'),
    ('Electronic'),
    ('Folk'),
    ('Hip Hop/Rap'),
    ('Jazz'),
    ('Metal'),
    ('Musicals'),
    ('New Age'),
    ('Opera'),
    ('Pop'),
    ('Rock'),
    ('R&B/Soul'),
    ('Reggae'),
    ('World'),
    ('Lo-fi');