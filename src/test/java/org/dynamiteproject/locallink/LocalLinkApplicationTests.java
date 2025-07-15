package org.dynamiteproject.locallink;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = LocalLinkApplication.class)
class LocalLinkApplicationTests {

    @Test
    void contextLoads() {
    }

}
