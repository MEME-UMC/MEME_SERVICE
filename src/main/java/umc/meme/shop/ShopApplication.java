package umc.meme.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;


@SpringBootApplication
@EnableJpaAuditing
public class ShopApplication {

	public static void main(String[] args) throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		// Resolve the resource for the current file
		Resource resource = resolver.getResource("classpath:");

		// Get the absolute path of the current file
		String absolutePath = resource.getFile().getAbsolutePath();

		// Print the absolute path
		System.out.println("Absolute path to the current file: " + absolutePath);

		SpringApplication.run(ShopApplication.class, args);
	}

}
