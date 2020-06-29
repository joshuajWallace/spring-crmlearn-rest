# spring-crmlearn-rest

Basic REST  service for simple customer CRUD.

@GET /api/customers
returns list of customers as JSON

@GET /api/customers/{id}
returns customer with matching id as JSON.

@POST /api/customers
post JSON newCustomer in body into database

@PUT /api/customers
put customer update of matching id within body, as JSON, into database

@DELETE /api/customer/{id}
delete customer with matching id from database
