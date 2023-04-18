# Introduction 
This is a sample project exposing a single endpoint for creating a Subscription

# Getting Started
1.	Installation process
   1.	Download the project from Git

2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
Build:

​	1. From root folder run: mvn clean compile

Run:

- From the root folder, in a terminal shell do: mvn spring-boot:run

  OR, in IntelliJ go the SubscriptionApplication class; Right click and then left-click "run"

- Use Postman to execute a 'Post' operation with 'http://localhost:8080/subscriptions' as the URI and send 

  - ​	{

     	"name": "clarkKent",

     	"email": "clarkKent@dailyplanet.net",

     	"user-type": "superUser",

     	"company": "Daily Planet Inc.",

     	"application-type": "news"

    ​	}

    as the payload, setting the type to 'raw' and the drop-down to 'JSON'

Test

1. From IntelliJ:: Under src/e2e/java/scenarios, run the SubscriptionEndpointScenariosE2E class
2. Using Maven commandline:: from app-root folder[~\?\?\walmart-coding-assignment], run mvn verify -Pe2e-test