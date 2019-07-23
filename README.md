# restaurant-app-springboot

This application is a rewrite of [Demo Restaurant Application](https://github.com/farazdurrani/demo-restaurant-webapplication). The difference is that former application used MySQL database to persist data. And it was hosted on Openshift V2. And it was a Spring MVC application deployed on Tomcat 8. This application is a Spring boot application that uses embedded tomcat and embedded MariaDB. It is hosted on Heroku. 

This application has a responsive and mobile first front-end using Bootstrap library. It uses JSPs for server side rendering. On the back end, it uses Spring MVC, Hibernate as a JPA implementation, and embedded MariaDB to persist data.

User Actions:

A user can order food on this web application (this application doesn't accept online payment as of yet).

A user can sign up to keep track of all the orders he/she has placed. User can login and logout as well. (This application doesn't use Spring Security yet).

A user can order food with or without signing up for an account.

A user is sent an email when he/she creates an account, and when places an order. Admin also recieved an email. Application makes use of JavaMail API and Apache Velocity for email templates. 

This application keeps track of a session through [@SessionAttributes](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/SessionAttributes.html) and JSESSION ID as this application is deployed on a single container so above technique works. 

Admin actions:

Admin can sign in at https://restaurant-application-12.herokuapp.com/a to keep track of orders and to close and complete orders. 

All the config parameters are passed as part of env variables.

