package org.muki;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.muki.pets.config.ObjectMappingConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectMappingConfig.class)
public class PetStoreApplicationTests {

    @Test
    public void contextLoads() {
    }

}
