boogle
======

A typical search engine written in Java8 and Spark

To run and compile, you'll need Java8 and Gradle.

To index a new page:
curl -X POST -H "Content-Type: application/json" -d '{"content" : "This is a journey into sound", "pageId" : "1"}' http://localhost:4567/index

To search:
curl -X GET http://localhost:4567/search?q=you+can+search

