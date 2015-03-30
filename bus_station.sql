-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.0.17-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 bus_info 的数据库结构
CREATE DATABASE IF NOT EXISTS `bus_info` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bus_info`;


-- 导出  表 bus_info.arrival_time 结构
CREATE TABLE IF NOT EXISTS `arrival_time` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUS_NUM` int(11) NOT NULL,
  `STATION_CODE` varchar(10) NOT NULL,
  `ARRIVAL_TIME` datetime NOT NULL,
  KEY `LOG_ID` (`LOG_ID`),
  KEY `BUS_NUM_STATION_CODE` (`BUS_NUM`,`STATION_CODE`),
  KEY `FK_arrival_time_station_stats` (`STATION_CODE`),
  CONSTRAINT `FK_arrival_time_bus_nums` FOREIGN KEY (`BUS_NUM`) REFERENCES `bus_nums` (`BUS_NUM`),
  CONSTRAINT `FK_arrival_time_station_stats` FOREIGN KEY (`STATION_CODE`) REFERENCES `station_stats` (`STATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公交到站时间';

-- 数据导出被取消选择。


-- 导出  表 bus_info.bus_nums 结构
CREATE TABLE IF NOT EXISTS `bus_nums` (
  `BUS_NUM` int(11) NOT NULL AUTO_INCREMENT,
  `LISENCE_NUM` char(10) NOT NULL,
  `ROUTE_NAME` char(10) NOT NULL,
  `DEPARTMENT_TIME` datetime NOT NULL,
  PRIMARY KEY (`BUS_NUM`),
  KEY `FK_bus_nums_route_stats` (`ROUTE_NAME`),
  CONSTRAINT `FK_bus_nums_route_stats` FOREIGN KEY (`ROUTE_NAME`) REFERENCES `route_stats` (`ROUTE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公交车单趟编号';

-- 数据导出被取消选择。


-- 导出  表 bus_info.route_stats 结构
CREATE TABLE IF NOT EXISTS `route_stats` (
  `ROUTE_NAME` char(10) NOT NULL,
  `STATION_CODE` varchar(10) NOT NULL,
  `EFFECTIVE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ROUTE_NAME`),
  KEY `FK_route_stats_station_stats` (`STATION_CODE`),
  CONSTRAINT `FK_route_stats_station_stats` FOREIGN KEY (`STATION_CODE`) REFERENCES `station_stats` (`STATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公交线路表，因同一公交线路可能会变化，所以要加时间戳';

-- 数据导出被取消选择。


-- 导出  表 bus_info.station_stats 结构
CREATE TABLE IF NOT EXISTS `station_stats` (
  `STATION_CODE` varchar(10) NOT NULL,
  `STATION_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`STATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站台编号对应的站台名';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
