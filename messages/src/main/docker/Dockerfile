FROM azul/zulu-openjdk-alpine:8u362-jre-headless


RUN addgroup -g 1000 -S testuser \
        && adduser -u 1000 -S -D -G testuser -s /bin/bash testuser



WORKDIR /home/testuser

RUN wget --quiet  -O /home/testuser/applicationinsights-agent.jar https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.4.10/applicationinsights-agent-3.4.10.jar
COPY applicationinsights.json /home/testuser/applicationinsights.json
COPY maven/*.jar  /home/testuser/app.jar
RUN chown testuser:testuser /home/testuser/*

USER testuser
CMD ["java","-javaagent:/home/testuser/applicationinsights-agent.jar","-jar","/home/testuser/app.jar"]

