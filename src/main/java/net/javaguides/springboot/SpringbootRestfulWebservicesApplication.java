package net.javaguides.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.service.MyService;

@SpringBootApplication
@EnableCaching
public class SpringbootRestfulWebservicesApplication implements CommandLineRunner{

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	@Primary
	public RestTemplate RestTemplateCreator(){
		return new RestTemplate();
	}
	@Autowired
	private MyService myService;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		myService.exportComponentDetails();
		
	}

}
