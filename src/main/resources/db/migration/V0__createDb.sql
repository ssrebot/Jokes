DROP  TABLE IF EXISTS category;
CREATE TABLE category (
  id     serial,
  name    varchar(50),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS joke;
CREATE TABLE joke (
  id     serial,
  content    varchar(1000),
  category_id integer,
  likes    integer DEFAULT 0,
  dislikes integer DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE SEQUENCE my_sequence START 101;