package com.walmart.subscription.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
@NoArgsConstructor
@JsonDeserialize(builder = Subscription.SubscriptionBuilder.class)
public class Subscription {
    @NotBlank
    protected String name;
    @Email
    protected String email;
    @NotBlank
    @JsonProperty("user-type")
    protected String userType;

    @NotBlank
    protected String company;

    @NotBlank
    @JsonProperty("application-type")
    protected String applicationType;

    @Builder(toBuilder = true)
    public Subscription(
            String name,
            String email,
            String userType,
            String company,
            String applicationType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.company = company;
        this.applicationType = applicationType;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class SubscriptionBuilder {

        protected String userType;

        protected String applicationType;

        @JsonProperty("user-type")
        public Subscription.SubscriptionBuilder userType(final String value) {
            this.userType = value;
            return this;
        }

        @JsonProperty("application-type")
        public Subscription.SubscriptionBuilder applicationType(final String value) {
            this.applicationType = value;
            return this;
        }
    }

}
