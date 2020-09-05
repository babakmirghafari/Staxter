package com.staxter.user.exception;

import lombok.Builder;
import lombok.Data;

/**
 * This Model used as return Object.In this case I use this model as exception return model,
 * but with can use it as general return model for unifying return object.
 */
@Builder
@Data
public class ActionResult {
    String code;
    String description;
}
