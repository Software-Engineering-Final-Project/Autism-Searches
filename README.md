![Status](https://github.com/Software-Engineering-Final-Project/articleFetch/workflows/Java%20CI%20with%20Maven/badge.svg)
# Autism Searches Back End
To view the other parts of the project click on the links below.
- [Front End](https://github.com/Software-Engineering-Final-Project/articleRecommender)
- [Machine Learning](https://github.com/Software-Engineering-Final-Project/BackEndML)
<br />

## Overview
- [Product Vision](https://github.com/Software-Engineering-Final-Project/articleFetch#product-vision)
- [Project Stack](https://github.com/Software-Engineering-Final-Project/articleFetch#product-stack)
- [Project Architecture](https://github.com/Software-Engineering-Final-Project/articleFetch#project-architecture)
- [Machine Learning](https://github.com/Software-Engineering-Final-Project/articleFetch#machine-learning)
- [UI Screenshots](https://github.com/Software-Engineering-Final-Project/articleFetch/blob/master/Images/README.md)
- [Future Additions](https://github.com/Software-Engineering-Final-Project/articleFetch#future-additions)
- [Final Remarks](https://github.com/Software-Engineering-Final-Project/articleFetch#final-remarks)
<br />

### Product Vision
We proposed creating an online application where individuals can search for news and scholarly articles that pertain to common autistic behaviors, handling of autistic individuals, and research relating to autism. What makes this application stand out from current applications in the field is that our application will make suggestions about articles that pertain to the topics and articles that the user has already searched.
<br />

### Project Stack
##### Frontend
- Javascript (REACT)
- Bootstrap
- Fontawesome

##### Backend
- Java (Spring MVC)
- Mockito
- My SQL Connector
- JUnit
- Python (Scikit-learn, Pandas, NumPy)
<br />

### Project Architecture
The project is built using a Layered Architecture & Test Driven Development. It is split into the following layers:
- Main
    - Controller Layer
    - Business Layer
    - Data Access Layer
- Database Schemas
- Tests

##### Controller Layer
This layer contains the endpoints for the RESTful API. 

##### Business Layer
This layer contains the business logic for our project. More specifically, this layer houses a service for each controller, the errors for all the controllers/services, and the Machine learning algorithm.

##### Data Access Layer
This layer houses the ORM for our project. It is split into 2 folders: Model Domain and Repository. The Model Domain folder contains our database table models and the Repository folder contains the repository interfaces that are mapped to a DAO.

##### Database Schemas
This folder holds our Database Create Script, as well as our Table Population Scripts. We used My SQL Workbench to create the database.

##### Tests
We used Test-Driven Development to ensure that all our classes worked properly. We used Mockito, which is a Mocking framework, to inject mocks into each of our classes. This allowed us to be able to swap layers in and out for each other and not have to worry about the tests failing.
<br />
<br />

### Machine Learning
Machine Learning on this project is a content-based recommendation system written in Python. Python was chosen because of the vast library support, and the wealth of information supporting them. A list of articles pulled from an API fetcher (written in Java) is stored in the database. A python script is then run on that data, and the recommendations for each article are returned to the backend.
Currently, the articles are scored using TFIDF vector space model, and articles are recommended based on cosine similarity. Future additions to the project will include a combination of a Collaborative filtering algorithm based on user preferences along with the content-based recommendation. 

Note: Machine Learning was accomplished through the help of [this tutorial.](https://heartbeat.fritz.ai/recommender-systems-with-python-part-i-content-based-filtering-5df4940bd831)
<br />
<br />

### UI Screenshots
Click Here for [UI Images](https://github.com/Software-Engineering-Final-Project/articleFetch/blob/master/Images/README.md)
<br />

### Future Additions
- Search Functionality
- Larger Data Set
- Recommend Articles Based on Starred Articles
<br />

### Final Remarks

