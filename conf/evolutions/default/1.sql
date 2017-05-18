# --- !Ups

CREATE TABLE tab
(
  id         INT UNSIGNED PRIMARY KEY           NOT NULL AUTO_INCREMENT,
  song       VARCHAR(255)                       NOT NULL,
  artist     VARCHAR(255)                       NOT NULL,
  version    SMALLINT UNSIGNED                  NOT NULL,
  album      VARCHAR(255)                       NOT NULL,
  composer   VARCHAR(255)                       NOT NULL,
  genre      VARCHAR(255)                       NOT NULL,
  year       SMALLINT UNSIGNED                  NOT NULL,
  file_type  VARCHAR(3)                         NOT NULL,
  comment    TEXT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
);

# --- !Downs

DROP TABLE tab;
