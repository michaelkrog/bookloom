# Bookloom

This is a Repository implementing the Coding test given from Tryg for an online bookstore.

## Overview

* [Usage](#usage)
  - [Downloading the code](#downloading-the-code)
  - [Building the code](#building-the-code)
  - [Running the containers](#running-the-containers)
* [Architecture](#architecture)
  - [Diagram](#diagram)
  - [Security](#security)
  - [Session Management](#session-management)
* [Backend](#backend)
  - [User service](#user-service)
  - [Book service](#book-service)
  - [Order service](#order-service)
  - [Notification service](#notification-service)
  - [Shared](#shared)
  - [Generic future enhancements](#generic-future-enhancements)
* [Frontend](#frontend)
  - [Public UI](#public-ui)
  - [Admin UI](#admin-ui)


## Usage

In order to run the system there are the following prerequisites:
* [Docker](https://docs.docker.com/engine/install/)
* [Node](https://nodejs.org/en/download/package-manager) (18 or later)
* NPM
* [Maven 3](https://maven.apache.org/install.html)
* [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)

Also, the system will occupy the ports 3000, 8080-8081, so these needes to be avialable for everything to start up.

### Downloading the code
First download the code from the repository either via Git or as a zip from Github. Ensure you have the code pulled or extracted into a folder on your local harddrive, fx. a folder called `bookloom`.

### Building the code
To build the project, open a terminal/command line and change to the before mentioned bookloom directory.
```
cd bookloom
```

Run the build command.
```
# On Mac/Linux
./build.sh

# On windows
build.bat
```

This will build the services and produce a docker image for each of the service. It will also build the frontend and produce a docker image for that.

### Running the containers

To run the system we will make use of docker compose. Within your terminal/command line, go to the before mentioned bookloom folder
```
cd bookloom
```

Now start the system by running this command:
```
docker compose up
```

This will create a docker stack that contains all the services as well as the frontend. When everything has started up, go to http://localhost:3000 in your browser. 

You can access the public UI without signing in. You can sign in at the top by clicking "Sign in". Use one of the following email/password combinations. (Default users)

#### Admin user
Email: admin@bookloom  
Password: AdminTest

#### Ordinary user
Email: user@bookloom  
Password: UserTest

**Remark:** Every service is started with 3 instances and requests will automatically round robin between them. See also [session management](#session-management).


### Integration tests
Integration tests are skipped during build. They require a mongo server available at localhost:27017 without auth requirements.

The integration tests must be run manually. The tests will create a database called bookloom-integration-test.


## Architecture

### Diagram
This diagram shows the overall architecture of the system, including planned elements.
![Architecture diagram](./bookloom-archtitecture.png)

### Security
All services accepts [JWT](https://jwt.io/introduction) Bearer tokens. The token contains the expire time, and the user's email and security roles.

For example:
```json
{
  "exp": 1723496258,
  "sub": "admin@bookloom",
  "roles": [
    "ROLE_ADMIN",
    "ROLE_USER"
  ]
}
```

In order to gain a JWT token the user service also accept Basic Auth and exposes an endpoint for authentication.

For example:
```
$ curl 'http://localhost:8080/users/actions/authenticate' \
  -X 'POST' \
  -H 'authorization: Basic YWRtaW5AYm9va2xvb206NTM1YzA0YjAtZWQ3NC00MjM4LWI5ZjgtN2I3OWEyMTM4NjZj' 

{
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJleHAiOjE3MjM1MDgwMzMsInN1YiI6ImFkbWluQGJvb2tsb29tIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdfQ.7vBGhWfIoTU-5bzf1hJAliRi0YkwaJa_ZHPx96WLqPW114Mp-I-9WIZlMRMxA_1h"
}
```

The token is kept until it expires or the user signs out and will be sent with every request to the services.

#### Future enhancements

#### Store token securely
This token should be stored in a Secure Cookie, but for demo purposes it is simply stored in LocalStorage.

Also, when the token expires, the user needs to log in again. An improvement would be to introduce a system for refresh tokens, so new access tokens could be resolved over a longer period of time. 

#### Correct Security Model
The correct security model is not yet implemented. A customized security model should be put in place to ensure that it fits the functional requirements, eg. correct security regarding who can edit users, books, orders etc.

### Session Management
Server side session management has been disabled, and everything is stateless on the server side. This allows for easier scaling of services.

Session management is handled simply by keeping a Bearer token on the client side. Any service instance that receives a Bearer token on request, will resolve and validate the token securely and from the token resolve the user's roles in order to grant authorities in the service.

## Backend
The services are small, independent units that each focus on a specific area within the larger system. Each service includes controllers, a service layer, and a repository layer for its domain, keeping everything related to that domain within the same service.

### User service
The user service manages User models and authentication. Authentication generates a new Bearer token that allows the UI to make requests on behalf of the user.

The user service will initialize the database with 2 users.

#### Future enhancements
As mentioned under Security, the Security model is not yet implemented.

Any authenticated user can currently retrieve any user, and only admins can edit and delete users. In reality admins should be able to read and edit all users, but users should only be able to see and edit their own user - and should not be able to edit it's own roles.

Also, the data initialization should be changed. For example to a proper Database change tool like Mongock.

### Book service
The book service manages books. It allows for searching among books for display in the public UI.

The book service will initialize the database with a few books.

#### Future enhancements
Data initialization should be changed. For example to a proper Database change tool like Mongock.

### Order service
*Only partially implemented*. The order service manages order models and Shopping cart details. 

#### Future enhancements
As mentioned under Security, the Security model is not yet implemented.
Any user can currently retrieve any orders.

### Notification service
*Not implemented*. The notification service sends notifications to users based on order events. It subscribes on topics for created orders and order status changes.

When an event is received, the service retrieves user information from the user service and sends a notification to the user based on the event type.

### Shared
The shared module holds generic security config, super classes and more. Every service includes this a builds its controllers, service-layer and repositories upon it.

### Generic future enhancements
None of the services currently allows for configuring outside of what Spring offers out of the box. But some things are currently hardcoded (like secrets for JWT, password for admin user etc.) and it should rather be configurable via application.properties and/or environment variables.

## Frontend

### Public UI
![Public UI](./ui_public.png)

The public UI on http://localhost:3000 will present the books in the system. You will be able to sort and filter the books. You can also click on a book to see details about the book.

If signed in as admin, you will be able to access the admin view via a link at the top of the page.

### Admin UI
![Admin UI](./ui_admin.png)

The admin UI on http://localhost:3000/admin will present a more app-like experience. You will be able to get an overview of the users and books in the system.


### Future enhancements
No tests have be written for the UI in this Coding Challenge. Priority was given into having a functional demo to present.
