ALTER TABLE s3_post
    ADD genre_id INT;

-- Add the foreign key constraint for the genre_id column
ALTER TABLE s3_post
    ADD CONSTRAINT FK_s3_post_genre
        FOREIGN KEY (genre_id)
            REFERENCES s3_genre(id);