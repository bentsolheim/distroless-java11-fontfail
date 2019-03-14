# Demonstration of failing distroless java 11

Build with

    ./gradlew jibDockerBuild
    
This will produce the image ```bentsolheim/distroless-java11-fontfail```

Run with

    docker run -it -p 8080:8080 bentsolheim/distroless-java11-fontfail
    
This should blow up on startup with an error.

If you adjust `jib.from.image` in `build.gradle` to something like `openjdk:11.0.2-jdk-slim-stretch` 
the application will start up successfully and you should be able to download the generated xls-file with

    curl http://localhost:8080 -O -J
    
The example will probably run fine in your local ide.