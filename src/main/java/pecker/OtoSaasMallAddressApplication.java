package pecker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("pecker.mybatis.mapper")
public class OtoSaasMallAddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtoSaasMallAddressApplication.class, args);
	}
}
