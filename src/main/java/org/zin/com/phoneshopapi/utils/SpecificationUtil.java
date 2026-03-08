package org.zin.com.phoneshopapi.utils;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class SpecificationUtil {

    public static <T> Specification<T> buildSpecification(Map<String, String> filters){
        return ((root, query, cb) ->{


            Predicate predicate = cb.conjunction();
            for(Map.Entry<String, String> entry  : filters.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                if(value == null || value.isEmpty()) continue;

                Class<?> type = root.get(key).getJavaType();
                if(type.equals(String.class)){
                    predicate = cb.and(predicate, cb.like(cb.lower(root.get(key)), "%"+value.toLowerCase()+"%"));
                }else if(type.equals(Integer.class)){
                    predicate = cb.and(predicate, cb.equal(root.get(key), Integer.parseInt(value)));
                }else if(type.equals(Double.class)){
                    predicate = cb.and(predicate, cb.equal(root.get(key), Double.parseDouble(value)));
                }else if(type.equals(Long.class)){
                    predicate = cb.and(predicate, cb.equal(root.get(key), Long.parseLong(value)));
                }
            }
            return predicate;

        });
    }
}
