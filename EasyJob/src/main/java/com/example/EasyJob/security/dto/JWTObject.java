package com.example.EasyJob.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JWTObject implements Serializable {
  @JsonProperty("access_token")
  private   String accesstoken;

  @JsonProperty("refresh_token")
  private   String refreshtoken;
}
