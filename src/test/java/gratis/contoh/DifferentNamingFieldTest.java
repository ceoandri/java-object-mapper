package gratis.contoh;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gratis.contoh.mapper.FieldMapper;
import gratis.contoh.mapper.MapperTemplate;
import gratis.contoh.mapper.ObjectMapper;
import gratis.contoh.model.Test1ModelC;
import gratis.contoh.model.Test1ModelD;

public class DifferentNamingFieldTest {
	
    @Test
    public void Test1MappingTwoObjectSnakeCaseToCamelCase()
    {
    	ObjectMapper<Test1ModelD, Test1ModelC> mapper = new MapperTemplate<>(
    		Test1ModelD.class, 
            Test1ModelC.class,
            new FieldMapper("snakeCaseField1", "fieldDifferent1"),
            new FieldMapper("snakeCaseField2", "fieldDifferent2"),
        	new FieldMapper("snakeCaseField3", "fieldDifferent3"));
            
    	Test1ModelD modelD = Test1ModelD.builder()
    							.snakeCaseField1("This is field 1")
    							.snakeCaseField2("This is field 2")
    							.snakeCaseField3("This is field 3")
    							.build();
    	Test1ModelC modelC = mapper.convert(modelD);
        assertTrue("Field 1 result value not equals", modelC.getFieldDifferent1().equals(modelD.getSnakeCaseField1()));
        assertTrue("Field 2 result value not equals", modelC.getFieldDifferent2().equals(modelD.getSnakeCaseField2()));
        assertTrue("Field 3 result value not equals", modelC.getFieldDifferent3().equals(modelD.getSnakeCaseField3()));
    }
	
    @Test
    public void Test1MappingTwoObjectCamelCaseToSnakeCase()
    {
    	ObjectMapper<Test1ModelC, Test1ModelD> mapper = new MapperTemplate<>(
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
    	Test1ModelD modelD = mapper.convert(modelC);
        assertTrue("Field 1 result value not equals", modelC.getFieldDifferent1().equals(modelD.getSnakeCaseField1()));
        assertTrue("Field 2 result value not equals", modelC.getFieldDifferent2().equals(modelD.getSnakeCaseField2()));
        assertTrue("Field 3 result value not equals", modelC.getFieldDifferent3().equals(modelD.getSnakeCaseField3()));
    }
    
}
