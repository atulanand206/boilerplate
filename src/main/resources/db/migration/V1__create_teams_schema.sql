CREATE TABLE IF NOT EXISTS teams
(
  id            UUID         NOT NULL,
  name          VARCHAR(100) NOT NULL UNIQUE,
  CONSTRAINT teams_pkey_id PRIMARY KEY (id),
  CONSTRAINT teams_pkey_name PRIMARY KEY (name)
);