package mk.ukim.finki.wp.eLibrary.model.dto;

import lombok.Data;

@Data
public class GenreDto {

    private String name;

    private String description;

    public GenreDto() {
    }

    public GenreDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
