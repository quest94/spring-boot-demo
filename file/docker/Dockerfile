# 基础镜像
FROM openjdk:8-jdk-alpine

# 维护者信息
MAINTAINER qyz12321@163.com

# 用于生成镜像的元数据的标签信息
LABEL version="1.0" description="Spring Boot 骨架项目."

# 声明对外开放端口
EXPOSE 8888

# 创建一个数据卷挂载点，主机上对应的目录是自动生成的，无法指定
VOLUME /opt/skeleton/logs

# 复制本地主机的上的 spring-boot-skeleton-0.0.1-SNAPSHOT.jar 到镜像中的 /opt/skeleton 下
COPY spring-boot-skeleton-0.0.1-SNAPSHOT.jar /opt/skeleton/spring-boot-skeleton.jar

# 配置工作目录
WORKDIR /opt/skeleton

# 镜像的默认入口命令
# -Djava.security.egd=file:/dev/./urandom 可解决tomcat可能启动慢的问题
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","spring-boot-skeleton.jar"]


