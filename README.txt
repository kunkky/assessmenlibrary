Hi!
This is a project add, update and delete books,


To register,
endpoint:   http://localhost:8080/api/v1/auth/register
json data must be sent through the body
{
    "email":"user@gmail.com",
    "username":"Johny",
    "lastname":"John",
    "firstname":"Doe",
    "password":"password"
}
Method: Post
a token will and user data will be returned this data can be used further by the front end developer

to Login
endpoint: http://localhost:8080/api/v1/auth/authenticate
{
    "email":"user@gmail.com",
    "password":"password"
}
Method: Post

user detail will be sent back with token to be used to access other pages


Librarian must be registered or signed in before performing actions using the route
http://localhost:8080/api/v1/

Add
http://localhost:8080/api/v1/edit/1?name=rich dad poor daddy&author='unknown'
http://localhost:8080/api/v1/getbooks
http://localhost:8080/api/v1/getBook/2
http://localhost:8080/api/v1/delete/id

to Record Lent books use
http://localhost:8080/api/v1/record/addrecord
with Auth Token
pass paramenter via Body
{
    "username":"Ademuyiwa Adekunle",
    "recorddate":"2022-02-02",
    "bookname":"Richest man in Bablyon",
    "bookid":2,
    "status":"not return"
}
Method: Post

to fetch lend book record

to update a book record use the end point
http://localhost:8080/api/v1/record/edit/2?status=returned
pass teh query parameter and use auth token