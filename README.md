![Status](https://github.com/Software-Engineering-Final-Project/articleFetch/workflows/Java%20CI%20with%20Maven/badge.svg)

### Overview
- Product Vision
- Project Stack
- Project Architecture
- UI Screenshots
- Future Additions
- Final Remarks

#### Product Vision
We proposed creating an online application where individuals can search for news and scholarly articles that pertain to common autistic behaviors, handling of autistic individuals, and research relating to autism. What makes this application stand out from current applications in the field is that our application will make suggestions about articles that pertain to the topics and articles that the user has already searched.

#### Project Stack
##### Frontend
- Javascript (REACT)
- Bootstrap
- Fontawesome

##### Backend
- Java (Spring MVC)
- Mockito
- My SQL Connector
- JUnit


#### Project Architecture
The project is built using a Layered Architecture & Test Driven Development. It is split into the following layers:
- Main
    - Controller Layer
    - Business Layer
    - Data Access Layer
- Tests

##### Controller Layer
This layer contains the endpoints for the RESTful API. 

##### Business Layer
This layer contains the business logic for our project. More specifically, this layer houses a service for each controller, the errors for all the controllers/services, and the Machine learning algorithm.

##### Data Access Layer
This layer houses the ORM for our project. It is split into 2 folders: Model Domain and Repository. The Model Domain folder contains our database table models and the Repository folder contains the repository interfaces that are mapped to a DAO.

##### Tests
We used Test-Driven Development to ensure that all our classes worked properly. We used Mockito, which is a Mocking framework, to inject mocks into each of our classes. This allowed us to be able to swap layers in and out for each other and not have to worry about the tests failing.

#### Future Additions

#### Final Remarks

