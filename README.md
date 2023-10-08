# json5-java 
[![Build](https://img.shields.io/github/actions/workflow/status/marhali/json5-java/cd.yml?branch=main)](https://github.com/marhali/json5-java/actions)
[![JavaDoc](https://javadoc.io/badge2/de.marhali/json5-java/javadoc.svg)](https://javadoc.io/doc/de.marhali/json5-java)
[![Coverage](https://img.shields.io/codecov/c/github/marhali/json5-java)](https://codecov.io/gh/marhali/json5-java)
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://paypal.me/marhalide)

This is a reference implementation of the [JSON5 standard](https://json5.org/) in Java 11+, 
capable of parsing and serialization of JSON5 data.

This library is an enhanced version of [Synt4xErr0r4 / json5](https://github.com/Synt4xErr0r4/json5), 
which provides a better full-fledged API inspired by the [GSON](https://github.com/google/gson) library.

## Download
Download the [latest release](https://search.maven.org/artifact/de.marhali/json5-java) manually or add a Maven dependency. 
Don't worry the project is already in the Maven Central Repository. Just add the following configuration:
```xml
<dependencies>
    <dependency>
        <groupId>de.marhali</groupId>
        <artifactId>json5-java</artifactId>
        <version>2.0.0</version>
    </dependency>
</dependencies>
```

## Usage
This library can be used by either configuring a [Json5](src/main/java/de/marhali/json5/Json5.java) 
instance or by using the underlying [Json5Parser](src/main/java/de/marhali/json5/stream/Json5Parser.java) 
and [Json5Writer](src/main/java/de/marhali/json5/stream/Json5Writer.java).

The following section describes how to use this library with the 
[Json5](src/main/java/de/marhali/json5/Json5.java) core class.

### Configure Json5 instance
See [Parsing & Serialization Options](#parsing--serialization-options) to see a list of possible configuration options.
```java
// Using builder pattern
Json5 json5 = Json5.builder(options ->
        options.allowInvalidSurrogate().quoteSingle().prettyPrinting().build());

// Using configuration object
Json5Options options = new Json5Options(true, true, true, 2);
Json5 json5 = new Json5(options);
```

### Parsing 
```java
Json5 json5 = ...

// Parse from a String literal
Json5Element element = 
        json5.parse("{ 'key': 'value', 'array': ['first val','second val'] }");

// ...

// Parse from a Reader or InputStream
try(InputStream stream = ...) {
    Json5Element element = json5.parse(stream);
    // ...
} catch (IOException e) {
    // ...
}
```

### Serialization
```java
Json5Element element = ...

// Serialize to a String literal
String jsonString = json5.serialize(element);

// ...

// Serialize to a Writer or OutputStream        
try(OutputStream stream = ...) {
    json5.serialize(element, stream);
    // ...
} catch (IOException e) {
    // ...
}
```



## Documentation

Detailed javadoc documentation can be found at [javadoc.io](https://javadoc.io/doc/de.marhali/json5-java).

### Parsing & Serialization Options
This library supports a few customizations to adjust the behaviour of parsing and serialization.
For a detailed explanation see the [Json5Options](src/main/java/de/marhali/json5/Json5Options.java) class.

- allowInvalidSurrogates
- quoteSingle
- trailingComma
- indentFactor

## License
This library is released under the [Apache 2.0 license](LICENSE).

Partial parts of the project are based on [GSON](https://github.com/google/gson) and [Synt4xErr0r4 / json5](https://github.com/Synt4xErr0r4/json5). The affected classes contain the respective license notice. 



1.添加记录 Json5Object节点的单行注释功能，可以读取某个KV对的注释信息，注释与key名称进行关联

```java
Json5 json5 = Json5.builder(builder ->
        builder.allowInvalidSurrogate()
                .quoteSingle()
                .indentFactor(2)
                .build()
                .remainComment(true)); // 保留注释
```

测试json5文本如下：这里逗号后的注释视为是处在该行的键值对的注释

```json5
{
  "Code": 200,
  "Msg": "成功",
  "Data": [
    {
      name: "zs",
      "failTRate": 0.1875,    //  this is single-line comment
      "roomTNum": 64,    //  this is single-line comment
      "useTRate": 1.968823,    //  this is single-line comment
      "schoolId": "S-3333",    //  this is single-line comment
      "roomUseCount": 3    //  this is single-line comment
    }
  ]
}
```

测试代码

```java
try (InputStream stream = getTestResource("test.comment.json5")) {
    Json5Element element = json5.parse(stream);
    if (element.isJson5Object()) {
        Json5Object asJson5Object = element.getAsJson5Object();
        Json5Array array = asJson5Object.getAsJson5Array("Data");
        Json5Object object = array.getAsJson5Object(0);
        for (String key : object.keySet()) {
            System.out.println(key);
            System.out.println(object.getComment(key)); // 获取指定key的注释信息
        }
    }
} catch (IOException e) {
    throw new RuntimeException(e);
}
```



```plain
Jankson - 解析json5和HJSON
https://github.com/falkreon/Jankson

json5.java - 解析json5 (已废弃)
https://github.com/brimworks/json5.java

json5-java
https://github.com/marhali/json5-java

另一个json5库
https://github.com/Synt4xErr0r4/json5
```



使用maven-javadoc-plugin生成中文文档时，IDEA控制台中文乱码，File | Settings | Build, Execution, Deployment | Build Tools | Maven | Runner在VM Options中添加-Dfile.encoding=GBK，，切记一定是GBK。即使用UTF-8的话，依然是乱码，这是因为Maven的默认平台编码是GBK，如果你在命令行中输入mvn -version的话，会得到如下信息，根据Default locale可以看出

```shell
➜ mvn -version
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
Maven home: D:\Develop\Tools\apache-maven-3.8.1\bin\..
Java version: 11.0.15.1, vendor: Oracle Corporation, runtime: D:\Develop\JDK\jdk-11.0.15.1
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```





打包命令

maven install

maven package

















