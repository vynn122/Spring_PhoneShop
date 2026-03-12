//package org.zin.com.phoneshopapi.utils;
//
//import jakarta.persistence.criteria.Predicate;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.Map;
//
//public class SpecificationUtil {
//
//    public static <T> Specification<T> buildSpecification(Map<String, String> filters){
//        return ((root, query, cb) ->{
//
//
//            Predicate predicate = cb.conjunction();
//            for(Map.Entry<String, String> entry  : filters.entrySet()){
//                String key = entry.getKey();
//                String value = entry.getValue();
//                if(value == null || value.isEmpty()) continue;
//
//                Class<?> type = root.get(key).getJavaType();
//                if(type.equals(String.class)){
//                    predicate = cb.and(predicate, cb.like(cb.lower(root.get(key)), "%"+value.toLowerCase()+"%"));
//                }else if(type.equals(Integer.class)){
//                    predicate = cb.and(predicate, cb.equal(root.get(key), Integer.parseInt(value)));
//                }else if(type.equals(Double.class)){
//                    predicate = cb.and(predicate, cb.equal(root.get(key), Double.parseDouble(value)));
//                }else if(type.equals(Long.class)){
//                    predicate = cb.and(predicate, cb.equal(root.get(key), Long.parseLong(value)));
//                }
//            }
//            return predicate;
//
//        });
//    }
//}
package org.zin.com.phoneshopapi.utils;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class SpecificationUtil {
    /**
     * Build a JPA Specification based on the provided filters.
     *
     * The filters map should contain field names as keys and filter values as
     * values.
     * Supported filter formats:
     * - Exact match: field=value
     * - Like match: field~value (case-insensitive)
     * - Range: field=min..max
     * - Greater than: field>value or field>=value
     * - Less than: field<value or field<=value
     *
     * @param filters a map of filter criteria
     * @param <T>     the type of the entity
     * @return a Specification that can be used with Spring Data JPA repositories
     */

    public static <T> Specification<T> buildSpecification(Map<String, String> filters) {
        return (root, query, cb) -> {

            Predicate predicate = cb.conjunction();

            for (Map.Entry<String, String> entry : filters.entrySet()) {

                String key = entry.getKey();
                String value = entry.getValue();

                if (value == null || value.isEmpty())
                    continue;

                Path<?> path;

                // support join like brand.name
                if (key.contains(".")) {
                    String[] parts = key.split("\\.");
                    path = root.join(parts[0]).get(parts[1]);
                } else {
                    path = root.get(key);
                }

                Class<?> type = path.getJavaType();

                // LIKE search name~iphone
                if (value.startsWith("~")) {
                    String search = value.substring(1).toLowerCase();
                    predicate = cb.and(predicate,
                            cb.like(cb.lower(path.as(String.class)), "%" + search + "%"));
                }

                // BETWEEN price=500..1000
                else if (value.contains("..")) {
                    String[] range = value.split("\\.\\.");
                    Double min = Double.parseDouble(range[0]);
                    Double max = Double.parseDouble(range[1]);

                    predicate = cb.and(predicate,
                            cb.between(path.as(Double.class), min, max));
                }

                // GREATER THAN
                else if (value.startsWith(">=")) {
                    Double num = Double.parseDouble(value.substring(2));
                    predicate = cb.and(predicate,
                            cb.greaterThanOrEqualTo(path.as(Double.class), num));
                }

                else if (value.startsWith(">")) {
                    Double num = Double.parseDouble(value.substring(1));
                    predicate = cb.and(predicate,
                            cb.greaterThan(path.as(Double.class), num));
                }

                // LESS THAN
                else if (value.startsWith("<=")) {
                    Double num = Double.parseDouble(value.substring(2));
                    predicate = cb.and(predicate,
                            cb.lessThanOrEqualTo(path.as(Double.class), num));
                }

                else if (value.startsWith("<")) {
                    Double num = Double.parseDouble(value.substring(1));
                    predicate = cb.and(predicate,
                            cb.lessThan(path.as(Double.class), num));
                }

                // EQUAL
                else {
                    if (type.equals(String.class)) {
                        predicate = cb.and(predicate,
                                cb.equal(path, value));
                    } else if (type.equals(Integer.class)) {
                        predicate = cb.and(predicate,
                                cb.equal(path, Integer.parseInt(value)));
                    } else if (type.equals(Double.class)) {
                        predicate = cb.and(predicate,
                                cb.equal(path, Double.parseDouble(value)));
                    } else if (type.equals(Long.class)) {
                        predicate = cb.and(predicate,
                                cb.equal(path, Long.parseLong(value)));
                    }
                }
            }

            return predicate;
        };
    }
}
