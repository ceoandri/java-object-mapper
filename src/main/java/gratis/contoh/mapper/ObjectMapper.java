package gratis.contoh.mapper;

import java.util.List;

import com.google.gson.FieldNamingPolicy;

public interface ObjectMapper<T, Z> {
	
	public Z convert(
			T source, 
			FieldNamingPolicy... namingPolicy);
	
	public List<Z> convertList(
			List<T> source, 
			FieldNamingPolicy... namingPolicy);
	
	public String getOriginAsJsonString(T object);
	
	public String getDestinationAsJsonString(Z object);
	
}
