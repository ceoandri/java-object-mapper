package gratis.contoh.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Test1ModelD {
	@JsonProperty("snake_case_field1")
	String snakeCaseField1;
	
	@JsonProperty("snake_case_field2")
	String snakeCaseField2;
	
	@JsonProperty("snake_case_field3")
	String snakeCaseField3;
}
