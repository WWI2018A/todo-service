ToDo-Service
---
**Description:**
Description of the toDo-Service

**Requests:**
* [GET /todos/{id}](#get-todosid)
* [POST /todos](#post-todos)
* [PUT /todos/{id}](#put-todosid)
* [DELETE /todos/{id}](#delete-todosid)

GET /todos/{id}
----
  Returns the todo with the given todo-id.
  
* **URL:**

  /todos/{id}

* **Method:**

  `GET`
  
*  **URL Params:**

   **Required:**
 
   `id=[string]`

   **Optional:**
 
   `none`

* **Data Params:**

    `none`

* **Success Response:**
  
  * **Code:** 200 OK <br />
    **Content:** 
     ```json
    {
      "todo-id": "todo-41723ac1-85c6-4520-a876-0d3388092e65",
      "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
      "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
      "createdOn": "1996-10-15T00:05:32.000Z",
      "dueUntil": "1996-10-16T00:05:32.000Z",
      "status": "COMPLETED",
      "content": "Aufgabe XY machen"
    }
    ```

 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    ```json
    {
      "error" : "Could not find the todo with the id: [id]" 
    }
    ```

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** ``
    ```json
    {
      "error" : "Unauthorized."
    }
    ```
    
  OR
    
  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** 
    ```json
    {
      "error" : "An error occurred." 
    }
    ```

* **Sample JSON mock files:**

  `GetTodosIdResponse.json`

* **Notes:**

***

POST /todos
   ----
   
   Creates a new todo.
     
   * **URL:**
   
     /todos
   
   * **Method:**
   
     `POST`
     
   *  **URL Params:**
   
      **Required:**
    
      `none`
   
      **Optional:**
    
      `none`
   
   * **Data Params:**
   
       ```json
       {
         "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
         "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
         "dueUntil": "1996-10-16T00:05:32.000Z",
         "status": "OPEN",
         "content": "Aufgabe XY machen"
       }
       ```
   
   * **Success Response:**
     
     * **Code:** 201 CREATED <br />
       **Content:** 
        ```json
       {
         "todo-id": "todo-41723ac1-85c6-4520-a876-0d3388092e65",
         "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
         "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
         "createdOn": "1996-10-15T00:05:32.000Z",
         "dueUntil": "1996-10-16T00:05:32.000Z",
         "status": "OPEN",
         "content": "Aufgabe XY machen"
       }
       ```
   
    
   * **Error Response:**
   
     * **Code:** 500 INTERNAL SERVER ERROR <br />
       **Content:** 
       ```json
       {
         "error" : "An error occurred." 
       }
       ```
   
     OR
   
     * **Code:** 401 UNAUTHORIZED <br />
       **Content:** ``
       ```json
       {
         "error" : "Unauthorized."
       }
       ```
       
   
   * **Sample JSON mock files:**
   
     `PostTodosRequest.json`<br>
     `PostTodosResponse.json`
   
   * **Notes:**
   
   ***
   
    

PUT /todos/{id}
     ----
     Updates a todo with the given todo-id.
     
   * **URL:**
   
     /todos/{id}
   
   * **Method:**
   
     `PUT`
     
   *  **URL Params:**
   
      **Required:**
    
      `id=[string]`
   
      **Optional:**
    
      `none`
   
   * **Data Params:**
   
       ```json
       {
         "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
         "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
         "dueUntil": "1996-10-16T00:05:32.000Z",
         "status": "DONE",
         "content": "Aufgabe XY machen"
       }
       ```
   
   * **Success Response:**
     
     * **Code:** 200 OK <br />
       **Content:** 
        ```json
       {
         "todo-id": "todo-41723ac1-85c6-4520-a876-0d3388092e65",
         "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
         "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
         "createdOn": "1996-10-15T00:05:32.000Z",
         "dueUntil": "1996-10-16T00:05:32.000Z",
         "status": "DONE",
         "content": "Aufgabe XY machen"
       }
       ```
       
     OR:
     * **Code:** 201 CREATED <br />
         **Content:** 
          ```json
         {
           "todo-id": "todo-41723ac1-85c6-4520-a876-0d3388092e65",
           "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
           "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
           "createdOn": "1996-10-15T00:05:32.000Z",
           "dueUntil": "1996-10-16T00:05:32.000Z",
           "status": "DONE",
           "content": "Aufgabe XY machen"
         }
         ```
    
   * **Error Response:**
   
     * **Code:** 500 INTERNAL SERVER ERROR <br />
       **Content:** 
       ```json
       {
         "error" : "An error occurred." 
       }
       ```
   
     OR
   
     * **Code:** 401 UNAUTHORIZED <br />
       **Content:** ``
       ```json
       {
         "error" : "Unauthorized."
       }
       ```
       
   
   * **Sample JSON mock files:**
   
     `PutTodosIdRequest.json`<br>
     `PutTodosIdResponse.json`
   
   * **Notes:**
   
   ***
   

PUT /todos/{id}
  ----
  Updates a todo with the given todo-id.
  
* **URL:**

  /todos/{id}

* **Method:**

  `PUT`
  
*  **URL Params:**

   **Required:**
 
   `id=[string]`

   **Optional:**
 
   `none`

* **Data Params:**

    ```json
    {
      "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
      "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
      "dueUntil": "1996-10-16T00:05:32.000Z",
      "status": "DONE",
      "content": "Aufgabe XY machen"
    }
    ```

* **Success Response:**
  
  * **Code:** 200 OK <br />
    **Content:** 
     ```json
    {
      "todo-id": "todo-41723ac1-85c6-4520-a876-0d3388092e65",
      "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
      "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
      "createdOn": "1996-10-15T00:05:32.000Z",
      "dueUntil": "1996-10-16T00:05:32.000Z",
      "status": "DONE",
      "content": "Aufgabe XY machen"
    }
    ```
    
  OR:
  * **Code:** 201 CREATED <br />
      **Content:** 
       ```json
      {
        "todo-id": "todo-41723ac1-85c6-4520-a876-0d3388092e65",
        "user-id": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
        "list-id": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
        "createdOn": "1996-10-15T00:05:32.000Z",
        "dueUntil": "1996-10-16T00:05:32.000Z",
        "status": "DONE",
        "content": "Aufgabe XY machen"
      }
      ```
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** 
    ```json
    {
      "error" : "An error occurred." 
    }
    ```

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** ``
    ```json
    {
      "error" : "Unauthorized."
    }
    ```
    

* **Sample JSON mock files:**

  `PutTodosIdRequest.json`<br>
  `PutTodosIdResponse.json`

* **Notes:**

***

DELETE /todos/{id}
  ----
  Deletes a todo with the given todo-id.
  
* **URL:**

  /todos/{id}

* **Method:**

  `DELETE`
  
*  **URL Params:**

   **Required:**
 
   `id=[string]`

   **Optional:**
 
   `none`

* **Data Params:**

    `none`

* **Success Response:**
  
  * **Code:** 204 NO CONTENT <br />
    **Content:** no content
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** 
    ```json
    {
      "error" : "An error occurred." 
    }
    ```

  OR

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** ``
    ```json
    {
      "error" : "Unauthorized."
    }
    ```
   
  OR
   
  * **Code:** 404 NOT FOUND <br />
    **Content:** ``
    ```json
    {
     "error" : "Could not find todo with the id: [id]."
    }
    ``` 

* **Sample JSON mock files:**

  `PutTodosIdRequest.json`<br>
  `PutTodosIdResponse.json`

* **Notes:**

***
