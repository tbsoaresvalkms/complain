package com.tbsoares.complain.integration;

import com.tbsoares.complain.dto.ComplainDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComplainController_CreateTests extends ComplainControllerTests {

    @Test
    public void itReturnsStatusCreatedWhenParamsAreCorrect() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createAnyComplainDTO();

        ResponseEntity<ComplainDTO> response = post(URL, complainDTO, ComplainDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void itReturnComplainWithIdWhenComplainCreate() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createAnyComplainDTO();

        ResponseEntity<ComplainDTO> response = post(URL, complainDTO, ComplainDTO.class);

        assertThat(response.getBody())
                .isEqualToIgnoringNullFields(complainDTO)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    public void itCreateComplainWhenParamsAreCorrect() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createAnyComplainDTO();

        ResponseEntity<ComplainDTO> response = post(URL, complainDTO, ComplainDTO.class);

        ComplainDTO returnComplainDTO = response.getBody();
        String complainId = Objects.requireNonNull(returnComplainDTO).getId();

        assertThat(repository.findById(complainId)
                .orElseThrow(RuntimeException::new))
                .isEqualToIgnoringNullFields(complainDTO)
                .isEqualToComparingFieldByField(returnComplainDTO)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenTitleIsNull() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithoutTitle();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("title: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenTitleIsEmpty() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithEmptyTitle();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("title: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenLocaleIsNull() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithoutLocale();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("locale: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenLocaleIsEmpty() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithEmptyLocale();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("locale: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenDescriptionIsNull() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithoutDescription();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("description: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenDescriptionIsEmpty() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithEmptyDescription();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("description: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenCompanyIsNull() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithoutCompany();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("company: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenCompanyIsEmpty() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithEmptyCompany();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("company: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }

    @Test
    public void itReturnsUnprocessableEntityWhenExistId() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithId();

        ResponseEntity<String> response = post(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("The object cannot already have a id");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }
}
