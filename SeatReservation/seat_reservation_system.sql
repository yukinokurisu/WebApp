SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS evetnts;
DROP TABLE IF EXISTS shops;
DROP TABLE IF EXISTS tickets;




/* Create Tables */

CREATE TABLE evetnts
(
	event_id int(10) unsigned NOT NULL AUTO_INCREMENT,
	shop_name varchar(100),
	-- イベント名
	title varchar(255) COMMENT 'イベント名',
	capacity int(11),
	-- イベントの説明
	description text DEFAULT 'null' COMMENT 'イベントの説明',
	start_at datetime,
	PRIMARY KEY (event_id)
);


CREATE TABLE shops
(
	shop_id int unsigned NOT NULL AUTO_INCREMENT,
	shop_name varchar(100),
	password varchar(100) NOT NULL,
	PRIMARY KEY (shop_id)
);


CREATE TABLE tickets
(
	event_id int(10) unsigned NOT NULL,
	-- ランダムに発行される文字列。認証に用いるパスワードのようなもの。
	identification_code  varchar(255) NOT NULL COMMENT 'ランダムに発行される文字列。認証に用いるパスワードのようなもの。',
	-- お客様に発行した日時
	sold_at datetime DEFAULT null COMMENT 'お客様に発行した日時',
	-- お客様が入場した日時
	punched_at datetime DEFAULT null COMMENT 'お客様が入場した日時',
	PRIMARY KEY (identification_code )
);



