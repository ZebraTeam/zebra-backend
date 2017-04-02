# Zebra Backend

## Local configuration
All required configuration is stored in *application-dev-sample.yml* file. Copy it do *application-dev.yml* and add proper configuration information.

To make this file be loaded during startup you need to activate app profile *dev*. Easiest way in local setup is to set environment variable *SPRING_PROFILES_ACTIVE*.
 
Right now only required configuration is Postgres DB connection data.

## Run
You can run application using *./gradlew bootRun* command or directly from your IDE.

## Test
Running test requires activation of at least two profiles. First active must be a profile *test*. The other one should be a profile that will provides basic configuration eg. for DB connection.
 
Integration tests will clean DB at start, so it's good to have other schema than application.
You can provide initial test data using DBUnit library. 

You can run test using *./gradlew test*.

## DB migrations
Flyway is used to manage database migrations.

All migration are stored in *src/resources/db/*.

They are divided into two categories:
* /migration/ - mandatory migrations
* /dev/ - data used for testing

Both directories should be maintained and used common name pattern.
There is integration test that will check whether we can do migration from scratch to full DB. 

## API Documentation
Application uses Swagger to document API. You can try API on *http://app/swagger-ui.html* 

## Logging


## Docker
