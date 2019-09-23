Loan API
==============
The repository is a demo of Java Spring Boot with Reactive-WebFlux and Dockerizing.


### Prequisites
* JDK 1.8
* Apache Maven 3.6.1 
* Docker
* Postman

Steps
=====

#1 Install Java 
-----------------------
If you don't have Java installed in your system, then the first thing you need to do is to install JDK (Java Development Kit).
This project is based on JDK 8, please follow the instructions from Oracle on how to install Java in your system.


#2 Install Apache Maven
----------------------
This project is maven project, so to build you have to install Apache Maven after installing Java. 
Please follow the instruction on how to install Apache Maven in your system. 

If or after Maven is installed in your system, do update in settings.xml file in your .m2 directory 

* open maven setting file

`$vim ~/.m2/settings.xml`

* add following item in setting.xml. 

based on 
(https://stackoverflow.com/questions/47301470/no-plugin-found-for-prefix-docker-in-the-current-project-and-in-the-plugin-gro/47301506)

```
<pluginGroups>
        <pluginGroup>com.spotify</pluginGroup>
</pluginGroups>
```

#3 Build project using Maven
--------------------------------------
* To build this project just simply run 
  `mvn clean install dockerfile:build` and it will compile the project and finally build docker images. You can check docker image created
  using `docker images` command.


#4 Run docker images
------------------------------------
* Run using `docker run -p 8080:8080 loan-api:latest`. 

#5 Check application log in container
* Check logs using `docker logs -f <container_id>` 

#6 Stop running container
* Terminate processes once you are done using `docker stop <container_id>`.


-----
This project include Postman request collection file for the API. you can import the file in your postman and test the API.


