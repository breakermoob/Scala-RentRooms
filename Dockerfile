FROM openjdk:8

RUN \
curl -L -o sbt-1.3.9.deb http://dl.bintray.com/sbt/debian/sbt-1.3.9.deb && \
dpkg -i sbt-1.3.9.deb && \
rm sbt-1.3.9.deb && \
apt-get update && \
apt-get install -y sbt=1.0.4 && \
sbt sbtVersion

WORKDIR /RentRooms

COPY . /RentRooms

EXPOSE 9000

CMD sbt run
