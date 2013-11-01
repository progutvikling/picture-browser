--
-- Changed the default value of 'blocked' to 0.
--

ALTER TABLE images CHANGE COLUMN blocked
      blocked TINYINT(1) NULL DEFAULT 0;
