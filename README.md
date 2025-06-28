# Expense-Tracker

# Backend
A RESTful API built with Spring Boot for managing personal expense records. Supports creating, updating, deleting, and filtering expense data with aggregation features.

## Features
* CRUD operations

* Filtering expenses by multiple categories, month, or both 

* Aggregated summaries, for total sum amount and average amount, by category, month, or both. 

* Input Validation

* JSON-based API requests and responses

* User registration and login with JWT authentication

* Secure endpoints using Spring Security

* User data isolation (users only access their own data)

## Tech Stack
* Java 24
* Spring Boot
* Spring Data JPA
* Postgres SQL
* Maven
* Postman (API Testing)
* Spring Security + JWT

## Starting Guide
* PreReqs
  * Java 24
  * Maven
  * Intellji (or ide of your choice, but instructions will assume you are using Intellji)
  * Database set up (I used Postgres, and instructions will assume you are too)  
* Connecting to Database
  * Make an `application.properties` file in the resource `backend/src/main/resources` folder, and copy the lines provided in the `template-AppProp.txt` file which is located in the `template` folder. Fill in the blanks.
  * Run the application to make sure it is all working

## Security

- Passwords are securely hashed using BCrypt.
- JWTs are used to authenticate and authorize users.
- Only authenticated users can access protected endpoints.
- Users can only interact with their own data.
- Admin role can be extended to manage users or access analytics (planned).

## Database Information

### Models
The `Expense` Entity, the main database
| Name            | Type    | Description          |
|-----------------|---------|----------------------|
| `id`            | long    | unique id of the expense |
| `userId`      | long  | id of the user to whom the expense belongs to  |
| `amount`        | Double  | Total expense amount |
| `month`         | String  | Date of the expense  |
| `category`      | String  | Category of the expense  |
| `expenseName`   | String  | Name of the expense  |

* **Validation**
  * id: unique and non null
  * username: cannot be empty
  * amount: cannot be empty and must be nonnegative
  * month: cannot be empty and follows MMMM-yyyy format, i.e April-2025
  * category: no constaints, if input doesn't specify a category will default to "no category" and no window path characters  
  * expenseName: Non Null and no window path characters  

The `UpdateExpense` class, helps with data transferring when it comes to updating an expense
| Name         | Type    | Description          |
|--------------|---------|----------------------|
| `amount`     | Double  | The updated amount |
| `month`      | String  | Updated date of the expense  |
| `category`   | String  | Updated category of the expense  |
| `expenseName`| String  | Updated name of the expense  |

* **Validation**
  * amount: must be nonnegative
  * month: follows MMMM-yyyy format, i.e April-2025
  * category: no constaints
  * expenseName: no window path characters

The `UserInfo` class is the database that stores users.

| Name       | Type   | Description                             |
|------------|--------|-----------------------------------------|
| `username` | String | username of the user                    |
| `password` | String | password of the user (encrypted)        |
| `role`     | String | role of the user, "USER" is the default |
| `userId`   | Long   | Id of user                              |


* **Validation**
  * username: unique


## API Endpoints 

### AUTH
* `localhost:8080/api/auth/register`
  * given a `username` and `password` in the json body it registers that user to the `UserInfo` database.
  * ```
      {
      "username": "jonh doe",
      "password": "password123!"
      }
* `localhost:8080/api/auth/login`
  * given a `username` and `password` in the json body, it checks if the credentials are correct. If they are correct it returns a JWT token that is needed to use the rest of the endpoints.
  * ```
      {
      "username": "jonh doe",
      "password": "password123!"
      }

* Note: for the following end points they expect a valid JWT Token so the `userId` can be extracted.


### GET

* `localhost:8080/api/expenses/userExpenses`
  * gets all expenses of the user
* `localhost:8080/api/expenses/userExpenses/filter?category=...&month=&...&category=...&month=&...`
  * This will return expenses of the user fitlering out `month` and `category`
  * note for multiple filters of a certain attribute you need to do separate calls as shown above
  * note the api endpoint does not expect either `category` or `month` to be in the url
  
* `localhost:8080/api/expenses/userExpenses/stats/category`
  * gets an aggregated total and average of `amount` for each `category` that the user has created 
* `localhost:8080/api/expenses/userExpenses/stats/month`
  * gets an aggregated total and average of `amount` for each `month` that the user has created 
* `localhost:8080/api/expenses/userExpenses/stats/both`
  *  gets an aggregated total and average of `amount` for each `month` and `category` that the user has created 

### POST

* `localhost:8080/api/expenses/save`
  * saves a user-inputted expense, expects a jsn body
  * ```
    {
      "amount": 25.50,
      "month": "April-2025",
      "category": "food",
     "expenseName": "in and out"
    }
    ```

### PUT

* `localhost:8080/api/expeness/update/{id}`
  * given an `id` and an `UpdatedExpense` it updates either `month`, `category`, `amount`, or all 3 depending on the json body
  * ```
    {
      "amount": 25.50,
      "month": "April-2025",
      "category": "food",
      "expenseName": "in and out"
    }
  
    ```
* note: the above shows all three atributes but if only some are being updated you just need to input those attributes into the json body
* will throw an error if `id` is not in the database     

### DELETE

* `localhost:8080/api/expenses/deleteID/{id}`
  * given an `id` it will delete that expense from the database
  * will throw an error if `id` is not found in database   
* `localhost:8080/api/expenses/deleteCategory?category=...&category=...`
  * given a list of `category` values it will delete all expenses with `category` values that are in the list
  * will throw an error if list is not provided or `username` or `cateogry` not in the database   

## Future Improvements

### Backend

*  add role actions
*  recurring expenses

### Frontend

* adding in frontend integration via react or other
