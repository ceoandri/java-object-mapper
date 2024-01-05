package gratis.contoh.model;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Test1ModelA {
	String field1;
	String field2;
	String field3;
	Date field4;
	LocalDateTime field5;
}
