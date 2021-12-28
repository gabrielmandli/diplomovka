FROM gradle:jdk11

COPY . /diplomovka
RUN cd /diplomovka && gradle build jar -x test