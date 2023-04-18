package com.walmart.subscription.scenarios;

import static org.assertj.core.api.Assertions.assertThat;
import com.walmart.subscription.common.SubscriptionConstants;
import com.walmart.subscription.dto.BadDataErrors;
import com.walmart.subscription.dto.Subscription;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static io.restassured.RestAssured.given;

public class SubscriptionEndpointScenariosE2E extends BaseRestEndToEndTest {

    @Test
    public void shouldReturnExpectedSubscription() throws Exception {

        Subscription subscription = Subscription.builder()
                .name("clarkKent")
                .userType("superUser")
                .email("clarkKent@dailyplanet.net")
                .company("Daily Planet Inc.")
                .applicationType("news").build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(subscription);

        String responseAsJson = given()
                .contentType(ContentType.JSON)
                .when()
                .request()
                .body(requestBodyAsJSON)
                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
                        SubscriptionConstants.SUBSCRIPTIONS_ENDPOINT_V1))
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .log()
                .all()
                .extract()
                .response()
                .body()
                .asString();

        assertThat(responseAsJson).isEqualTo(requestBodyAsJSON);
    }

    @Test
    public void shouldReturnExpectedValidationErrors() throws Exception {

        final String expectedErrorResponse = "{\"errors\":[{\"userType\":\"must not be blank\"},{\"applicationType\":\"must not be blank\"},{\"email\":\"must be a well-formed email address\"},{\"company\":\"must not be blank\"},{\"name\":\"must not be blank\"}]}";

        final BadDataErrors expectedBadDataErrors = objectMapper.readValue(expectedErrorResponse, BadDataErrors.class);

        Subscription subscription = Subscription.builder()
                .name("")
                .userType("")
                .email("clarkKent#dailyplanet.net")
                .company("")
                .applicationType("").build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(subscription);

        BadDataErrors actualResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .request()
                .body(requestBodyAsJSON)
                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
                        SubscriptionConstants.SUBSCRIPTIONS_ENDPOINT_V1))
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log()
                .all()
                .extract()
                .response()
                .body()
                .as(BadDataErrors.class);

        assertThat(actualResponse.getErrors()).containsExactlyInAnyOrderElementsOf(expectedBadDataErrors.getErrors());


    }

}
