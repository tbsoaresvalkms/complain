package com.tbsoares.complain.integration;

import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComplainController_ShowTests extends ComplainControllerTests {

    @Test
    public void itReturnsComplainWhenItExists() {
        Complain complain = BuilderScenarioComplain.createAnyComplain();
        String complainId = repository.save(complain).getId();

        String fullUrl = builderUrl(complainId);
        ResponseEntity<ComplainDTO> response = get(fullUrl, ComplainDTO.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(repository.findById(complainId)
                .orElseThrow(RuntimeException::new))
                .isEqualToComparingFieldByField(response.getBody());
    }

    @Test
    public void itReturnsComplainNotFoundWhenDoesNotExist() {
        String complainId = "999";
        String fullUrl = builderUrl(complainId);
        ResponseEntity<String> response = get(fullUrl, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

        assertThat(response.getBody())
                .contains("Complain not found");
    }

    private String builderUrl(String complainId) {
        return URL + "/" + complainId;
    }
}
