package gratis.contoh;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.FieldNamingPolicy;

import gratis.contoh.mapper.FieldMapper;
import gratis.contoh.mapper.MapperTemplate;
import gratis.contoh.mapper.ObjectMapper;
import gratis.contoh.model.*;

public class ListObjectTest 
{

    @Test
    public void Test1MappingTwoObjectListIdentical()
    {
    	ObjectMapper<Test1ModelA, Test1ModelB> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelB.class);
    	
    	List<Test1ModelA> modelAArr = new ArrayList<>();
    	Test1ModelA modelATemp;
    	for (int i = 0; i < 10; i++) {
    		modelATemp = Test1ModelA.builder()
    				.field1("This is field 1 item " + i)
    				.field2("This is field 2 item " + i)
    				.field3("This is field 3 item " + i)
    				.build();
    		modelAArr.add(modelATemp);
    	}
    	
    	List<Test1ModelB> modelBArr = mapper.convertList(modelAArr);
        
        for (int i = 0; i < modelBArr.size(); i++) {
        	assertTrue("Field 1 result value not equals", modelBArr.get(i).getField1().equals(modelAArr.get(i).getField1()));
        	assertTrue("Field 2 result value not equals", modelBArr.get(i).getField2().equals(modelAArr.get(i).getField2()));
        	assertTrue("Field 3 result value not equals", modelBArr.get(i).getField3().equals(modelAArr.get(i).getField3()));        	
        }
    }
    
    @Test
    public void Test1MappingTwoObjectListWithDifferentNameField()
    {
    	ObjectMapper<Test1ModelA, Test1ModelC> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelC.class,
            new FieldMapper("field1", "fieldDifferent1"),
            new FieldMapper("field2", "fieldDifferent2"));
            
    	List<Test1ModelA> modelAArr = new ArrayList<>();
    	Test1ModelA modelATemp;
    	for (int i = 0; i < 10; i++) {
    		modelATemp = Test1ModelA.builder()
    				.field1("This is field 1 item " + i)
    				.field2("This is field 2 item " + i)
    				.field3("This is field 3 item " + i)
    				.build();
    		modelAArr.add(modelATemp);
    	}
    	
    	List<Test1ModelC> modelCArr = mapper.convertList(modelAArr);
        
        for (int i = 0; i < modelCArr.size(); i++) {
        	assertTrue("Field 1 result value not equals", modelCArr.get(i).getFieldDifferent1().equals(modelAArr.get(i).getField1()));
        	assertTrue("Field 2 result value not equals", modelCArr.get(i).getFieldDifferent2().equals(modelAArr.get(i).getField2()));    
        	assertNull("Field 3 result value not null", modelCArr.get(i).getFieldDifferent3());
        }
    }
    
    @Test
    public void Test1MappingTwoObjectListIdenticalWithNamingPolicyParam()
    {
    	ObjectMapper<Test1ModelA, Test1ModelB> mapper = new MapperTemplate<>(
        		Test1ModelA.class, 
                Test1ModelB.class);
    	List<Test1ModelA> modelAArr = new ArrayList<>();
    	Test1ModelA modelATemp;
    	for (int i = 0; i < 10; i++) {
    		modelATemp = Test1ModelA.builder()
    				.field1("This is field 1 item " + i)
    				.field2("This is field 2 item " + i)
    				.field3("This is field 3 item " + i)
    				.build();
    		modelAArr.add(modelATemp);
    	}
    	
    	List<Test1ModelB> modelBArr = mapper.convertList(modelAArr, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        
        for (int i = 0; i < modelBArr.size(); i++) {
        	assertTrue("Field 1 result value not equals", modelBArr.get(i).getField1().equals(modelAArr.get(i).getField1()));
        	assertTrue("Field 2 result value not equals", modelBArr.get(i).getField2().equals(modelAArr.get(i).getField2()));
        	assertTrue("Field 3 result value not equals", modelBArr.get(i).getField3().equals(modelAArr.get(i).getField3()));  
        }
    }
    
    @Test
    public void Test1MappingTwoObjectListWithDifferentNameFieldAndNamingPolicyParam()
    {
    	ObjectMapper<Test1ModelA, Test1ModelC> mapper = new MapperTemplate<>(
    		Test1ModelA.class, 
            Test1ModelC.class,
            new FieldMapper("field1", "field_different1"),
            new FieldMapper("field2", "field_different2"));
            
    	List<Test1ModelA> modelAArr = new ArrayList<>();
    	Test1ModelA modelATemp;
    	for (int i = 0; i < 10; i++) {
    		modelATemp = Test1ModelA.builder()
    				.field1("This is field 1 item " + i)
    				.field2("This is field 2 item " + i)
    				.field3("This is field 3 item " + i)
    				.build();
    		modelAArr.add(modelATemp);
    	}
    	
    	List<Test1ModelC> modelCArr = mapper.convertList(modelAArr, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        
        for (int i = 0; i < modelCArr.size(); i++) {
        	assertTrue("Field 1 result value not equals", modelCArr.get(i).getFieldDifferent1().equals(modelAArr.get(i).getField1()));
        	assertTrue("Field 2 result value not equals", modelCArr.get(i).getFieldDifferent2().equals(modelAArr.get(i).getField2()));    
        	assertNull("Field 3 result value not null", modelCArr.get(i).getFieldDifferent3());
        }
    }
}
