WITH RankedBalances AS (
    SELECT
        client_id,
        client_name,
        client_balance_date,
        client_balance_value,
        ROW_NUMBER() OVER (
            PARTITION BY client_id, client_name, client_balance_date, client_balance_value
            ORDER BY client_id
        ) AS rn
    FROM ClientBalance
)
DELETE FROM ClientBalance
WHERE client_id IN (
    SELECT client_id
    FROM RankedBalances
    WHERE rn > 1
);
