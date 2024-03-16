CREATE TABLE t_chat (
  id BIGSERIAL,
  name VARCHAR(248),
  create_date TIMESTAMP(6) NOT NULL,
  CONSTRAINT pk_t_chat PRIMARY KEY (id)
);

CREATE TABLE t_user_chat (
  id_user BIGINT,
  id_chat BIGINT,
  CONSTRAINT pk_t_user_chat PRIMARY KEY (id_user, id_chat),
  CONSTRAINT fk_t_user_chat_id_user FOREIGN KEY (id_user) REFERENCES t_user (id),
  CONSTRAINT fk_t_user_chat_id_chat FOREIGN KEY (id_chat) REFERENCES t_chat (id)
);