package app;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class Test2 {
	
	@Mock
	private PersonaRepo pRepo;
	
	private Persona [] pList = new Persona[] {new Persona("Unai", "Perez"), new Persona("Iker", "Perez"), new Persona("Leire", "Goitia")};
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreation() {
		assertNotNull(pRepo);
	}
	
	@Test
	public void test1() {
		when(pRepo.count()).thenReturn(3L);
		assertEquals(3L, pRepo.count());
	}
	
	@Test
	public void test2() {
		given(pRepo.findByFirstName(any())).willAnswer((inv) -> {
			Stream<Persona> s = Arrays.asList(pList).stream();
			return s.filter(p -> p.getFirstName().equals(inv.getArgumentAt(0, String.class))).collect(Collectors.toList());
		});
		assertEquals(pRepo.findByFirstName("Unai").get(0), new Persona("Unai", "Perez"));
		assertNotEquals(pRepo.findByFirstName("Leire").get(0), new Persona("Leire", "Perez"));
	}
	
	@Test
	public void test3() {
		given(pRepo.findByLastName(any())).willAnswer((inv) -> {
			Stream<Persona> s = Arrays.asList(pList).stream();
			return s.filter(p -> p.getLastName().equals(inv.getArgumentAt(0, String.class))).collect(Collectors.toList());
		});
		assertEquals(pRepo.findByLastName("Perez").size(), 2);
		assertEquals(pRepo.findByLastName("Perez"), Arrays.asList(new Persona[] {new Persona("Unai", "Perez"), new Persona("Iker", "Perez")}));
	}
	
}
