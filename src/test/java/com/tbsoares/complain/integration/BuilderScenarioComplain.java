package com.tbsoares.complain.integration;

import com.github.javafaker.Faker;
import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class BuilderScenarioComplain {

    private static Faker faker = new Faker();

    static ComplainDTO createAnyComplainDTO() {
        return ComplainDTO.builder()
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .locale(faker.address().cityName())
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithoutTitle() {
        return ComplainDTO.builder()
                .description(faker.lorem().paragraph())
                .locale(faker.address().cityName())
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithEmptyTitle() {
        return ComplainDTO.builder()
                .title("")
                .description(faker.lorem().paragraph())
                .locale(faker.address().cityName())
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithoutLocale() {
        return ComplainDTO.builder()
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithEmptyLocale() {
        return ComplainDTO.builder()
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .locale("")
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithoutDescription() {
        return ComplainDTO.builder()
                .title(faker.book().title())
                .locale(faker.address().cityName())
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithEmptyDescription() {
        return ComplainDTO.builder()
                .title(faker.book().title())
                .description("")
                .locale(faker.address().cityName())
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithId() {
        return ComplainDTO.builder()
                .id("123")
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .locale(faker.address().cityName())
                .company(faker.company().bs())
                .build();
    }

    static ComplainDTO createComplainDTOWithoutCompany() {
        return ComplainDTO.builder()
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .locale(faker.address().cityName())
                .build();
    }

    static ComplainDTO createComplainDTOWithEmptyCompany() {
        return ComplainDTO.builder()
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .locale(faker.address().cityName())
                .company("")
                .build();
    }

    static List<Complain> createManyComplain(Long count) {
        return LongStream
                .range(0, count)
                .mapToObj(BuilderScenarioComplain::createRandomComplain)
                .collect(Collectors.toList());
    }

    private static Complain createRandomComplain(Long aLong) {
        return Complain.builder()
                .title(faker.book().title())
                .description(faker.lorem().paragraph())
                .locale(faker.address().cityName())
                .company(faker.company().bs())
                .build();
    }
}
