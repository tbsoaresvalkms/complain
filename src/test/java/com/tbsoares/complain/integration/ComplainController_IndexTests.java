package com.tbsoares.complain.integration;

import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import com.tbsoares.complain.models.RestPageImpl;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

    @Test
    public void itReturnsFilterComplainByLocale() {
        Long countComplains = 4L;
        Long countComplainsSameLocale = 2L;
        Consumer<Complain> setLocaleName = c -> c.setLocale("Sao Paulo");

        List<Complain> manyComplain = BuilderScenarioComplain.createManyComplainWithAnyAction(countComplains, countComplainsSameLocale, setLocaleName);
        repository.saveAll(manyComplain);


        String fullUrl = builderUrl("locale=Sao Paulo");
        ResponseEntity<RestPageImpl<ComplainDTO>> response = get(fullUrl, new ParameterizedTypeReference<RestPageImpl<ComplainDTO>>() {
        });


        List<ComplainDTO> expectedComplain = repository.findAll()
                .stream()
                .filter(c -> c.getLocale().equals("Sao Paulo"))
                .map(complainMapper::toDto)
                .collect(Collectors.toList());

        assertThat(Objects.requireNonNull(response.getBody()).getContent())
                .asList()
                .hasSize(Math.toIntExact(countComplainsSameLocale))
                .hasSameElementsAs(expectedComplain);
    }

    @Test
    public void itReturnsFilterComplainByCompany() {
        Long countComplains = 4L;
        Long countComplainsSameCompany = 2L;
        Consumer<Complain> setCompanyName = c -> c.setCompany("Government");

        List<Complain> manyComplain = BuilderScenarioComplain.createManyComplainWithAnyAction(countComplains, countComplainsSameCompany, setCompanyName);
        repository.saveAll(manyComplain);


        String fullUrl = builderUrl("company=Government");
        ResponseEntity<RestPageImpl<ComplainDTO>> response = get(fullUrl, new ParameterizedTypeReference<RestPageImpl<ComplainDTO>>() {
        });


        List<ComplainDTO> expectedComplain = repository.findAll()
                .stream()
                .filter(c -> c.getCompany().equals("Government"))
                .map(complainMapper::toDto)
                .collect(Collectors.toList());

        assertThat(Objects.requireNonNull(response.getBody()).getContent())
                .asList()
                .hasSize(Math.toIntExact(countComplainsSameCompany))
                .hasSameElementsAs(expectedComplain);
    }

    @Test
    public void itReturnsFilterComplainByCompanyAndLocale() {
        Long countComplains = 4L;
        Long countComplainsSameCompany = 2L;
        Consumer<Complain> setCompanyName = c -> {
            c.setCompany("Bank");
            c.setLocale("Rio de Janeiro");
        };

        List<Complain> manyComplain = BuilderScenarioComplain.createManyComplainWithAnyAction(countComplains, countComplainsSameCompany, setCompanyName);
        // It verifies only the and operator works
        manyComplain.get(2).setCompany("Bank");
        manyComplain.get(3).setLocale("Rio de Janeiro");
        repository.saveAll(manyComplain);


        String fullUrl = builderUrl("locale=Rio de Janeiro&company=Bank");
        ResponseEntity<RestPageImpl<ComplainDTO>> response = get(fullUrl, new ParameterizedTypeReference<RestPageImpl<ComplainDTO>>() {
        });


        List<ComplainDTO> expectedComplain = repository.findAll()
                .stream()
                .filter(c -> c.getCompany().equals("Bank"))
                .filter(c -> c.getLocale().equals("Rio de Janeiro"))
                .map(complainMapper::toDto)
                .collect(Collectors.toList());

        assertThat(Objects.requireNonNull(response.getBody()).getContent())
                .asList()
                .hasSize(Math.toIntExact(countComplainsSameCompany))
                .hasSameElementsAs(expectedComplain);
    }

    @Test
    public void itReturnsFilterComplainByTwoCompanyAndLocale() {
        Long countComplains = 4L;

        List<Complain> manyComplain = BuilderScenarioComplain.createManyComplain(countComplains);
        manyComplain.get(0).setCompany("BankONE");
        manyComplain.get(0).setLocale("Sao Paulo");

        manyComplain.get(1).setCompany("BankTWO");
        manyComplain.get(1).setLocale("Sao Paulo");

        manyComplain.get(2).setCompany("BankONE");
        manyComplain.get(2).setLocale("Rio de Janeiro");

        manyComplain.get(3).setCompany("BankThree");
        manyComplain.get(3).setLocale("Sao Paulo");

        repository.saveAll(manyComplain);


        String fullUrl = builderUrl("locale=Sao Paulo&company=BankONE,BankTWO");
        ResponseEntity<RestPageImpl<ComplainDTO>> response = get(fullUrl, new ParameterizedTypeReference<RestPageImpl<ComplainDTO>>() {
        });


        List<ComplainDTO> expectedComplain = repository.findAll()
                .stream()
                .filter(c -> c.getCompany().equals("BankONE") || c.getCompany().equals("BankTWO"))
                .filter(c -> c.getLocale().equals("Sao Paulo"))
                .map(complainMapper::toDto)
                .collect(Collectors.toList());

        assertThat(Objects.requireNonNull(response.getBody()).getContent())
                .asList()
                .hasSize(2)
                .hasSameElementsAs(expectedComplain);
    }

    @Test
    public void itReturnsFilterComplainByCompanyAndTwoLocale() {
        Long countComplains = 4L;

        List<Complain> manyComplain = BuilderScenarioComplain.createManyComplain(countComplains);
        manyComplain.get(0).setCompany("BankONE");
        manyComplain.get(0).setLocale("Sao Paulo");

        manyComplain.get(1).setCompany("BankTWO");
        manyComplain.get(1).setLocale("Sao Paulo");

        manyComplain.get(2).setCompany("BankONE");
        manyComplain.get(2).setLocale("Rio de Janeiro");

        manyComplain.get(3).setCompany("BankThree");
        manyComplain.get(3).setLocale("Sao Paulo");

        repository.saveAll(manyComplain);


        String fullUrl = builderUrl("locale=Sao Paulo,Rio de Janeiro&company=BankONE");
        ResponseEntity<RestPageImpl<ComplainDTO>> response = get(fullUrl, new ParameterizedTypeReference<RestPageImpl<ComplainDTO>>() {
        });


        List<ComplainDTO> expectedComplain = repository.findAll()
                .stream()
                .filter(c -> c.getCompany().equals("BankONE"))
                .filter(c -> c.getLocale().equals("Sao Paulo") || c.getLocale().equals("Rio de Janeiro"))
                .map(complainMapper::toDto)
                .collect(Collectors.toList());

        assertThat(Objects.requireNonNull(response.getBody()).getContent())
                .asList()
                .hasSize(2)
                .hasSameElementsAs(expectedComplain);
    }

    @Test
    public void itReturnsFilterComplainByTwoCompanyAndTwoLocale() {
        Long countComplains = 4L;

        List<Complain> manyComplain = BuilderScenarioComplain.createManyComplain(countComplains);
        manyComplain.get(0).setCompany("BankONE");
        manyComplain.get(0).setLocale("Sao Paulo");

        manyComplain.get(1).setCompany("BankTWO");
        manyComplain.get(1).setLocale("Sao Paulo");

        manyComplain.get(2).setCompany("BankONE");
        manyComplain.get(2).setLocale("Rio de Janeiro");

        manyComplain.get(3).setCompany("BankThree");
        manyComplain.get(3).setLocale("Sao Paulo");

        repository.saveAll(manyComplain);


        String fullUrl = builderUrl("locale=Sao Paulo,Rio de Janeiro&company=BankONE,BankTWO");
        ResponseEntity<RestPageImpl<ComplainDTO>> response = get(fullUrl, new ParameterizedTypeReference<RestPageImpl<ComplainDTO>>() {
        });


        List<ComplainDTO> expectedComplain = repository.findAll()
                .stream()
                .filter(c -> c.getCompany().equals("BankONE") || c.getCompany().equals("BankTWO"))
                .filter(c -> c.getLocale().equals("Sao Paulo") || c.getLocale().equals("Rio de Janeiro"))
                .map(complainMapper::toDto)
                .collect(Collectors.toList());

        assertThat(Objects.requireNonNull(response.getBody()).getContent())
                .asList()
                .hasSize(3)
                .hasSameElementsAs(expectedComplain);
    }

    private String builderUrl(String query) {
        return URL + "?" + query;
    }
}
