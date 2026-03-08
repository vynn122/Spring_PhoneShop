package org.zin.com.phoneshopapi.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {



    public static Pageable buildPageable(int page, int size, String sort) {
        int pageNumber = (page > 0) ? page - 1 : 0;
        if(sort != null && sort.contains(",")){
            String[] parts = sort.split(",");
            return PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(parts[1]),parts[0]));
        }
        return PageRequest.of(pageNumber,size);

    }
}
