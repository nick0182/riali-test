execute **GET http://riali-env.eba-nr3f3kh2.eu-west-2.elasticbeanstalk.com/listings with the following params (optional):**

 * page
 * page_size
 * min_price
 * max_price
 * min_bed
 * max_bed
 * min_bath
 * max_bath

To improve:
 - 
 * implement reactive flow (for example, using Spring reactive web framework and non-blocking jdbc driver)
 * deploy on several nodes with load balancing
 * improve test coverage
 * turn on Spring actuator and metrics
 * configure Swagger