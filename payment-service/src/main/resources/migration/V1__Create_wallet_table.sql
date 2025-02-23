-- V1__Create_wallet_table.sql

CREATE TABLE IF NOT EXISTS wallet (
                                      id SERIAL PRIMARY KEY,
                                      user_id BIGINT UNIQUE NOT NULL,
                                      balance DECIMAL(15,2) NOT NULL DEFAULT 0.0,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
