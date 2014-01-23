package mixaniki;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditPatientTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		Main.loggedInUser="Gia";
		EditPatient	ep = new EditPatient();	
		assertNotNull(ep);
	}

}
