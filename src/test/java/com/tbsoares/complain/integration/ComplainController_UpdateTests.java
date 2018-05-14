package com.tbsoares.complain.integration;

import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComplainController_UpdateTests extends ComplainControllerTests {

    @Test
    public void itReturnsStatusOKWhenParamsAreCorrect() {
        Complain complain = BuilderScenarioComplain.createAnyComplain();
        Complain saveComplain = repository.save(complain);
        ComplainDTO complainDTO = complainMapper.toDto(saveComplain);

        ResponseEntity<ComplainDTO> response = put(URL, complainDTO, ComplainDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void itUpdateComplainWhenParamsAreCorrect() {
        Complain complain = BuilderScenarioComplain.createAnyComplain();
        Complain saveComplain = repository.save(complain);
        String complainId = saveComplain.getId();

        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithOtherData();
        complainDTO.setId(complainId);
        ResponseEntity<ComplainDTO> response = put(URL, complainDTO, ComplainDTO.class);
        ComplainDTO returnComplainDTO = response.getBody();

        assertThat(repository.findById(complainId)
                .orElseThrow(RuntimeException::new))
                .isEqualToIgnoringNullFields(complainDTO)
                .isEqualToComparingFieldByField(returnComplainDTO)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    public void itReturnsComplainNotFoundWhenDoesNotExist() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithId();

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

        assertThat(response.getBody())
                .contains("Complain not found");
    }

    @Test
    public void itReturnsUnprocessableEntityWhenTitleIsNull() {
        ComplainDTO complainDTO = BuilderScenarioComplain.createComplainDTOWithoutTitle();

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

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

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

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

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

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

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

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

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

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

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

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

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

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

        ResponseEntity<String> response = put(URL, complainDTO, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

        assertThat(response.getBody())
                .contains("company: must not be empty");

        assertThat(repository.findAll())
                .asList()
                .isEmpty();
    }
}
