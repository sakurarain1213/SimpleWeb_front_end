# JavaWeb登陆注册说明文档

## 项目部署
IDEA+tomcat

## 更改DBUtil.java文件中JDBC连接信息
- 若使用MySQL数据库： 
    1. 更改连接数据库的用户和密码为自己的
    2. 测试数据库连接

- 若使用其他数据库：
    1. 将数据库驱动名更改位自己所用数据库的驱动名，
    2. 替换数据库连接的驱动包，本项目只提供Mysql的驱动包，其他数据库驱动jar包请自行下载
    3. 测试数据库连接
    
## 新建用户表 
运行下面的建表语句：
```
CREATE TABLE `user` (
  `USERID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USERID`,`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8;
```
到此，项目已可以尝试运行了！



    
