--
-- This field will store the time that the image is added to instagram or twitter.
-- We will use it in our query to get the latest N images.
--

ALTER TABLE `PictureBrowser`.`images` ADD COLUMN `created_time` DATETIME NULL  AFTER `description` ;
