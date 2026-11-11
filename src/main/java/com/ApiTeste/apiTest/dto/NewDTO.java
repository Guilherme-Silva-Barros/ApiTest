package com.ApiTeste.apiTest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewDTO(Long postId,
                     Long id,
                     String name,
                     String email,
                     String body) {
}
