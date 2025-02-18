-- V2__Create_payment_table.sql

CREATE TABLE IF NOT EXISTS payment (
                                       id SERIAL PRIMARY KEY,
                                       order_id BIGINT,
                                       cost DECIMAL(15,2) NOT NULL,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       wallet_id BIGINT,
                                       is_canceled BOOLEAN,
                                       CONSTRAINT fk_wallet FOREIGN KEY (wallet_id) REFERENCES wallet(id)
);
