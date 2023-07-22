USE TrocEncheres;
ALTER TABLE `articlesvendus`
	DROP FOREIGN KEY `article_user_fk`;
ALTER TABLE `articlesvendus`
	ADD CONSTRAINT `article_user_fk` FOREIGN KEY (`no_user`) REFERENCES `trocencheres`.`users` (`no_user`) ON UPDATE RESTRICT ON DELETE CASCADE;