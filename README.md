![2021-06-16 (12)](https://user-images.githubusercontent.com/82329310/122496997-47142c00-d01f-11eb-8357-15ae77a2207b.png)
# web3j-maven-plugin
[![Build Status](https://travis-ci.org/web3j/web3j-maven-plugin.svg?branch=master)](https://travis-ci.org/web3j/web3j-maven-plugin)
[![codecov.io](https://codecov.io/github/web3j/web3j-maven-plugin/coverage.svg?branch=master)](https://codecov.io/github/web3j/web3j-maven-plugin?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

web3j maven plugin is used to create java classes based on the solidity contract files.

## Usage
The base configuration for the plugin will take the solidity files from `src/main/resources` and generates 
the java classes into the folder `src/main/java`.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.web3j</groupId>
            <artifactId>web3j-maven-plugin</artifactId>
            <version>4.8.1</version>
            <configuration>
                <soliditySourceFiles/>
            </configuration>
        </plugin>
    </plugins>
</build>
```

to run the plugin execute the goal `generate-sources`
```bash
mvn web3j:generate-sources
```


## Configuration
The are several variable to select the solidity source files, define a source destination path or change the package name.

| Name                   | Format                                                                                 | Default value                   |
| -----------------------|----------------------------------------------------------------------------------------| --------------------------------|
| `<packageName/>`       | valid java package name                                                                | `org.web3j.model`               |
| `<outputDirectory><java/></outputDirectory>` | relative or absolute path of the generated for 'Java files       | value in `<sourceDestination/>` |
| `<outputDirectory><bin/></outputDirectory>`  | relative or absolute path of the generated for 'Bin' files       | value in `<sourceDestination/>` |
| `<outputDirectory><abi/></outputDirectory>`  | relative or absolute path of the generated for 'ABI' files       | value in `<sourceDestination/>` |
| `<sourceDestination/>` | relative or absolute path of the generated files (java, bin, abi)                      | `src/main/java`                 |
| `<outputFormat/>`      | generate Java Classes(`java`), ABI(`abi`) and/or BIN (`bin`) Files (comma separated)   | `java`                          |
| `<nativeJavaType/>`    | Creates Java Native Types (instead of Solidity Types)                                  | `true`                          |
| `<outputJavaParentContractClassName/>` | Sets custom(? extends org.web3j.tx.Contract) class as a parent for java generated code | `org.web3j.tx.Contract` |
| `<soliditySourceFiles>`| Standard maven [fileset](https://maven.apache.org/shared/file-management/fileset.html) | `<soliditySourceFiles>`<br>`  <directory>src/main/resources</directory>`<br>`  <includes>`<br>`    <include>**/*.sol</include>`<br>`  </includes>`<br>`</soliditySourceFiles>`  |
| `<abiSourceFiles>`     | Standard maven [fileset](https://maven.apache.org/shared/file-management/fileset.html) | `<abiSourceFiles>`<br>`  <directory>src/main/resources</directory>`<br>`  <includes>`<br>`    <include>**/*.json</include>`<br>`  </includes>`<br>`</abiSourceFiles>`           |
| `<contract>`           | Filter (`<include>` or `<exclude>`) contracts based on the name.                       | `<contract>`<br>`  <includes>`<br>`    <include>greeter</include>`<br>`  </includes>`<br>`  <excludes>`<br>`    <exclude>mortal</exclude>`<br>`  <excludes>`<br>`</contracts>`  |
| `<pathPrefixes>`       | A list (`<pathPrefixe>`) of replacements of dependency replacements inside Solidity contract.  |  |

Configuration of `outputDirectory` has priority over `sourceDestination`


## Getting Started

Create a standard java maven project. Add following `<plugin>` - configuration into the `pom.xml` file:
web3j Maven plugin 的pom.xml檔案

```xml
<plugin>
    <groupId>org.web3j</groupId>
    <artifactId>web3j-maven-plugin</artifactId>
    <version>4.8.1</version>
    <configuration>
        <packageName>com.zuehlke.blockchain.model</packageName>
        <sourceDestination>src/main/java/generated</sourceDestination>
        <nativeJavaType>true</nativeJavaType>
        <outputFormat>java,bin</outputFormat>
        <soliditySourceFiles>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.sol</include>
            </includes>
        </soliditySourceFiles>
        <abiSourceFiles>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.json</include>
            </includes>
        </abiSourceFiles>
        <outputDirectory>
            <java>src/java/generated</java>
            <bin>src/bin/generated</bin>
            <abi>src/abi/generated</abi>
        </outputDirectory>
        <contract>
            <includes>
                <include>greeter</include>
            </includes>
            <excludes>
                <exclude>mortal</exclude>
            </excludes>
        </contract>
        <pathPrefixes>
            <pathPrefix>dep=../dependencies</pathPrefix>
        </pathPrefixes>
    </configuration>
</plugin>
```




系統操作流程


![2021-06-16 (13)](https://user-images.githubusercontent.com/82329310/122493482-5cd32280-d01a-11eb-9e27-8ccc3de8e2f6.png)

執行程式後會看到登入畫面，在文字框內輸入自己帳號的私鑰便可登入。

![2021-06-16 (12)](https://user-images.githubusercontent.com/82329310/122497022-5004fd80-d01f-11eb-812d-2b501348c725.png)

如果登入者微系統預設管理者的私鑰則會出現部屬合約的視窗，管理者輸入候選人的名字後發布合約(預設候選人數為三個)。

![2021-06-18 (2)](https://user-images.githubusercontent.com/82329310/122495379-81c89500-d01c-11eb-969b-37ab40ff9373.png)

看到跳出視窗代表合約部屬成功

![2021-06-18 (9)](https://user-images.githubusercontent.com/82329310/122497609-3912db00-d020-11eb-8322-699c6ec08411.png)
![2021-06-18 (8)](https://user-images.githubusercontent.com/82329310/122499381-6ca33480-d023-11eb-84f3-6b7e753fc322.png)

接著管理者可以看到管理者的操縱畫面，有start ballot 跟 end ballot兩個按鈕，管理者按下 start ballot 後投票者才能開始投票，當管理者按下結束投票，管理者視窗會跳出最終結果畫面。
但如果投票人要看到投票結果要按下顯示選舉結果按鈕，如果投票已經結束，才會顯示出結果視窗。另外refresh按鈕可以讓管理者在部屬合約時更新管理者畫面。

![2021-06-18 (12)](https://user-images.githubusercontent.com/82329310/122497911-c0604e80-d020-11eb-9d08-6e06af902db7.png)

結果頁面有每個候選人獲取的票數和當選者的名字顯示在最上方。






