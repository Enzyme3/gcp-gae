# Java - Spring Boot - CloudSQL - Users

Sample Java Spring Boot app that connects to CloudSQL (MySQL)
GAE flex app that exposes API to create and get users

## Pre-Requisites
1. All parent pre-reqs
2. Setup CloudSQL instance (MySQL) [doc](https://cloud.google.com/sql/docs/mysql/create-instance)
  * Ensure that [private IP](https://cloud.google.com/sql/docs/mysql/configure-private-ip) is selected and choose the same network that you plan to deploy your GAE app to
  * Sample code uses test password of `test-pw`
3. In the created CloudSQL instance, [create a database](https://cloud.google.com/sql/docs/mysql/create-manage-databases) called `user_store`
4. Ensure that your GAE service account has permissions to access CloudSQL
  * Service account is of form: `PROJECT-ID@appspot.gserviceaccount.com`
  * By default, SA should be assigned `roles/editor`, which provides more than sufficient access
  * If SA has been modified to limit access, ensure that `roles/cloudsql.client` is assigned

# Local verification
1. Update `src/main/docker/Dockerfile`
  * Change line 2 from `ARG JAR_FILE=*.jar` to `ARG JAR_FILE=target/*.jar`
2. Point code to CloudSQL instance
  * From the Cloud SQL console, grab the **instane connection name** of the instance you created
  * **instance connection name** is of form: PROJECT_ID:REGION:INSTANCE_ID
  * Open `/src/main/resources/application.properties` and update `spring.datasource.url` to point to your CloudSQL instance
3. Build image
`docker build -f src/main/docker/Dockerfile -t gae-cloudsql-users .`
4. Run container
`docker run -p 8080:8080 gae-cloudsql-users:latest`
5. Validate
  * Browse to http://localhost:8080 and confirm that an empty JSON block is returned
  * Add a user via `curl http://localhost:8080 -d name=First -d email=sdaf@fasf.com`
  * Browse to http://localhost:8080 and confirm that user is returned

## Deployment Steps
1. Update the `networks` block in `src/main/appengine/app.yaml` to point to the proper vpc and subnet that the app will be deployed to
2. `mvn spring-boot:run` and navigate to http://localhost:8080 to confirm application runs locally
3. `mvn clean package appengine:deploy`

## Validation Steps
1. Once deployment completes, run `gcloud app browse -s users`
  * Confirm that empty JSON block is returned
2. To add users, make a POST request to the app endpoint:
  * `curl https://SERVICE-ENDPOINT -d name=First -d email=sdaf@fasf.com`
3. Confirm that user has been added by hitting `gcloud app browse -s users` again
