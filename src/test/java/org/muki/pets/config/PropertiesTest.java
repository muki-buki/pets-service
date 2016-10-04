package org.muki.pets.config;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertiesConfig.class)
public class PropertiesTest {

    @Value("${value.injection.test}")
    private String injectedValue;

    @Test
    public void testValueInjectedWithCorrectValue() throws Exception {
        Assert.assertEquals("ok", injectedValue);
    }

}
