# CS106A Programming Methodology

- Stanford Engineering Everywhere (SEE): <https://see.stanford.edu>
- CS106A: <https://see.stanford.edu/Course/CS106A>
- CS106A Materials: <https://see.stanford.edu/materials/icspmcs106a/ProgrammingMethodologyAllMaterials.zip>
- Karel the Robot Learns Java: <https://cs.stanford.edu/people/eroberts/karel-the-robot-learns-java.pdf>
- The Art and Science of Java: <https://cs.stanford.edu/people/eroberts/books/TheArtAndScienceOfJava/index.html>
- YouTube: <https://www.youtube.com/playlist?list=PL84A56BC7F4A1F852>

# How to run

## CLI

```
$ ./gradlew Assignment1-CollectNewspaperKarel:run
```
or

```
$ cd Assignment1-CollectNewspaperKarel
$ ../gradlew run
```

## Eclipse

### Gradle: The Eclipse Plugin

```
$ ./gradlew eclipse
```

The Eclipse plugins generate files that are used by the Eclipse IDE,
thus making it possible to import the project into Eclipse
(File - Import - General - Existing Projects into Workspace).

### Eclipse Gradle Integration

Install plugin: Help -> Eclipse Marketplace -> Find: Gradle -> Buildship Gradle Integration
File -> Import -> Gradle -> Existing Gradle Project -> Project's root dir

## IntelliJ IDEA

### Gradle: The IDEA Plugin

```
$ ./gradlew idea
```

The IDEA plugin generates files that are used by IntelliJ IDEA,
thus making it possible to open the project from IDEA (File - Open Project)

### IntelliJ IDEA Gradle

https://www.jetbrains.com/help/idea/gradle.html
