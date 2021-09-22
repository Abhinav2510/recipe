# Recipe API Backend

## Description:
The Recipe API Backend allows performing following operation
- Create Recipe
- Edit created recipe
- Delete created recipes
- Retrieve all recipes with pagination support
- Retrieve recipe with detailed instruction
- Create User 
- Login with user details

## Assumptions :
- Validations for recipe creation and update are assumed as 
  - recipeName should be at least 5 characters
  - cuisine, course and instructionText should be at least 3 characters
  - serves,prepTime,cookingTime and instructionNumber should always be non-zero positive number
  - name,unit and quantity of ingredient should be not empty
- Validations for users
  - username and password should be at least 8 characters long.
- Only the user who created recipe can edit,update or delete the recipe

## Tech-Stack
- Java 8
- Spring-Boot
- JPA
- In-Memory Database H2
- Maven
- Git bash

- ## Steps to run the application
- Checkout the code / Download from git repo()
- checkout : open git bash and run command `git clone `
- open command prompt(cmd) or terminal on Mac
- navigate to the project folder
- run command `mvn clean install`
- once its successfully build run command `mvn spring-boot: run`

Now application is up and running on http://localhost:8080

## How to use this service
- Open the URL in your browser : http://localhost:8080
- User will see a swagger page with all the defined specs of the service.
- There will have 2 Tags you can see.


### 1. user-controller
#### Description:
- Endpoint 1: `POST /users/signup`
  - Allows creation of user
- Endpoint 2: `POST /users/signin`
  - Allows user to login
  - On providing correct credential in request the response provides Bearer token in header which can be used for calling further API

### 2. recipe-controller
#### Description:
- All the below endpoints are secured with stateless JWT authentication.
- All request to below Endpoints should contain custom header `authorization` with value containing `Bearer {JWT}`
- Endpoint 1:  `POST /recipe/create`
    - Allows creation of recipes with above-mentioned validations
- Endpoint 2: `GET /recipe/{recipeId}`
    - Response contains detailed recipe with all instructions and ingredients as part of recipe
- Endpoint 3: `PUT /recipe/{recipeId}`
  - Allows update of existing recipe with above-mentioned validations
  - Only the creator user can Update recipe
- Endpoint 4: `DELETE /recipe/{recipeId}`
  - Allows deletion of recipe created previously 
  - Only the creator user can delete recipe
- Endpoint 5: `GET /recipe/`
    - Gets all the recipes with pagination support with default page size 0
    

### Testing using Swagger UI

####Running application
- Run application using `mvn spring-boot: run` or `java -jar /target/recipe-0.0.1-SNAPSHOT.jar`
- Navigate to http://localhost:8080

#### Authenticating for using Recipe API
- under `user-controller` tab you can create user or use already created user to authenticate 
- to create user use `/users/signup` endpoint
- to Use default user for application use below JSON object for `user/signin`
```
  {
  "userName":"TestUser1",
  "password":"password1234"
  } 
```
- It will return response header `authorization` with JWT token
- Copy the value in `authorization` header
- On top left corner click on Authorize button and enter copied value

Now you should be able to call all the APIs without needing to specify `authorization` header manually 
