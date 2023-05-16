# java-object-mapper

## How to use this library for your maven project
1. Add this repository setting in your `pom.xml`
    ```xml
    <repositories>
        <repository>
            <id>nexus-solecode-snapshots</id>
            <name>nexus-solecode-snapshots</name>
            <url>https://nexus.solecode.tech/repository/maven-snapshots/</url>
        </repository>
    </repositories>
    ```
2. Add the dependency you need inside `pom.xml`.
    ```xml
    <dependency>
        <groupId>gratis.contoh</groupId>
        <artifactId>mapper</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>
    ```
3. Run `mvn clean install` inside your project directory

## Object Mapper Configuration Code
```
@Configuration
public class AutoMapperConfiguration {
	
    @Bean
    ObjectMapper<ModelA, ModelB> modelAModelBMapper() {
        return new MapperTemplate<>(
            ModelA.class, 
            ModelB.class,
            new FieldMapper("field1From", "field1To"),
            new FieldMapper("field2From", "field2To"));
    }
    
}
```
You can create more bean as much as you need

## Object Mapper Usage Code
```
@RestController
@RequestMapping("/mapper")
public class MapperSampleController {
	
    @Autowired
    private ObjectMapper<ModelA, ModelAResponse> mapper;

    @PostMapping("")
    public ResponseEntity<ModelB> mapperTest(@RequestBody @Valid ModelA model) {
        return ResponseEntity.ok(mapper.convert(model));
    }
	
}
```
