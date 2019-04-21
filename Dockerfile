FROM ghostben/java1.8.0_201:1.0.0
ENV LANG C.UTF-8
VOLUME /tmp
ADD target/*-SNAPSHOT.jar /app.jar
RUN /bin/sh -c 'mkdir -p /data/images'         \
    &&  /bin/sh -c 'touch /app.jar'
WORKDIR /
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","app.jar"]
#ENTRYPOINT ["sh","-c","java -Dfile.encoding=UTF-8 -jar app.jar"]
EXPOSE 8088