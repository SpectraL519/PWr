# PWr - Technologie Programowania: Maven instructions

### Creating maven projects:

* Creating a maven project
```
mvn archetype:generate -DgroupId="com.PROJECT_NAME.app" -DartifactId="APP_NAME" -DarchetypeArtifactId="maven-archetype-quickstart" -DarchetypeVersion="1.4" -DinteractiveMode="false"
```

* Creating a maven subproject:
```
mvn archetype:generate -DgroupId="com.PROJECT_NAME.SUBPROJECT_NAME" -DartifactId="SUBAPP_NAME" -DarchetypeArtifactId="maven-archetype-quickstart" -DarchetypeVersion="1.4" -DinteractiveMode="false"
```

<br />
<br />

### Building and running a maven project

* Entering the project app directory
```
cd APP_NAME (SUBAPP_NAME)
```

<br />

* Building the project
```
mvn package
```

<br />

> If running on linux:
> ```
> export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
> ```

* Running the project code using `java` command:

```
java -cp target/APP_NAME-1.0-SNAPSHOT.jar com.PROJECT_NAME.app (com.PROJECT_NAME.SUBPROJECT_NAME.SUBAPP_NAME)
```

* Running the project using `mvn exec` command:

> Add the following to the `pom.xml` file:
> ```
> <project>
> ...
>   <properties>
>    ...
>    <exec.mainClass>com.CheckersGame.Server.GameServer</exec.mainClass>
>    ...
>  </properties>
> ...
> </project>
> ```

```
mvn clean compile exec:java
```


<br />
<br />

### Adding plugins to a maven project

* JavaFX - add the following to the `pom.xml` file:

```
<project>
  ...
  <dependencies>
    ...
    <dependency>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-controls</artifactId>
      <version>19</version>
    </dependency>
    ...
  </dependencies>
  ...
  <build>
    <pluginManagement>
      <plugins>
        ...
        <plugin>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-maven-plugin</artifactId>
          <version>0.0.8</version>
          <configuration>
            <mainClass>com.PROJECT_NAME.MAIN_FX_CLASS</mainClass>
          </configuration>
        </plugin>
        ...
      </plugins>
    </pluginManagement>
  </build>
  ...
</project>
```

Running a JavaFX project
```
mvn clean javafx:run
```

<br />

* PMD and Checkstyle reports - add the following to the `pom.xml` file:

```
<project>
  ...
  <reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-pmd-plugin</artifactId>
        <version>3.19.0</version>
      </plugin>
      <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-checkstyle-plugin</artifactId>
          <version>3.2.0</version>
          <reportSets>
            <reportSet>
              <reports>
                <report>checkstyle</report>
              </reports>
            </reportSet>
          </reportSets>
        </plugin>
    </plugins>
  </reporting>
  ...
</project>
```

<br />
<br />
<br />

**Useful links:**

* [General info about maven](https://maven.apache.org/users/index.html)

* [Maven download](https://maven.apache.org/download.cgi)

* [Maven tutorial](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

* [Maven: Getting starget guide](https://maven.apache.org/guides/getting-started/index.html)

