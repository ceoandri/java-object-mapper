package gratis.contoh;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gratis.contoh.mapper.FieldMapper;
import gratis.contoh.mapper.MapperTemplate;
import gratis.contoh.model.Test1ModelA;
import gratis.contoh.model.Test1ModelB;
import gratis.contoh.model.Test1ModelC;
import gratis.contoh.model.Test1ModelD;

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
	
	@Test
    public void TestGetAsJsonString()
    {
    	MapperTemplate<Test1ModelC, Test1ModelD> mapper = new MapperTemplate<>(
    		Test1ModelC.class, 
            Test1ModelD.class,
            new FieldMapper("fieldDifferent1", "snakeCaseField1"),
            new FieldMapper("fieldDifferent2", "snakeCaseField2"),
        	new FieldMapper("fieldDifferent3", "snakeCaseField3"));
    	
    	Test1ModelC modelC = Test1ModelC.builder()
				.fieldDifferent1("This is field 1")
				.fieldDifferent2("This is field 2")
				.fieldDifferent3("This is field 3")
				.build();
    	
    	String modelCJsonString = mapper.getOriginAsJsonString(modelC);
    	String modelDJsonString = mapper.getDestinationAsJsonString(mapper.convert(modelC));
    	
    	String modelCExpected = "{\"fieldDifferent1\":\"This is field 1\",\"fieldDifferent2\":\"This is field 2\",\"fieldDifferent3\":\"This is field 3\"}";
    	String modelDExpected = "{\"snakeCaseField1\":\"This is field 1\",\"snakeCaseField2\":\"This is field 2\",\"snakeCaseField3\":\"This is field 3\"}";
    	assertTrue("Result origin not same as expected", modelCJsonString.equals(modelCExpected));
    	assertTrue("Result destination not same as expected", modelDJsonString.equals(modelDExpected));
    }
	
}
