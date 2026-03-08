package org.zin.com.phoneshopapi.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse <T>{
    private List<T> contend;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;


    public static <T> PageResponse<T> from(Page<T> page){
        return PageResponse.<T>builder()
                .contend(page.getContent())
                .page(page.getNumber() +1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
