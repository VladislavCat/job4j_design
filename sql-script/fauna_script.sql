SELECT FROM fauna WHERE name LIKE '%fish%';
SELECT FROM fauna WHERE avg_age > 10000 and avg_age < 21000;
SELECT FROM fauna WHERE discovery_date is null;
SELECT FROM fauna WHERE discovery_date < '1950-01-01';