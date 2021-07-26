## Contact Form
Contact form is a Spring boot project with integration of Thymeleaf that allows users to send feedback and stores this feedback in the database. 
## Running the project
As IDE, Intellij IDEA is recommended. Start by cloning the repository with `git clone https://github.com/TStrecha/ContactForm`.

Make sure the service will be able to connect to the PostgreSQL database. All the logins and database name are in `src/main/resources/application.yaml`. Don't worry about tables and columns, liquibase will take care of it.

Last but not least make sure that port `8080` isn't taken by another application. If it is, you can easily change the port of the service in `src/main/resources/application.yaml`.

If you have all this, you can run the main class `/src/main/java/eu.lundegaard.contactform/ContactFormApplication.java` or in root directory call `mvn spring-boot:run` command. Then, the contact form will be availible at [http://localhost:8080/contact-form](http://localhost:8080/contact-form)

### Running in docker
To run the service in a docker, make sure you have a mysql container running in your docker. Then run `mvn clean package`. This will create a `.jar` executable file in the target directory.

Now you can proceed to run a command `docker build -t contact-form .` in the root directory of the service. This will build a new image called `contact-form`. Now, simply start the container using command `docker run -dp 3000:3000 contact-form` and the application should be available at your docker ip on port `3000`. Feel free to change the port of the docker container to your needs.

## Testing
Once you have run the service, Swagger should be available in a browser at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

Swagger will show you all the endpoints available and will suggest the structure for each request. 

You can also test the service through other applications for REST API, but I really suggest using Swagger as it is optimized to show you everything you need to pass to the request.

## Using the service
First of all you need to create request types in the database table `request_type`. Please note, that the service keeps `request_types` in the cache. If you want to reset this cache, just call the `/v1/cache/request-type/reset` endpoint.

## Contribution
### Entities
If you have changed an entity, run `mvn liquibase:generateChangeLog` to generate the diff, check if everything is alright, move it into `src/main/resources/liquibase/{current-year}` and include it in `src/main/resources/liquibase/changelog.yaml` if necessary.

### Adding new features
If you have added a new feature, please make a Unit test to test this feature. Make a few test cases in which at least one will succeed and one will fail on purpose. Also, make sure all test including the previous ones do work by running `mvn test`. If not, change previous tests to make them work.

### Validation
In terms of validation of entities or DTOs, please use the `UserValidationErrorDTO` class. Do not validate through Spring annotations. The `UserValidationErrorDTO` class ensures that every validation failure will throw the same error and will provide more information for the client than a normal Spring annotation would.

### Changing pom
If you have changed `pom.xml` run `mvn dependency:analyze` to list unused dependencies. Look through that list and remove those dependencies that are not needed.

### Changing the port
If you have changed the port in `src/main/resources/application.yaml`, change it back to `8080`.
