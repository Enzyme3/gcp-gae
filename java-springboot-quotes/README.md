# Java - Spring Boot - Quotes

Sample Java Spring Boot app
Standalone app that exposes an API that returns a random quote from a hard-coded list
Uses GAE Flex and deployed as a shared VPC

## Pre-Requisites
* No extra pre-requisites

## Deployment Steps
1. Update the `networks` block in `src/main/appengine/app.yaml` to point to the proper vpc and subnet that the app will be deployed to
2. `mvn spring-boot:run` and navigate to http://localhost:8080 to confirm application runs locally
3. `mvn clean package appengine:deploy`

## Validation Steps
1. Once deployment completes, run `gcloud app browse -s quotes`
2. Confirm that JSON quote is returned