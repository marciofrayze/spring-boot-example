[![Build Status](https://travis-ci.org/mfdavid/spring-boot-example.svg?branch=master)](https://travis-ci.org/mfdavid/spring-boot-example)

# Spring Boot Example
This project is a simples WEB (REST + Action Based) application using many Spring components.

The goal is to test Spring and some other tools and create a place were people can see them in action and maybe use it
as a quick start for other projects.

**This is a work in progress.**

## Components and plugins used in this demo
- Spring Boot
- Spring MVC
- Spring Data
- Spring REST Docs
- Springfox
- OWASP Dependency Check
- Checkstyle
- Gradle Versions Plugin (dependencyUpdates)

## Demo

http://mfdavid-spring-boot-example.herokuapp.com

Please note that this project is deployed on a free heroku tier and may take up to 1 minute to "wake up" the dyno/server on the first request.

## Requirements
- JDK 11
- Gradle

## How to compile (without dependency security analysis)
- ```./gradlew clean restdoc jacocoTestReport checkstyleMain checkstyleTest build --stacktrace --info --warning-mode=all```

## How to compile (with dependency security analysis)
- ```./gradlew clean restdoc jacocoTestReport checkstyleMain checkstyleTest dependencyCheckAnalyze build --stacktrace --info --warning-mode=all```

The first time you run this command it will take many minutes to execute, since it will download the NDC database.
The second time should be much faster.

## Test reports
After compiling the project with one the commands above, a new test report will be available at:
- build/reports/jacoco/test/html/index.html

## Dependency security analysis
When compiling the project (with dependency security analysis), the report of known vulnerabilities will be available
at:
- build/reports/security-report/dependency-check-report.html

## Checkstyle report
After compiling, a *checkstyle* report (according to the rules defined at 
config/checkstyle/checkstyle.xml) will be generated at:
- build/reports/checkstyle/main.html
- build/reports/checkstyle/test.html 

## Listing the dependencies tree
- ```./gradlew dependencies --stacktrace --info --warning-mode=all```

## Checking for outdated dependencies
- ```./gradlew dependencyUpdates -Drevision=release --stacktrace --info --warning-mode=all``` 

## Listing other available gradle tasks
- ```./gradlew tasks```

## Starting the server
Spring Boot uses the concept of "fat jar". The entire application server is self contained in a *jar* file.
To start the server, run:
- ```java -jar build/libs/spring-boot-example-0.0.1-SNAPSHOT.jar```

Or, if you prefer, you may compile and start the application server using the bootRun command such as:
- ```./gradlew clean restdoc jacocoTestReport bootRun --stacktrace --info --warning-mode=all```
 
## Known issues
- The current version of the H2 database has a security flaw and do not recommend using it in production. 
More details can be seen by executing the script  "generateDependencyCheckAnalyzerReport.sh".  
