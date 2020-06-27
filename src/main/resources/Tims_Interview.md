#Overview

Ecommerce is mostly driven by integrations and the systems we work with are Java and mostly some forms of Web services. As such, it will be usefull for us to understand your skills and your level of thought around problems and how you solve them. This exercise has no right or wrong answer. We are also consultants so presenting ideas clearly and effectively is 50% of our job. With that, here is the exercise below:


1. Develop a Spring Boot Application: https://spring.io/projects/spring-boot
2. Your application should run, and be testable
3. During our next demo, you should be able to run, test and screenshare it to us
4. Below are the acceptance criterias for the application.


## Acceptance Criteria

1. The application runs using spring boot, and any Java version you wish
2. An API exists that is able to accept an GET request, and return a resource with a small subset of JSON data
3. The JSON data returned is in the format below:

"interview": {
        "title": "Tims interview",
        "status" "OK"
       	}

4. There is a POST endpoint, this endpoint accepts information, and validates that the interview request is well formed. The POST endpoint accepts an interview object

"interview": {
        "title": "Another interview",
        "type" "REQUEST"
       	}


5. You are able to invoke the API's using either code, or another testing tool