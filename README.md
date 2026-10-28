# Movies

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

Pet project about movies

Here I try various libraries and architectural approaches that I find convenient and interesting.

I am also trying to show the correct, in my opinion, approach to organizing work with tasks in a project.

I work on the project in my free time, so there may be long breaks between commits.

Any help is welcome! I hope you find something useful/interesting for yourself.

## Build environment

Use JDK 21 to run Gradle locally and in CI. In Android Studio, select JDK 21
under Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK.
For command-line builds, point `JAVA_HOME` to a JDK 21 installation.

Java and Kotlin bytecode target JVM 17. Detekt 1.23.8 is incompatible with
running on JDK 25, so use JDK 21 for the project build.
