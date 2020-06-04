lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
    organization := "co.edu.udea",
    name := "RentRooms",
    version := "1.3.0",
    scalaVersion := "2.12.3",
    libraryDependencies += guice,
    libraryDependencies += jdbc,
    libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41",
    libraryDependencies += "com.github.daddykotex" %% "courier" % "2.0.0",
    libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
  )
