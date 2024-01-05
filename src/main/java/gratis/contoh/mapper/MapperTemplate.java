package gratis.contoh.mapper;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MapperTemplate<T, Z> implements ObjectMapper<T, Z> {
	
	private Class<T> originClass;
	private Class<Z> destinationClass;
	private List<FieldMapper> fields;
	
	private Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, new GsonDateAdapter())
			.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
		    .create();
	
	public MapperTemplate(Class<T> originClass, Class<Z> destinationClass, FieldMapper... fieldMapper) {
		setOriginClass(originClass);
		setDestinationClass(destinationClass);
		constructFieldMapper(fieldMapper);
	}
	
	public Class<T> getOriginClass() {
		return this.originClass;
	}
	
	public Class<Z> getDestinationClass() {
		return this.destinationClass;
	}
	
	public List<FieldMapper> getFields() {
		return this.fields;
	}
	
	public void setOriginClass(Class<T> originClass) {
		this.originClass = originClass;
	}
	
	public void setDestinationClass(Class<Z> destinationClass) {
		this.destinationClass = destinationClass;
	}
	
	public void setFields(List<FieldMapper> fields) {
		this.fields = fields;
	}
	
	private void constructFieldMapper(FieldMapper... fieldMapper) {
		List<FieldMapper> fieldsConstructed = new ArrayList<>();
		
		for (int i = 0; i < fieldMapper.length; i++) {
			fieldsConstructed.add(fieldMapper[i]);
		}
		
		setFields(fieldsConstructed);
	}
	
	public Z convert(Object source, FieldNamingPolicy... namingPolicy) {
		reformGson(namingPolicy);
		
		String json = gson.toJson(source);
		json = reformJson(json);
		Z res = gson.fromJson(json, destinationClass);
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Z> convertList(Object source, FieldNamingPolicy... namingPolicy) {
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
