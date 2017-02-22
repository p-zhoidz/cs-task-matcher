# Pattern Matching App

## Prerequisites:
To build the application locally you will need:
- JDK 8
- maven version 3.1 or higher

## Configuration
Search engine can be configured through the configuration.properties file.
By default, it has the following configuration:
- max.edit.distance  - Maximum edit distance (https://en.wikipedia.org/wiki/Edit_distance).
                         Should not be negative. Default value is 1.
- input.file.path    - Absolute path to the input file. Required. Does not have default value.
- patterns.file.path - Absolute path to the patterns file. Required. Does not have default value.


## Building
Clone this repo and run the following command:
```
mvn clean install
```

## Running
1. Find executable jar file. (By default should be in the local maven repository.
    For instance: ~/.m2/repository/com/cushing/software/tasks/pattern-matching/0.0.1-SNAPSHOT)

2. Run the following: java -jar pattern-matching-{VERSION}.jar

