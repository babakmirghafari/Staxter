package com.staxter.user.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ActionResult {
    String code;
    String description;
}
