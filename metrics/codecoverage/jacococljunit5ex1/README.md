## JaCoCo for Code Coverage

This example demonstrates obtaining code coverage using
JaCoCo on the command line.

### Source Code

Obtain the source of the example. For instance, copy
the code from another example:

```bash
cp -rv ../vscodejunit5ex1/src .
```

### Directories

Create directories.

```bash
mkdir bin lib report
```
### JUnit 5

Download the JUnit 5 library.

```bash
JUNIT5_PCS_VER=1.13.0-M3
JAR_FILE=junit-platform-console-standalone-${JUNIT5_PCS_VER}.jar
curl \
  "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/${JUNIT5_PCS_VER}/${JAR_FILE}" \
  -o "lib/${JAR_FILE}"
```
### JaCoCo

Download the JaCoCo Agent and Command Line Interface tools.

```bash
JACOCO_VER=0.8.13
ZIP_FILE=jacoco-${JACOCO_VER}.zip
curl \
  -L \
  "https://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/${JACOCO_VER}/${ZIP_FILE}" \
  -o "${ZIP_FILE}"
unzip -j "${ZIP_FILE}" lib/*.jar -d lib2
mv -v lib2/* lib
rmdir -v lib2
rm -v "${ZIP_FILE}"
```

## Code Coverage

1. Compile the source code

   ```bash
   JUNIT5_VER=1.13.0-M3.jar
   javac \
     -cp "lib/junit-platform-console-standalone-${JUNIT5_VER}:bin" \
     -d bin \
     src\test\TestClient.java src\main\Client.java
   ```

1. Generate JaCoCo code coverage report

   ```bash
   java -javaagent:lib/jacocoagent.jar=destfile=report/jacoco.exec \
     -jar lib/junit-platform-console-standalone-1.13.0-M3.jar \
     --class-path bin/ \
     --scan-class-path
   ```

1. Interpret the report

   ```bash
   java -jar lib\jacococli.jar report report/jacoco.exec \
     --classfiles bin \
     --sourcefiles src \
     --html report/html \
     --xml report/report.xml \
     --csv report/report.csv
   ```

1. View the report. The above produces the code coverage reports in three
   formats, `html`, `xml`, and `csv`. For instance, to review the `html`
   format of the report, open `report/html/index.html` using a Web browser.
