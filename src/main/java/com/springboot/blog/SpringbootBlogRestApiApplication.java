package com.springboot.blog;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App REST APIs",
				description = "Spring Boot Blog App REST APIs documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Izabela",
						email = "gramovska.izabela@gmail.com",
						url = "Here provide the website for the current application  -> https://www.javaguides.net"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.javaguides.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog App Documentation",
				url = "Here you can provide the github repository link of the current REST APIs"
)
)

// Make the class to implements CommandLineRunner
public class SpringbootBlogRestApiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Bean // @Bean will configure this instance as a Spring bean/component
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//@Override
	//public void run(String... args) throws Exception {
		//Role adminRole = new Role();
		//adminRole.setName("ROLE_ADMIN");
		//roleRepository.save(adminRole);

		//Role userRole = new Role();
		//userRole.setName("ROLE_USER");
		//roleRepository.save(userRole);

		//Set<Role> roles = new HashSet<>();
		//roles.add(adminRole);

		//User user = new User();
		//user.setName("admin");
		//user.setUsername("admin");
		//user.setEmail("admin@gmail.com");
		//user.setPassword(passwordEncoder().encode("admin"));
		//user.setRoles(roles);

		//userRepository.save(user);
	//}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}
}

