CREATE TABLE IF NOT EXISTS developers
(
  id            UUID         NOT NULL,
  team_id       UUID         NOT NULL,
  name          VARCHAR(100) NOT NULL,
  phone_number  VARCHAR(10)  NOT NULL,
  CONSTRAINT teams_pkey_id PRIMARY KEY (id)
);