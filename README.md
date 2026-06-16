# Product API Examples

This README shows the JSON request bodies for creating and updating products.

## Create Product

**Endpoint**

```http
POST /api/products
```

**Request Body**
> Category is optionalso you can leave it out if you want and it will just be null when created
```json
{
  "name": "New Product",
  "price": 19.99,
  "category": {
    "categoryId": 1
  }
}
```

**Responses**

```text
201 Created - Product was created successfully
400 Bad Request - categoryId does not exist
```

## Update Product

**Endpoint**

```http
PUT /api/products/{id}
```

Example:

```http
PUT /api/products/1
```

**Request Body**

```json
{
  "name": "Updated Product",
  "price": 24.99,
  "category": {
    "categoryId": 2
  }
}
```

**Responses**

```text
200 OK - Product was updated successfully
404 Not Found - Product ID does not exist
400 Bad Request - Category is missing or categoryId does not exist
```

## Notes

The `category` object only needs to include the `categoryId`. It is optional on the create method

```json
{
  "category": {
    "categoryId": 1
  }
}
```

The service uses that `categoryId` to look up the real category from the database before saving the product.
