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
public class PageResponse<T> {
    private List<T> contend;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;

    /**
     * Static factory method to create a PageResponse from a Spring Data Page
     * object. This method extracts the content, page number, page size, total
     * elements, and total pages from the Page
     * object and uses them to build a PageResponse instance. The page number is
     * incremented by 1 to convert from zero-based indexing used by Spring Data to
     * one-based indexing for the API response.
     * 
     * @param <T>  the type of the content in the page
     * @param page the Page object containing the paginated data and metadata to
     *             convert into a PageResponse
     * @return a PageResponse instance populated with data from the Page object
     */
    public static <T> PageResponse<T> from(Page<T> page) {
        return PageResponse.<T>builder()
                .contend(page.getContent())
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
