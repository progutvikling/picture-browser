--
-- Changed the default value of 'blocked' to 0.
--

ALTER TABLE `PictureBrowser`.`images` CHANGE COLUMN `blocked` `blocked` TINYINT(1) NULL DEFAULT 0  ;