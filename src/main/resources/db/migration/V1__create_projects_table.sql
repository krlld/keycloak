CREATE TABLE projects
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT    NOT NULL,
    description TEXT    NOT NULL,
    budget      DECIMAL NOT NULL,
    start_at    DATE    NOT NULL,
    end_at      DATE    NOT NULL,
    status      TEXT    NOT NULL
);