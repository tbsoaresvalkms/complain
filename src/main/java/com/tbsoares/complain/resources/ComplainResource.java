package com.tbsoares.complain.resources;

import com.tbsoares.complain.dto.ComplainDTO;
import com.tbsoares.complain.service.DefaultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/complain", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplainResource {

    private final DefaultService<ComplainDTO> complainService;

    public ComplainResource(DefaultService<ComplainDTO> complainService) {
        this.complainService = complainService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ComplainDTO> index() {
        return complainService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComplainDTO show(@PathVariable("id") String id) {
        return complainService.findOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ComplainDTO create(@Valid @RequestBody ComplainDTO complainDTO) {
        return complainService.save(complainDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ComplainDTO update(@Valid @RequestBody ComplainDTO complainDTO) {
        return complainService.update(complainDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        complainService.delete(id);
    }
}
