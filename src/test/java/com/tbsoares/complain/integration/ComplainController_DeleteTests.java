package com.tbsoares.complain.integration;

import com.tbsoares.complain.domain.Complain;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComplainController_DeleteTests extends ComplainControllerTests {

    @Test
    public void itDeleteComplainWhenItExists() {
        Complain complain = BuilderScenarioComplain.createAnyComplain();
        String complainId = repository.save(complain).getId();

        String fullUrl = builderUrl(complainId);
        ResponseEntity<String> response = delete(fullUrl, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    private String builderUrl(String complainId) {
        return URL + "/" + complainId;
    }
}
