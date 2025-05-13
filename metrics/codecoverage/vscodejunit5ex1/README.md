## Code Coverage in Visual Studio Code

This is an example for obtaining code coverage 
for unmanaged Java projects in Visual Studio
Code.

In this example, test cases are written with
JUnit 5.

### Directories

Create the `bin` and `lib` directories if they aren't present:

```bash
mkdir bin lib
```

### JUnit 5

Download JUnit 5 libary to the `lib` directory:

```bash
JUNIT5_PCS_VER=1.13.0-M3
JAR_FILE=junit-platform-console-standalone-${JUNIT5_PCS_VER}.jar
curl \
  "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/${JUNIT5_PCS_VER}/${JAR_FILE}" \
  -o "lib/${JAR_FILE}"
```

### Visual Studio Code

Install the following extensions for Java:

- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)


### Code Coverage

To obtain code coverage, do the following:

1. Start Visual Studio Code, i.e., issue command `code`
1. Open a Java file
1. Wait for the activation for Java extensions to complete
1. Click the "Test Tube" icon on the sidebar
1. Locate the test suite
1. Right-click the mouse, and select "Run Test With Coverage"


