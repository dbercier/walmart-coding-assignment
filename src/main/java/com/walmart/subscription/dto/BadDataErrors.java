package com.walmart.subscription.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
@NoArgsConstructor
@JsonDeserialize(builder = BadDataErrors.BadDataErrorsBuilder.class)
public class BadDataErrors {
    protected List<Map<String, String>> errors = new ArrayList<Map<String, String>>();

    @Builder(toBuilder = true)
    public BadDataErrors(
            List<Map<String, String>> errors) {
        this.errors = errors;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class BadDataErrorsBuilder {
    }
}
