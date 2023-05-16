package gratis.contoh.mapper;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;

@Data
public class MapperTemplate<T, Z> implements ObjectMapper<T, Z> {
	
	private Class<T> originClass;
	private Class<Z> destinationClass;
	private List<FieldMapper> fields;
	private Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, new GsonDateAdapter())
			.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
		    .create();
	
	public MapperTemplate(
			Class<T> originClass,
			Class<Z> destinationClass,
			FieldMapper... fieldMapper) {
		
		this.originClass = originClass;
		this.destinationClass = destinationClass;
		constructFieldMapper(fieldMapper);
		
	}
	
	private void constructFieldMapper(FieldMapper... fieldMapper) {
		
		this.fields = new ArrayList<>();
		
		for (int i = 0; i < fieldMapper.length; i++) {
			this.fields.add(fieldMapper[i]);
		}
		
	}
	
	public Z convert( 
			Object source, 
			FieldNamingPolicy... namingPolicy) {
		
		reformGson(namingPolicy);
		
		String json = gson.toJson(source);
		json = reformJson(json);
		Z res = gson.fromJson(json, destinationClass);
		
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Z> convertList(
			Object source, 
			FieldNamingPolicy... namingPolicy) {
		
		List<Z> res = new ArrayList<Z>();
		
		reformGson(namingPolicy);
		
		String json = gson.toJson(source);
		json = reformJson(json);
		List<Object> sourceList = gson.fromJson(json, List.class);
		
		for (int i = 0; i < sourceList.size(); i++) {	
			res.add(gson.fromJson(gson.toJson(sourceList.get(i)), destinationClass));
		}
		
		return res;
		
	}
	
	private void reformGson(FieldNamingPolicy... namingPolicy) {
		
		if (namingPolicy.length > 0) {
			gson = new GsonBuilder()
					.registerTypeAdapter(Date.class, new GsonDateAdapter())
					.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
				    .setFieldNamingPolicy(namingPolicy[0])
				    .create();
		}
		
	}
	
	private String reformJson(String json) {
		String res = json;
		
		for (FieldMapper fieldMapper : fields) {
			res = res.replace(fieldMapper.getFrom(), fieldMapper.getTo());
		}
		
		return res;
	}

}
