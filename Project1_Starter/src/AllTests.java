import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DumpTest.class, InsertTest.class, IntersectionTestTest.class, RegoinSearchTest.class,
		RemoveByValueTest.class, RemoveTest.class, SearchTest.class })
public class AllTests {

}
