
name := "kastrull-core"

version := "0.1"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
	"org.specs2" %% "specs2-core" % "3.6.4" % "test",
	// "org.specs2" %% "specs2-mock" % "3.6.4" % "test",
	"org.specs2" %% "specs2-scalacheck" % "3.6.4" % "test"
)

// *** avoid java source directories ***
unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(Seq(_))

unmanagedSourceDirectories in Test <<= (scalaSource in Test)(Seq(_))


