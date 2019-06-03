# Host: localhost  (Version 5.7.26)
# Date: 2019-06-03 16:04:33
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "users"
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` int(3) DEFAULT '1',
  `email` varchar(255) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `creditCard` int(11) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "users"
#

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ami','123',1,'as@as.com',NULL,NULL,NULL,NULL),(2,'sharon','123',2,NULL,NULL,NULL,NULL,NULL),(3,'amii','123',1,'asq@as.com',190867,NULL,'amnon','vetamar'),(4,'slomi','123',1,'shlomi@as.com',190867,123456789,'amnon','vetamar'),(5,'slomsi','123',1,'shldomi@as.com',190867,123456789,'amnon','vetamar'),(6,'abc','123',1,'abc@as.com',190867,123456789,'amnon','vetamar'),(7,'def','123',1,'def@as.com',190867,123456789,'amnon','vetamar');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
