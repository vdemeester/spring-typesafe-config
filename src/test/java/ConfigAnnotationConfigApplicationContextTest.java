import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.github.vdemeester.springtypesafeconfig.ConfigAnnotationConfigApplicationContext;

/**
 * Created by vincent on 10/06/14.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ConfigAnnotationConfigApplicationContextTest {

	@Test
	public void testIt() {
		ApplicationContext context = new ConfigAnnotationConfigApplicationContext(AppConfig.class);
		TestService testService = context.getBean(TestService.class);
		Assert.assertEquals("test", testService.getTestString());
		Assert.assertEquals(new Integer(1), testService.getTestInteger());
	}

	@Configuration
	public static class AppConfig {

		@Autowired
		private Environment env;

		@Bean
		public TestService testService() {
			String testString = env.getProperty("test");
			Integer testInteger = env.getProperty("nested.test", Integer.class);

			return new TestService(testString, testInteger);
		}
	}

	private static class TestService {
		private final String testString;
		private final Integer testInteger;

		public TestService(String testString, Integer testInteger) {
			this.testString = testString;
			this.testInteger = testInteger;
		}

		public String getTestString() {
			return testString;
		}

		public Integer getTestInteger() {
			return testInteger;
		}
	}
}
