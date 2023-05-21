package com.example.xtramile.common.helper;

import com.example.xtramile.dto.PageableRequest;
import com.example.xtramile.dto.PageableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

class PageableHelperTest {

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getRequest() {
        PageRequest result = PageableHelper.getRequest(new PageableRequest());
        Assertions.assertNotNull(result);
    }

    @Test
    void getRequest_withDesc() {
        PageableRequest pageableRequest = PageableRequest.builder()
                .sort(Sort.Direction.DESC)
                .build();

        PageRequest result = PageableHelper.getRequest(pageableRequest);

        Assertions.assertNotNull(result);
    }

    @Test
    void getRequest_withColumn() {
        PageableRequest pageableRequest = PageableRequest.builder()
                .column("createdDate")
                .sort(Sort.Direction.ASC)
                .build();

        PageRequest result = PageableHelper.getRequest(pageableRequest);

        Assertions.assertNotNull(result);
    }

    @Test
    void getResponse() {
        PageableResponse result = PageableHelper.getResponse(Page.empty());
        Assertions.assertNotNull(result);
    }
}
