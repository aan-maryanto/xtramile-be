package com.example.xtramile.common.helper;

import com.example.xtramile.dto.PageableRequest;
import com.example.xtramile.dto.PageableResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableHelper {
    public static PageableResponse getResponse(Page<?> page) {
        int startData = page.getSize() * page.getNumber();
        int endData = startData + page.getNumberOfElements();
        return PageableResponse.builder()
                .startData(endData == 0 ? 0 : startData + 1)
                .endData(endData)
                .totalData(page.getTotalElements())
                .build();
    }

    private static Sort getSort(Sort.Direction direction, String column) {

        if (StringUtils.isNotBlank(column)) {
            return Sort.by(direction, column);
        } else {
            if (Objects.equals(Sort.Direction.DESC, direction)) {
                return Sort.by(Sort.Direction.DESC, "id");
            } else {
                return Sort.by(Sort.Direction.ASC, "id");
            }
        }
    }

    public static PageRequest getRequest(PageableRequest pageableRequest) {
        Sort sort = getSort(pageableRequest.getSort(), pageableRequest.getColumn());
        return PageRequest.of(pageableRequest.getPage(), pageableRequest.getSize(), sort);
    }

//    private static String getData(String value) {
//        Map<String, String> columnMap = new HashMap<>();
//        columnMap.put("category", "type");
//        columnMap.put("start_date", "startDateTime");
//        columnMap.put("end_date", "endDateTime");
//        columnMap.put("name", "description");
//        columnMap.put("status", "status");
//        columnMap.put("payment_method", "prmPromoCriteria.prmCriteriaDetail.value");
//        return columnMap.get(value);
//    }
}
