# java-object-mapper

## Prerequisite
- Java 11 or later

## How to use this library for your maven project
1. Add this repository setting in your `pom.xml`
```xml
<repositories>
	<repository>
		<id>repo-contoh-gratis</id>
		<name>repo-contoh-gratis</name>
		<url>http://repo.contoh.gratis:81/repository/maven-public/</url>
	</repository>
</repositories>
```
2. Add the dependency you need inside `pom.xml`.
```xml
<dependency>
	<groupId>gratis.contoh</groupId>
	<artifactId>mapper</artifactId>
	<version>1.0.0</version>
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
    private ObjectMapper<ModelA, ModelB> mapper;

    @PostMapping("")
    public ResponseEntity<ModelB> mapperTest(@RequestBody @Valid ModelA model) {
        return ResponseEntity.ok(mapper.convert(model));
    }
	
}
```
