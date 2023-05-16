package gratis.contoh.mapper;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FieldMapper implements Serializable {
	
	private static final long serialVersionUID = 3424491883488813858L;
	
	private String from;
	private String to;

}
