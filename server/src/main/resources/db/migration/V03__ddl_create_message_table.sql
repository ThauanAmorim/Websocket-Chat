CREATE TABLE t_message (
  id BIGSERIAL,
  content TEXT NOT NULL,
  id_chat BIGINT,
  id_sender BIGINT,
  create_date TIMESTAMP(6) NOT NULL,
  CONSTRAINT pk_t_message PRIMARY KEY (id),
  CONSTRAINT fk_t_message_id_chat FOREIGN KEY (id_chat) REFERENCES t_chat (id),
  CONSTRAINT fk_t_message_id_sender FOREIGN KEY (id_sender) REFERENCES t_user (id)
);
