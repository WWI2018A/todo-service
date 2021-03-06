ToDo-Service
---

Description:
The ToDo-Service is responsible to return, create, update and delete todos or todo-lists. 

**Requests:**
* [GET /todos](#get-todos)
* [GET /todos/{id}](#get-todosid)
* [GET /todos/todoLists](#get-todostodolists)
* [GET /todos/todoLists/{id}](#get-todostodolistsid)
* [POST /todos](#post-todos)
* [POST /todos/todoLists](#post-todotodolists)
* [PUT /todos/{id}](#put-todosid)
* [PUT /todos/todoLists/{id}](#put-todostodolistsid)
* [DELETE /todos/{id}](#delete-todosid)
* [DELETE /todos/todoLists/{id}](#delete-todostodolistsid) 

GET /todos
----
  Returns a list of all to dos.
  
* **URL:**

  /todos

* **Method:**

  `GET`
  
* **URL Params:**

   **Required:**
 
   `none`

   **Optional:**
 
   `listId=[string]`

* **Header Params**

  **Required:**
  
  `x-uid:[string]`
  
  **Optional:**
  
  `none`

* **Data Params:**

  `none`

* **Success Response:**
  
  * **Code:** 200 OK <br />
    **Content:** 
     ```json
    [
      {
        "id": "5e6a72d68fadc7688e04e2bd",
        "createdDate": "2020-03-12T17:35:18.764+0000",
        "lastModifiedDate": "2020-03-12T17:35:18.764+0000",
        "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
        "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
        "dueDate": "1996-10-16T00:05:32.000+0000",
        "status": "NONE",
        "content": "Aufgabe XY machen"
      },
      {
        "id": "5e6a731a833ae5306526eb35",
        "createdDate": "2020-03-12T17:36:26.465+0000",
        "lastModifiedDate": "2020-03-12T17:51:08.665+0000",
        "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
        "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
        "dueDate": "1996-10-16T00:05:32.000+0000",
        "status": "COMPLETED",
        "content": "Aufgabe XY machen"
      },
      {
        "id": "5e6a75891d40026b13e5e43a",
        "createdDate": "2020-03-12T17:46:49.573+0000",
        "lastModifiedDate": "2020-03-12T17:46:49.573+0000",
        "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
        "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
        "dueDate": "1996-10-16T00:05:32.000+0000",
        "status": "NONE",
        "content": "Aufgabe YZ machen"
      },
      {
        "id": "5e7119ed46d2435c162f17ab",
        "createdDate": "2020-03-17T18:41:49.452+0000",
        "lastModifiedDate": "2020-03-17T18:41:49.452+0000",
        "userId": null,
        "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
        "dueDate": "1996-10-16T00:05:32.000+0000",
        "status": "COMPLETED",
        "content": "Aufgabe XY machen"
      },
      {
        "id": "5e711a5446d2435c162f17ac",
        "createdDate": "2020-03-17T18:43:32.127+0000",
        "lastModifiedDate": "2020-03-17T18:43:32.127+0000",
        "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
        "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
        "dueDate": "1996-10-16T00:05:32.000+0000",
        "status": "COMPLETED",
        "content": "Aufgabe AB machen"
      }
    ]
    ```
 
* **Error Response:**
  
  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    ```json
    {
      "error" : "Could not find any to do" 
    }
    ```

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
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

  `GetTodosResponse.json`


***

GET /todos/{id}
----
  Returns the todo with the given todo-id.
  
* **URL:**

  /todos/{id}

* **Method:**

  `GET`
  
* **URL Params:**

  **Required:**
 
  `id=[string]`

  **Optional:**
 
  `none`
   
* **Header Params**

  **Required:**
  
  `x-uid:[string]`
  
  **Optional:**
  
  `none`

* **Data Params:**

  `none`

* **Success Response:**
  
  * **Code:** 200 OK <br />
    **Content:** 
     ```json
     {
       "id": "5e6a72d68fadc7688e04e2bd",
       "createdDate": "2020-03-12T17:35:18.764+0000",
       "lastModifiedDate": "2020-03-12T17:35:18.764+0000",
       "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
       "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
       "dueDate": "1996-10-16T00:05:32.000+0000",
       "status": "NONE",
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

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
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


***

GET /todos/todoLists
----
  Returns a list of all to do lists.
  
* **URL:**

  /todos/todoLists

* **Method:**

  `GET`
  
* **URL Params:**

  **Required:**
 
  `none`

  **Optional:**
 
  `none`
   
* **Header Params**

  **Required:**
  
  `x-uid:[string]`
  
  **Optional:**
  
  `none`

* **Data Params:**

  `none`

* **Success Response:**
  
  * **Code:** 200 OK <br />
    **Content:** 
     ```json
     [
       {
        "id": "5e6a6d1ea6b054649bb3a3a2",
        "createdDate": "2020-03-12T17:10:54.923+0000",
        "lastModifiedDate": "2020-03-12T17:10:54.923+0000",
        "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
        "name": "Listennamen Test"
      },
      {
        "id": "5e71185b46d2435c162f17aa",
        "createdDate": "2020-03-17T18:35:07.829+0000",
        "lastModifiedDate": "2020-03-17T18:35:07.829+0000",
        "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
        "name": "Listenname 1"
      }
     ]
    ```
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    ```json
    {
      "error" : "Could not find any todo list" 
    }
    ```

  OR

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
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

  `GetTodoListsIdResponse.json`


***

GET /todos/todoLists/{id}
----
  Returns the todo list with the given todolist-id.
  
* **URL:**

  /todos/todoLists/{id}

* **Method:**

  `GET`
  
* **URL Params:**

  **Required:**
 
  `id=[string]`

  **Optional:**
 
  `none`

* **Header Params**

  **Required:**
  
  `x-uid:[string]`
  
  **Optional:**
  
  `none`

* **Data Params:**

  `none`

* **Success Response:**
  
  * **Code:** 200 OK <br />
    **Content:** 
     ```json
     {
       "id": "5e6a6d1ea6b054649bb3a3a2",
       "createdDate": "2020-03-12T17:10:54.923+0000",
       "lastModifiedDate": "2020-03-12T17:10:54.923+0000",
       "userId": "user-ffb7c974-342b-4e58-8d9c-768fe5718ff5",
       "name": "Listennamen Test"
     }
    ```

 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    ```json
    {
      "error" : "Could not find the todo list with the id: [id]" 
    }
    ```

  OR

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
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

  `GetTodoListsIdResponse.json`


***

POST /todos
   ----
   
   Creates a new todo.
     
* **URL:**
   
   /todos
   
* **Method:**
   
   `POST`
     
* **URL Params:**
   
  **Required:**
    
  `none`
   
  **Optional:**
    
  `none`
      
* **Header Params**

  **Required:**
  
  `x-uid:[string]`
  
  **Optional:**
  
  `none`
   
* **Data Params:**
   
   ```json
   {
     "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a"
   }
   ```
   
 * **Success Response:**

   * **Code:** 201 CREATED <br />
     **Content:** Created
   
    
 * **Error Response:**

   * **Code:** 500 INTERNAL SERVER ERROR <br />
     **Content:** 
     ```json
     {
       "error" : "An error occurred." 
     }
     ```

   OR
   
  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
    ```

* **Sample JSON mock files:**

 `PostTodosRequest.json`<br>


***


POST /todos/todoLists
   ----
   
   Creates a new todo list.
     
* **URL:**

 /todos/todoLists

* **Method:**

 `POST`

* **URL Params:**
   
  **Required:**
    
  `none`
   
  **Optional:**
    
  `none`
      
* **Header Params**

  **Required:**
  
  `x-uid:[string]`
  
  **Optional:**
  
  `none`

* **Data Params:**

   ```json
   {
     "name": "Listenname 1"
   }
   ```
   
* **Success Response:**

 * **Code:** 201 CREATED <br />
   **Content:** created
   

* **Error Response:**

 * **Code:** 500 INTERNAL SERVER ERROR <br />
   **Content:** 
   ```json
   {
     "error" : "An error occurred." 
   }
   ```
   
 OR

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
    ```

* **Sample JSON mock files:**

 `PostTodoListsRequest.json`<br>


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

* **Header Params**

 **Required:**

 `x-uid:[string]`

 **Optional:**

 `none`

* **Data Params:**

   ```json
   {
     "listId": "list-31877f02-45ff-4eac-8af1-b3ee50fec49a",
     "dueDate": "1996-10-16T00:05:32.000+0000",
     "status": "COMPLETED",
     "content": "Aufgabe AB machen"
   }
   ```
   
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

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
    ```


* **Sample JSON mock files:**

 `PutTodosIdRequest.json`<br>


***
   

PUT /todos/todoLists/{id}
  ----
  Updates a todo list with the given todolist-id.
  
* **URL:**

  /todos/todoLists/{id}

* **Method:**

  `PUT`
  
*  **URL Params:**

   **Required:**
 
   `id=[string]`

   **Optional:**
 
   `none`
   
* **Header Params**

  **Required:**
  
  `x-uid:[string]`
  
  **Optional:**
  
  `none`

* **Data Params:**

    ```json
    {
    "name":"Neuer Name"
    }
    ```

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

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
    ```

* **Sample JSON mock files:**

  `PutTodoListsIdRequest.json`<br>


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
   
* **Header Params**

  **Required:**
  
  `none`
  
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

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
    ```
   
  OR
   
  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    ```json
    {
     "error" : "Could not find todo with the id: [id]."
    }
    ``` 

* **Sample JSON mock files:**
    `none`


***

DELETE /todos/todoLists/{id}
  ----
  Deletes a todo List with the given todolist-id.
  
* **URL:**

  /todos/todoLists/{id}

* **Method:**

  `DELETE`
  
* **URL Params:**

   **Required:**

   `id=[string]`

   **Optional:**

   `none`

* **Header Params**

  **Required:**
  
  `none`
  
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

  * **Code:** 403 ACCESS DENIED <br />
    **Content:** 
    ```json
    Acess Denied
    ```
   
  OR
   
  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    ```json
    {
     "error" : "Could not find todolist with the id: [id]."
    }
    ``` 

* **Sample JSON mock files:**
    `none`


***
