package org.dynamiteproject.locallink.service;

import lombok.extern.slf4j.Slf4j;
import org.dynamiteproject.locallink.LocalLinkApplication;
import org.dynamiteproject.locallink.config.TestSecurityConfig;
import org.dynamiteproject.locallink.data.model.Local;
import org.dynamiteproject.locallink.dto.Request.LocalRecordRequest;
import org.dynamiteproject.locallink.dto.Request.LocalRegistrationRequest;
import org.dynamiteproject.locallink.dto.Response.LocalRecordResponse;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")

public class AuthenticationServiceImplTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private AuthenticationServiceImpl authService;

    @Autowired
    private UserServiceImpl userService;

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
                "phoneNumber",
                "address"

        );

        LocalRegistrationResponse response = authService.registerLocal(registrationRequest);

        // Only assert the fields you expect in the response
        Assertions.assertNotNull(response);
        log.info("Response: {}", response);
        Assertions.assertEquals("Dozie", response.getFirstname());
        Assertions.assertNotNull(response.getLocalId());

        // Verify the complete data was saved to database
        Local saved = mongoTemplate.findById(response.getLocalId(), Local.class);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals("Dozie", saved.getFirstname());
        Assertions.assertEquals("Money", saved.getLastname());
        Assertions.assertEquals("dozie@gmail.com", saved.getEmail());
        // Add other field assertions as needed
    }

    @Test
    void TestCanCreateRecord(){
        LocalRecordRequest recordRequest = new LocalRecordRequest(
                "More money",
                "I need 1billion dollars",
                "oka101",
                "Dozie!23",
                List.of("manowar"),  // Correct way to create a List with one element
                new BigDecimal("105000000")  // Note: BigDecimal should be created from a String for precise values
        );

        LocalRecordResponse response = userService.createRecord(recordRequest);
        Assertions.assertNotNull(response);
        log.info("Response: {}", response);
        Assertions.assertEquals("More money", response.getTitle());
    }
}
    // Provide necessary beans for the test context
