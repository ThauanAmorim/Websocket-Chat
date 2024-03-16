CREATE TABLE t_user (
  id BIGSERIAL,
  login VARCHAR(248) NOT NULL,
  create_date TIMESTAMP(6) NOT NULL,
  CONSTRAINT pk_t_user PRIMARY KEY (id)
);