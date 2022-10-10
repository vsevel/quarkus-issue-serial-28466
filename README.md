# Quarkus Myext

Reproducer for issue https://github.com/quarkusio/quarkus/issues/28466

```
mvn clean install -Pnative-image -Dquarkus.native.container-build=true
cd integration-tests
docker build -f src/main/docker/Dockerfile.native-micro -t quarkus/myext-test .
docker run -i --rm -p 8080:8080 quarkus/myext-test
curl http://localhost:8080/myext
```
