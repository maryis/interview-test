## Project Design:

 A Rest API with following layers:
	 - controller : includes 3 flight-company APIs
	 - service: service layer by using interfaces and concrete classes
	 - repository layer for two flight company with mock data
	 - domain includes models. I changed them to super/sub classes to achieve a degree of loosely-coupling in project
	 - exception: by using a global exception-handler, I managed exceptions in controller level

## Other Parts:

Tests
	 - unit tests
	 - integration test

Authentication
	 - basic authentication using spring security
	 - it operates like a proxy design pattern 

API Documents 
	- based on OpenAPI definition and saved as "openapi.yaml" in root project

## Next steps:

- separating project to 3 independent micro services, then using spring cloud projects if it needs to be extended in future:   

    - a service discovery project like Eureka
    - a central config center  
    - a central log server  like zipkin
    - have two or more instances of each microservice and using a load balancer like netflix robin for decreasing latency and increasing availability  

  
- to support CI/CD better, having:  

    - a docker file for each project  
    - a jenkins file for each project 

 (I am not proficient in CI/CD tools, but I love to engage in it in real projects)  
  
  - using JWT instead of basic Authentication  spring security using JWT instead of basic Authentication  
 