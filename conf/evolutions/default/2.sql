# --- !Ups
CREATE TABLE PUBLIC_BUBBLE.COMMITTEE
(
    ID SERIAL PRIMARY KEY,
    DESCRIPTION TEXT,
    IMAGE_URL TEXT,
    POSITION INTEGER
);
Commit;

# --- !Downs
drop table PUBLIC_BUBBLE.COMMITTEE;
