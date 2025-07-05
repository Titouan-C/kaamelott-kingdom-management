package com.example.kaamelott.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutPaginatedDataDto<T> {

  private Integer nextCursor;
  private T content;
}
