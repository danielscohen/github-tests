package qageekweek.examples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringConfigurationExample extends AbstractTestNGSpringContextTests {
    @Value("${url}")
    private String url;

    @Value("${browser}")
    private String browser;

    @Test
    public void testConfiguration(){
        System.out.println("URL    : " + url);
        System.out.println("BROWSER: " + browser);
    }
}


