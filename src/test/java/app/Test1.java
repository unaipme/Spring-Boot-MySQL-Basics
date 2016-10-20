package app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class Test1 {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void exampleTest() {
		Persona resp = restTemplate.getForEntity("/people/1", Persona.class).getBody();
		assertThat(resp).isEqualTo(new Persona("Unai", "Perez"));
	}
	
	@Test
	public void exampleTest2() {
		Grupo g = restTemplate.getForEntity("/people/2/group", Grupo.class).getBody();
		assertThat(g).isEqualTo(new Grupo("Grupo A"));
	}
	
	@Test
	public void exampleTest3() {
		HttpStatus s = restTemplate.getForEntity("/groups", Void.class).getStatusCode();
		assertThat(s).isNotEqualTo(HttpStatus.NOT_FOUND);
	}
	
}
