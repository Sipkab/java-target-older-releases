# java-target-older-releases

[![Build status](https://img.shields.io/azure-devops/build/sipkab/9d59d82f-91d9-4ebe-afde-9576467ac8b6/5/master)](https://dev.azure.com/sipkab/java-target-older-releases/_build?definitionId=5)

Example project that uses new Java language features while still able to run on Java 8. The project uses the [saker.build system](https://github.com/sakerbuild/saker.build).

See the related blogpost [Using switch expressions, text blocks, and var on Java 8](https://saker.build/blog/java_target_older_releases/) for more information.

The sources for the example is in the [Main.java](src/test/Main.java) file.

## Building

You can download saker.build using the following command:

```
curl -L https://api.nest.saker.build/bundle/download/saker.build-v0.8.12 -o saker.build.jar
# for PowerShell:
Invoke-WebRequest "https://api.nest.saker.build/bundle/download/saker.build-v0.8.12" -OutFile saker.build.jar
```

Or see the [installation guide](https://saker.build/saker.build/doc/installation.html) for other options like IDE plugins.

You need Java 14 to build the project which should be specified in the following build command:

```
java -jar saker.build.jar -bd build -EUsaker.java.jre.install.locations=path/to/jdk14 export saker.build
```

(Replace `path/to/jdk14` with the path to the Java home of the JDK 14 installation. You can download JDK14 from [AdoptOpenJDK](https://adoptopenjdk.net/archive.html?variant=openjdk14).)

## Running

To check that you can indeed use new language feature on Java 8, run the following on Java 8:

```
java -jar build/saker.jar.create/output.jar
```

You can also view the test run as [part of the CI build on Azure Pipelines](https://dev.azure.com/sipkab/java-target-older-releases/_build/results?buildId=53&view=logs&j=12f1170f-54f2-53f3-20dd-22fc7dff55f9&t=59a85588-b0ba-5043-24c4-d9e29d89c6f6).

The output should be similar to the following:

```plaintext
java.version: 1.8.0_221

Switch expression a -> 1
Switch expression b -> 2
Switch expression c -> 2
Switch expression d -> 0

Text block:
  Lorem ipsum dolor sit amet, consectetur adipiscing elit,
sed do eiusmod tempor incididunt ut labore et dolore magna
aliqua. Ut enim ad minim veniam, quis nostrud exercitation
ullamco laboris nisi ut aliquip ex ea commodo consequat.

Local type inference:
The following number is 1: 1

Anonymous local type inference:
The following number is 33: 33

Private interface methods:
The following number is 9: 9
The following number is 9123: 9123

Generic subclass with diamond operator:
The following number is 24: 24

Effectively final closeable:
Inside try-with-resources.
Closing.

Pattern matching:
Integer 123: Not string. (123)
String __abc: abc
```

