package gratis.contoh;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gratis.contoh.mapper.FieldMapper;
import gratis.contoh.mapper.MapperTemplate;
import gratis.contoh.model.Test1ModelA;
import gratis.contoh.model.Test1ModelB;

public class ObjectMapperTest {
	
	@Test
    public void TestCreateMapperWithoutFieldMapper()
    {
    	MapperTemplate<Test1ModelA, Test1ModelB> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelB.class);
    	
    	assertTrue("FieldMapper size not 0", mapper.getFields().size() == 0);
    	assertTrue("Origin Class not equals", mapper.getOriginClass().equals(Test1ModelA.class));
    	assertTrue("Destination Class not equals", mapper.getDestinationClass().equals(Test1ModelB.class));
    }
	
	@Test
    public void TestCreateMapperWithFieldMapper()
    {
    	MapperTemplate<Test1ModelA, Test1ModelB> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelB.class,
            new FieldMapper("field1", "fieldDifferent1"),
            new FieldMapper("field2", "fieldDifferent2"));
    	
    	assertTrue("FieldMapper size not 2", mapper.getFields().size() == 2);
    	assertTrue("Origin Class not equals", mapper.getOriginClass().equals(Test1ModelA.class));
    	assertTrue("Destination Class not equals", mapper.getDestinationClass().equals(Test1ModelB.class));
    }
	
}
