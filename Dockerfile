FROM openjdk:8-jdk-alpine

ARG JAR_FILE

RUN mkdir -p /apps
COPY ./target/${JAR_FILE} /apps/app.jar
COPY entrypoint.bash /apps/entrypoint.bash

RUN chmod +x /apps/entrypoint.bash
EXPOSE 8080
CMD ["/apps/entrypoint.bash"]