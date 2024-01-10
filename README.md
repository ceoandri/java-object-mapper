# java-object-mapper

## Prerequisite
- Java 11 or later

## How to use this library for your maven project
1. Add this repository setting in your `pom.xml`
```xml
<repositories>
	<repository>
		<id>contoh-gratis</id>
		<name>contoh-gratis</name>
		<url>https://nexus.contoh.gratis/repository/maven-public/</url>
	</repository>
</repositories>
```
2. Add the dependency you need inside `pom.xml`.
```xml
<dependency>
	<groupId>gratis.contoh</groupId>
	<artifactId>mapper</artifactId>
	<version>1.0.1</version>
</dependency>
```
3. Run `mvn clean install` inside your project directory

## Simple Java Code
```
public class App {
    public static void main(String[] args) {
        ObjectMapper<ModelA, ModelB> mapper = new MapperTemplate<>(
            ModelA.class, 
            ModelB.class,
            new FieldMapper("field1From", "field1To"),
            new FieldMapper("field2From", "field2To"));
            
        ModelA modelA = new Model();
        ModelB modelB = mapper.convert(model);
    }
}

class ModelA {
    // ...properties
}

class ModelB {
    // ...properties
}
```
 
## Object Mapper Configuration Code (Springboot)
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

## Object Mapper Usage Code (Springboot)
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
