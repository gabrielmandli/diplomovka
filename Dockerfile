FROM gradle:jdk11

#WORKDIR /diplomovka
#ADD ./build/libs/diplomovka*.jar diplomovka/diplomovka.jar
#ADD ./wait-for-it.sh /diplomovka/
#
#
#CMD java -jar diplomovka/diplomovka.jar

COPY . /diplomovka
RUN cd /diplomovka && gradle build jar -x test