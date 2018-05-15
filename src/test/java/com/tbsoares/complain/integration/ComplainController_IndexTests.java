package com.tbsoares.complain.integration;

import com.tbsoares.complain.dto.ComplainDTO;
import com.tbsoares.complain.models.RestPageImpl;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComplainController_IndexTests extends ComplainControllerTests {

    @Test
    public void itReturnsAEmptyListWhenHaveNoComplain() {
        ResponseEntity<RestPageImpl<ComplainDTO>> response = get(URL, new ParameterizedTypeReference<RestPageImpl<ComplainDTO>>() {
        });

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(response.getBody()).getContent())
                .asList()
                .isEmpty();
    }
}
