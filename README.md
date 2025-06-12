# Expense-Tracker

A RESTful API built with Spring Boot for managing personal expense records. Supports creating, updating, deleting, and filtering expense data with aggregation features.

## Features
* CRUD operations

* Filtering expenses by multiple categories, month, or both 

* Aggregated summaries, for total sum amount and average amount, by category, month, or both. 

* Input Validation

* JSON-based API requests and responses

## Tech Stack
* Java 24
* Spring Boot
* Spring Data JPA
* Postgres SQL
* Maven
* Postman (API Testing)

## Starting Guide
* PreReqs
  * Java 24
  * Maven
  * Intellji (or ide of your choice, but instructions will assume you are using Intellji)
  * Database set up (I used Postgres, and instructions will assume you are too)  
* Connecting to Database
  * Go to the `application.properties` file, the lines provided are all that you need to use the project; you just need to fill them out
  * If you are using PostgreSQL, the host will be localhost, and the port number will be 5432.
  * For the database name, it will be the database that you are using.
  * Username will be the username you assigned to the database, usually it is either postgres or your own username.
    * Make sure to grant all privileges to this user   
  * Password: If you created the database without a password, leave it blank; otherwise, input the password you assigned to the database.
  *  Run the application to make sure it is all working

## Database Information

### Models
The `Expense` Entity, the main database
| Name         | Type    | Description          |
|--------------|---------|----------------------|
| `id`         | long    | unique id of the expense |
| `username`   | String  | user to whom the expense belongs to  |
| `amount`     | Double  | Total expense amount |
| `month`      | String  | Date of the expense  |
| `category`   | String  | Category of the expense  |

* **Validation**
  * id: unique and non null
  * username: cannot be empty
  * amount: cannot be empty and must be nonnegative
  * month: cannot be empty and follows MMMM-yyyy format, i.e April-2025
  * category: no constaints, if input doesn't specify a category will default to "no category"   

The `UpdateExpense` class, helps with data transferring when it comes to updating an expense
| Name         | Type    | Description          |
|--------------|---------|----------------------|
| `amount`     | Double  | The updated amount |
| `month`      | String  | Updated date of the expense  |
| `category`   | String  | Updated category of the expense  |

* **Validation**
  * amount: must be nonnegative
  * month: follows MMMM-yyyy format, i.e April-2025
  * category: no constaints


## API Endpoints 

### GET

* `localhost:8080/api/{user}`
  * gets all expenses of the user
  * will throw an error if `user` is not in the databasae
* `localhost:8080/api/{user}/filter?category=...&month=&...&category=...&month=&...`
  * This will return expenses of the user fitlering out `month` and `category`
  * note for multiple filters of a certain attribute you need to do separate calls as shown above
  * note the api endpoint does not expect either `category` or `month` to be in the url
  * will throw an error is `user` is not in the databasae   
* `localhost:8080/api/{user}/stats/category`
  * gets an aggregated total and average of `amount` for each `category` that the user has created
  * will throw an error is `user` is not in the databasae   
* `localhost:8080/api/{user}/stats/month`
  * gets an aggregated total and average of `amount` for each `month` that the user has created
  * will throw an error is `user` is not in the databasae   
* `localhost:8080/api/{user}/stats/both`
  *  gets an aggregated total and average of `amount` for each `month` and `category` that the user has created
  *  will throw an error is `user` is not in the databasae   

### POST

* `localhost:8080/api/save`
  * saves a user inputted expense, expects a jsn body
  * ```
    {
      "username": "John Doe",
      "amount": 25.50,
      "month": "April-2025",
      "category": "food"
    }
    ```

### PUT

* `localhost:8080/api/update/{id}`
  * given an `id` and an `UpdatedExpense` it updates either `month`, `category`, `amount`, or all 3 depending on the json body
  * ```
    {
      "amount": 25.50,
      "month": "April-2025",
      "category": "food"
    }
  
    ```
* note: the above shows all three atributes but if only some are being updated you just need to input those attributes into the json body
* will throw an error if `id` is not in the database     

### DELETE

* `localhost:8080/api/deleteID/{id}`
  * given an `id` it will delete that expense from the database
  * will throw an error if `id` is not found in database   
* `localhost:8080/api/deleteCategory/{user}?category=...&category=...`
  * given a `username` and a list of `category` values it will delete all expenses with `category` values that are in the list
  * will throw an error if list is not provided or `username` or `cateogry` not in the database   

## Future Improvements

### Backend

*  add User authentication (JWT)
*  recurring expenses

### Frontend

* adding in frontend intergration via react or other
