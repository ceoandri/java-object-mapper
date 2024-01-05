package gratis.contoh;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDateTime;

import org.junit.Test;

import com.google.gson.FieldNamingPolicy;

import gratis.contoh.mapper.FieldMapper;
import gratis.contoh.mapper.MapperTemplate;
import gratis.contoh.mapper.ObjectMapper;
import gratis.contoh.model.*;

public class SingleObjectTest 
{

    @Test
    public void Test1MappingTwoObjectIdentical()
    {
    	ObjectMapper<Test1ModelA, Test1ModelB> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelB.class);
            
    	Test1ModelA modelA = Test1ModelA.builder()
    							.field1("This is field 1")
    							.field2("This is field 2")
    							.field3("This is field 3")
    							.build();
    	Test1ModelB modelB = mapper.convert(modelA);
        assertTrue("Field 1 result value not equals", modelB.getField1().equals(modelA.getField1()));
        assertTrue("Field 2 result value not equals", modelB.getField2().equals(modelA.getField2()));
        assertTrue("Field 3 result value not equals", modelB.getField3().equals(modelA.getField3()));
    }
    
    @Test
    public void Test1MappingTwoObjectWithDifferentNameField()
    {
    	ObjectMapper<Test1ModelA, Test1ModelC> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelC.class,
            new FieldMapper("field1", "fieldDifferent1"),
            new FieldMapper("field2", "fieldDifferent2"));
            
    	Test1ModelA modelA = Test1ModelA.builder()
    							.field1("This is field 1")
    							.field2("This is field 2")
    							.field3("This is field 3")
    							.build();
    	Test1ModelC modelC = mapper.convert(modelA);
        assertTrue("Field 1 result value not equals", modelC.getFieldDifferent1().equals(modelA.getField1()));
        assertTrue("Field 2 result value not equals", modelC.getFieldDifferent2().equals(modelA.getField2()));
        assertNull("Field 3 result value not null", modelC.getFieldDifferent3());
    }
    
    @Test
    public void Test1MappingTwoObjectIdenticalWithNamingPolicyParam()
    {
    	ObjectMapper<Test1ModelA, Test1ModelB> mapper = new MapperTemplate<>(
        		Test1ModelA.class, 
                Test1ModelB.class);
            
    	Test1ModelA modelA = Test1ModelA.builder()
    							.field1("This is field 1")
    							.field2("This is field 2")
    							.field3("This is field 3")
    							.build();
    	Test1ModelB modelB = mapper.convert(modelA, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        assertTrue("Field 1 result value not equals", modelB.getField1().equals(modelA.getField1()));
        assertTrue("Field 2 result value not equals", modelB.getField2().equals(modelA.getField2()));
        assertTrue("Field 3 result value not equals", modelB.getField3().equals(modelA.getField3()));
    }
    
    @Test
    public void Test1MappingTwoObjectWithDifferentNameFieldAndNamingPolicyParam()
    {
    	ObjectMapper<Test1ModelA, Test1ModelC> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelC.class,
            new FieldMapper("field1", "field_different1"),
            new FieldMapper("field2", "field_different2"));
            
    	Test1ModelA modelA = Test1ModelA.builder()
    							.field1("This is field 1")
    							.field2("This is field 2")
    							.field3("This is field 3")
    							.build();
    	Test1ModelC modelC = mapper.convert(modelA, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        assertTrue("Field 1 result value not equals", modelC.getFieldDifferent1().equals(modelA.getField1()));
        assertTrue("Field 2 result value not equals", modelC.getFieldDifferent2().equals(modelA.getField2()));
        assertNull("Field 3 result value not null", modelC.getFieldDifferent3());
    }
    
    @Test
    public void Test1MappingTwoObjectFieldDateAndLocalDateTime()
    {
    	ObjectMapper<Test1ModelA, Test1ModelB> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelB.class);
    	
    	java.util.Date currentDate = new java.util.Date();

		Test1ModelA modelA = Test1ModelA.builder()
    							.field4(new Date(currentDate.getTime()))
    							.field5(LocalDateTime.now())
    							.build();
    	Test1ModelB modelB = mapper.convert(modelA);
        assertTrue("Field 4 result value not equals", modelB.getField4().toString().equals(modelA.getField4().toString()));
        assertTrue("Field 5 result value not equals", modelB.getField5().equals(modelA.getField5()));
    }
}
