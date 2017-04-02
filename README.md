# Zebra Backend

## Local configuration
All required configuration is stored in *application-dev-sample.yml* file. Copy it do *application-dev.yml* and add proper configuration information.

To make this file be loaded during startup you need to activate app profile *dev*. Easiest way in local setup is to set environment variable *SPRING_PROFILES_ACTIVE*.
 
Right now only required configuration is PostgreSQL connection data.

## Run
You can run application using *./gradlew bootRun* command or directly from your IDE.

## Test

For writing and running test use Junit 5.   

### Integration Tests
Running integration tests requires configuration of external service e.g. database.


Basic configuration is prepared in *test* profile. This profile must be first activated profile. Second profile should contains information about those connections.
  
E.g. *SPRING_ACTIVE_PROFILES=test,dev* for local environment or *SPRING_ACTIVE_PROFILES=test,travis* for CI.
 
To activate proper configuration in test add *@IntegrationTest* to your test class.

When running first class annotated with *@IntegrationTest* DB will be cleaned and all migrations will be resolved. It is recommended to have different database to perform integration tests.

You can also apply initial database data by adding @DatabaseSetup with proper xml file for specific test or test classes.

Test can be run using *./gradlew test*.

## DB migrations
Flyway is used to manage database migrations.

All migration are stored in *src/resources/db/*.

They are divided into two categories:
* /migration/ - mandatory migrations
* /dev/ - data used for testing

Both directories should be maintained and used common name pattern.
There is integration test that will check whether we can do migration from scratch to full DB. 

## Logging
Application uses SL4J as logger facade and Logback as a logger infrastructure provider. Use first to log from your classes and write your appenders configuration in *logback-spring.xml*.
 
Currently Logback is writing logs only to default output. 

## Docker
*docker-compose.yml* file is provided to setup development environment contains:
- PostgreSQL
- RabbitMQ
More details about used image available on Docker library website.

Example usage: *docker-compose up* run in root project directory