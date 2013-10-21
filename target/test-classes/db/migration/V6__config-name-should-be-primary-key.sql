-- 
-- No point in having an id field. We want our config names to be
-- unique, so why not make the 'name' field primary key? This also
-- makes it easier to insert new configurations because we can use ON
-- DUPLICATE KEY UPDATE in our queries. This way, if the config
-- already exists, its value will be updated. If not, a new config
-- will be inserted.
--

ALTER TABLE `PictureBrowser`.`configs` DROP COLUMN `id` , CHANGE COLUMN `name` `name` VARCHAR(64) NOT NULL DEFAULT 'default'  
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`name`) ;
