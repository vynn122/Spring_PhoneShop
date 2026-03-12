package org.zin.com.phoneshopapi.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {
    /**
     * Build a Pageable object based on the given page, size, and sort parameters.
     *
     * @param page the page number (starting from 1)
     * @param size the number of items per page
     * @param sort the sorting criteria in the format "field,direction" (e.g.,
     *             "name,asc")
     * @return a Pageable object for pagination and sorting
     */

    public static Pageable buildPageable(int page, int size, String sort) {

        int pageNumber = (page > 0) ? page - 1 : 0;

        if (sort != null && !sort.isBlank()) {

            String[] parts = sort.split(",");

            String field = parts[0];
            Sort.Direction direction = Sort.Direction.ASC;

            if (parts.length > 1) {
                direction = Sort.Direction.fromString(parts[1]);
            }

            return PageRequest.of(pageNumber, size, Sort.by(direction, field));
        }

        return PageRequest.of(pageNumber, size);
    }
}