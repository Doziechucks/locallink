package org.dynamiteproject.locallink.service;

import org.dynamiteproject.locallink.LocalLinkApplication;
import org.dynamiteproject.locallink.config.TestSecurityConfig;
import org.dynamiteproject.locallink.data.model.Local;
import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRegistrationResponse;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class AuthenticationServiceImplTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private AuthenticationServiceImpl service;

    @Autowired
    private MongoTemplate mongoTemplate;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(Local.class);
    }
    @BeforeEach
    void clearDatabase() {
        mongoTemplate.dropCollection(Local.class);
    }

    @Test
    void createAccountIsSentToDatabase() {
        LocalRegistrationRequest registrationRequest = new LocalRegistrationRequest(
                "Dozie",
                "Money",
                "dozie@gmail.com",
                "Password123*",
                "address",
                "phoneNumber"
        );

        LocalRegistrationResponse response = service.registerLocal(registrationRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Dozie", response.getFirstName());

        Local saved = mongoTemplate.findById(response.getLocalId(), Local.class);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals("Dozie", saved.getFirstname());
    }
}
    // Provide necessary beans for the test context
