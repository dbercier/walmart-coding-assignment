package com.walmart.subscription.scenarios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.subscription.E2eSubscriptionApplication;
import com.walmart.subscription.SubscriptionApplication;
import com.walmart.subscription.dto.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

@ExtendWith(SpringExtension.class)
@Slf4j
@TestPropertySource(locations = {"classpath:application-e2e.properties"})
@SpringBootTest(classes = {E2eSubscriptionApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=6543"})
public abstract class BaseRestEndToEndTest {

    public static final String DEFAULT_SCHEME = "http";
    public static final String DEFAULT_HOST = "localhost";
    public static String DEFAULT_SPRING_BOOT_BASE_URI;
    @Value("${server.port}")
    int port;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        log.info("calling setup beforeEach");
    }

    @AfterEach
    public void tearDown() {
        log.info("calling tearDown afterEach");
    }

    @PostConstruct
    private void init() {
        DEFAULT_SPRING_BOOT_BASE_URI = StringUtils.join(DEFAULT_SCHEME, "://", DEFAULT_HOST, ":", port);
    }

}