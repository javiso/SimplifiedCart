# SimplifiedCart
A basic model for a cart

ENDPOINTS

#Product:
- Create a new product: Post: /ecommerce/v1/product

Body Request Example: 

{

    "name":"mate",
    "description":"taragui",
    "sku":"ss3e4r222ad",
    "price": 20,
    "discount": true
}

- Find a product by id: Get: /ecommerce/v1/product/{idProduct}

- Update an existing product. Put: /ecommerce/v1/product/{idProduct}

Body Request Example: 

{

    "name":"Cindor",    
    "description":"chocolatada",
    "sku":"ss3e4r222ad",
    "price": 20,
    "discount": true
}

- Delete a product by id: Delete: /ecommerce/v1/product/{idProduct}

- List all the existing products: Get: /ecommerce/v1/product/all

#Cart

- Create a new Cart: Post: /ecommerce/v1/cart

- Add a new product to a cart: Put: /ecommerce/v1/cart/{idCart}

Body Request Example: 

{

    "product":  {
                "id": "bdd840aa-3fc3-406d-84be-b40646095eb1",
                "name":"jabon",
                "description":"en polvo",
                "sku":"dsksad3ds27",
                "price":33
            },
    "amount": 2
}

- Update the amount of a existing product in a cart: Put: /ecommerce/v1/cart/{idCart}

{

    "product":  {
                "id": "bdd840aa-3fc3-406d-84be-b40646095eb1",
                "name":"jabon",
                "description":"en polvo",
                "sku":"dsksad3ds27",
                "price":33
            },
    "amount": 24
}

- Delete a product from a cart: Delete: ecommerce/v1/cart/{idCart} 
    Query Params: productId : {idProduct}

- List the products belonging to a cart: Get: /ecommerce/v1/cart/{idCart}

- Checkout a cart: Put /ecommerce/v1/cart/checkout/{idCart}
