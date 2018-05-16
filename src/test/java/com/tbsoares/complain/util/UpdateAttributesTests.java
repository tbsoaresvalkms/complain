package com.tbsoares.complain.util;

import com.tbsoares.complain.domain.Complain;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdateAttributesTests {
    @Test
    public void itShouldUpdateAttributesNotNull() {
        Complain complainTo = Complain.builder()
                .id("123")
                .title("Title")
                .build();

        Complain complainFrom = Complain.builder()
                .id(null)
                .title("New Title")
                .build();

        Complain complain = UpdateAttributes.updateOnlyNotNull(complainTo, complainFrom);

        assertThat(complain.getId()).isEqualTo("123");
        assertThat(complain.getTitle()).isEqualTo("New Title");
    }

    @Test
    public void itShouldUpdateAttributesNotNullAndPassed() {
        Complain complainTo = Complain.builder()
                .id("123")
                .title("Title")
                .build();

        Complain complainFrom = Complain.builder()
                .id("456")
                .title("New Title")
                .build();

        Complain complain = UpdateAttributes.updateOnlyNotNull(complainTo, complainFrom, "id");

        assertThat(complain.getId()).isEqualTo("123");
        assertThat(complain.getTitle()).isEqualTo("New Title");
    }

    @Test
    public void itShouldIgnoreNonexistentAttribute() {
        Complain complainTo = Complain.builder()
                .title("Title")
                .build();

        Complain complainFrom = Complain.builder()
                .title("New Title")
                .build();

        Complain complain = UpdateAttributes.updateOnlyNotNull(complainTo, complainFrom, "nonexistent");

        assertThat(complain.getTitle()).isEqualTo("New Title");
    }
}
