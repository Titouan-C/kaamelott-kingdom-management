package com.example.kaamelott.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutResponseDto<T> {

  private T data; // true for success, false for error
  private boolean ok; // Descriptive message (success or error)
  private String message; // Data (is null in cae of error)

  public OutResponseDto(boolean ok, String message) {
    this.ok = ok;
    this.message = message;
  }
}
