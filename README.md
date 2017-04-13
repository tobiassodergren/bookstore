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


