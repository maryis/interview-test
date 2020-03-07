
The context is always cached between tests if it is the same configuration. Configuration is determined on loaded configuration files, @MockBean (and friends), @TestPropertySource and some other things. If that is what you use then you will get a new context each time.
Solution:

- user pass in header (and proxy DP to restrict access to get response from server)
- test
- req-res ha Interface ( maybe adapter DP for compliance )

- a general WS client  (composite dp : two categories (tree structure)) : crazy and tough)
- presentation- swagger or openAPI definition



Future steps:
separating project to 3 micro services
-
-
-
then using spring cloud projects if it needs to be extended in future: 
- a service discovery project (like ...)
- a central config center
- a central log server
- ...

and to support CI/CD better, having:
- a docker file for each project
- a jenkins file for each project
(I am not proficient in CI/CD tools, but I love to do it in real projects)

and in spring security using JWT instead of basic Auth(user/pass)
and using dozer mapper to convert a bean to another bean

when busyFlightApi is called, the CrazyAir Api and ToughJetAPI should be called 
and their result should be returned to client(in Json format) after aggregation.
am I right, if yes
1) by calling  CrazyAir Api and ToughJetAPI we should get som data. is it need that I have a DataBase
or I should mock the data?
2) in a real project I am the client of two APIs: CrazyAir Api and ToughJetAPI.
What about this project? am I allowed to change the request-response format, 
or I have to obey their structure as a real project

