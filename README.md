# sum-service

Spring Boot 4.1.1 / Java 21 REST API with Log4j2.

    GET /api/sum?a=2&b=3   ->  {"a":2,"b":3,"result":5}

Invalid or missing parameters return HTTP 400 with `{status, error, message}`; unexpected errors return 500 without internal details.

Run: `mvn spring-boot:run` · Test + enforce 100% line/branch coverage: `mvn verify` (report in `target/site/jacoco`).
