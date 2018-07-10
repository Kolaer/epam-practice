Gift guessing game on Spring MVC.

To run the project run this command in your shell:
```
$ mvn clean package
$ mvn tomcat7:run 
```
Site will be available at `localhost:8080`.

---

To run in docker:

1) Build a docker image:

```
$ docker build --tag <tag> .
```

2) Run it:

```
$ docker run --rm -p 8080:8080 <tag>
```

--rm - remove container after stopping application  
-p - port forwarding
