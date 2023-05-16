package gratis.contoh.mapper;

import java.util.List;

import com.google.gson.FieldNamingPolicy;

public interface ObjectMapper<T, Z> {
	
	public Z convert(
			Object source, 
			FieldNamingPolicy... namingPolicy);
	
	public List<Z> convertList(
			Object source, 
			FieldNamingPolicy... namingPolicy);
	
}
