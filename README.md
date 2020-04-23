![Status](https://github.com/Software-Engineering-Final-Project/articleFetch/workflows/Java%20CI%20with%20Maven/badge.svg)

### Overview
The project is built using a Layered Architecture Approach and is split into the following layers:
- Controller Layer
- Business Layer
- Data Access Layer
- Common Layer

##### Controller layer
This layer contains the endpoints for the RESTful API. 

##### Business Layer
This layer contains the business logic for our project. More specifically, this layer houses a service for each controller, the errors for all the controllers/services, and the Machine learning algorithm.

##### Data Access Layer
This layer houses the ORM for project. It is split into 2 folders: Model Domain and Repository. The Model Domain folder contains our database table models and the Repository folder contains the repository interfaces that are mapped to a DAO.


### Stack Specifications:
- Javascript (frontend) [The backend can found here](https://github.com/Software-Engineering-Final-Project/articleRecommender)
    - React Framework
- Java (backend)
    - Spring MVC
- Python (backend)
    - Machine Learning
- MySQL


### TODO LIST
- [x] Test Controller Layer
- [x] Test Service Layer
- [X] Test repository Layer
- [X] Add Machine Learning Algorithm
- [ ] Add JWT for authentication
- [X] Add API fetches
- [X] Benchmark implimenting API fetches using completablefuture's and threads
- [ ] Better READme
