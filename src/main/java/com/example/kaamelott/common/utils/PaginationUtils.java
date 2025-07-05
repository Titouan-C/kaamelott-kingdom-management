package com.example.kaamelott.common.utils;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public class PaginationUtils {

  // Constants for pagination
  public static final String PAGE = "page";
  public static final String DEFAULT_PAGE_SIZE = "10";
  public static final String LIMIT = "limit";
  public static final String DEFAULT_CURSOR = "0";

  /**
   * Utility method to paginate data and map entities to DTOs.
   *
   * @param cursor      The current page index.
   * @param pageSize    The number of items per page.
   * @param fetchData   A function that retrieves paginated entities from the repository.
   * @param mapToDto    A function that converts entities to DTOs.
   * @param <E>         Entity type.
   * @param <D>         DTO type.
   * @return A paginated response containing the next cursor and the list of DTOs.
   */
  public static <E, D> OutPaginatedDataDto<List<D>> paginate(
    Integer cursor,
    int pageSize,
    Function<Pageable, Page<E>> fetchData,
    Function<E, D> mapToDto
  ) {
    Pageable pageable = PageRequest.of(cursor, pageSize);
    Page<E> pageResult = fetchData.apply(pageable);

    Integer nextCursor = pageResult.hasNext() ? cursor + 1 : null;
    List<D> dtos = pageResult
      .getContent()
      .stream()
      .map(mapToDto)
      .toList();

    return new OutPaginatedDataDto<>(nextCursor, dtos);
  }
}
