package mk.ukim.finki.wp.eLibrary.model.dto;

import lombok.Data;

@Data
public class AuthorDto {

    private String name;

    private String residency;

    public AuthorDto() {
    }

    public AuthorDto(String name, String residency) {
        this.name = name;
        this.residency = residency;
    }

}
