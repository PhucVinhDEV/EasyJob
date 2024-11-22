package com.example.EasyJob.common.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageReponsese<T> {
    private List<T> data;
    long totalElements;
    int totalPages;
    int size;
    int number;
    boolean first;
    boolean last;
    int numberOfElements;
    boolean empty;

    public <U> PageReponsese<U> map(Function<? super T, ? extends U> converter) {
        // Chuyển đổi content từ List<T> sang List<U>
        List<U> convertedContent = data.stream()
                .map(converter)
                .collect(Collectors.toList());

        // Trả về một đối tượng PageReponsese<U> mới với đúng thứ tự các tham số
        return PageReponsese.<U>builder()
                .data(convertedContent)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .size(size)
                .number(number)
                .first(first)
                .last(last)
                .numberOfElements(numberOfElements)
                .empty(empty)
                .build();
    }

}
