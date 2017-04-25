# Bookstore

The rationale for this project is to implement a
Bookstore in Java, as dictated by the
`org.sodergren.model.entity.BookList` interface.

A Cart should also be implemented.

## Architectural decisions


### External API
The store and cart should be exposed by an API, I chose 
a REST api to enable a client-side javascript application.
 
The rest API is implemented using javax.ws.rs annotations, 
jersey as implementation together with Jetty as the server.

The operations on the cart is done using simple PUT/POST
operations in the REST layer. These are converted into 
Java operations, which can be reused for other types of
clients.

### Model

The entities are identified by a UUIDs.
The books has an ID generated using UUID v3 from the standard
Java UUID library. The reason for this is to get a manageable ID
 that is based on the information that makes the book unique.

Ideally the UUID should be UUID v5.

The book data contained a book with the same title/author combo
but with different prices. I chose to include the price as a unique
parameter.


## How to build

```bash
$ mvn install
```

## How to run

```bash
$ java -version
java version "1.8.0"

$ java -jar target/bookstore-1.0-SNAPSHOT.jar file:books.csv
```

A REPL will start. enter help and press Enter to administrate the book store

## Rest API

### Query for books

```
$ curl  http://localhost:5000/api/v1/books?query=G
[{"uuid":"40c1e827-4f71-3d13-adec-848d37ae5491","author":"First Author","title":"Generic Title","price":185.50},{"uuid":"d15596ad-2d4b-32b5-ad62-be7528b07135","author":"Second Author","title":"Generic Title","price":1748.00}]
```

### Create a cart

```
$ curl http://localhost:5000/api/v1/cart
{"id":"0b674c9e-4d8a-4751-874e-8c1298dfa706","total":0,"items":[]}
```

### Add a book to cart
* Path parameter specifies the cart
* Query parameter bookId specifies the UUID of the book
* Query parameter quantity specifies the number of books to put in cart

```
$ curl -X PUT  'http://localhost:5000/a4e-8c1298dfa706?bookId=40c1e827-4f71-3d13-adec-848d37ae5491&quantity=2'
  {"id":"37af43be-e12a-4888-bbe7-9b58ad310925","total":185.50,"items":[{"uuid":"40c1e827-4f71-3d13-adec-848d37ae5491","title":"Generic Title","author":"First Author","quantity":1,"price":185.50,"cost":185.50}]}
```

### Checkout cart
* Path parameter specifies the cart

```
$ curl -X GET  'http://localhost:5000/a4e-8c1298dfa706/checkout'
  ["OK","OK","OK","OK","OK","NOT_IN_STOCK","NOT_IN_STOCK","NOT_IN_STOCK","DOES_NOT_EXIST"]
```