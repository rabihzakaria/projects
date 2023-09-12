CREATE TABLE IF NOT EXISTS Transaction (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              transaction_id VARCHAR(255),
                              operation_id VARCHAR(255),
                              accounting_date DATE,
                              value_date DATE,
                              type VARCHAR(255), -- Assuming TransactionType is a string enum
                              amount DOUBLE,
                              currency VARCHAR(255),
                              description VARCHAR(1000)

);