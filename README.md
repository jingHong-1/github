##spring文档 
https://spring.io/guides https://spring.io/guides/gs/serving-web-content/ 
##官方说明文档
https://docs.spring.io/spring/docs/5.2.9.RELEASE/spring-framework-reference/core.html#spring-core
##git文档 
https://docs.github.com/cn/developers/apps/creating-a-github-app 
##okhttp 
https://square.github.io/okhttp/ 
##工具
##posman
##富文本框
https://pandao.github.io/editor.md/
##mvn
https://mvnrepository.com/search?q=org.h2.Driver
##h2
http://www.h2database.com/html/quickstart.html
#Mybatis
http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html
##lombok
https://projectlombok.org/setup/maven
##thymeleaf
https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html
##连接数据库错误
create user if not exists sa password '123';
alter user sa admin true ;
##命令行--自动生成数据库文件
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

##自动生成时需要修改的文件位置
usermapper   commentmapper 110 114 comment_count  questionmapper 132行id
