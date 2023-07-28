package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class EmployeeSearchModel {
    private String surname;
    private boolean working;

    private int page ;

    private int elementPerPage ;

    private String direction = "dsc";

    private String key;

    public Sort buildSort() {
        switch (direction) {
            case "asc":
                return Sort.by(key).ascending();
            default:
                return Sort.by(key).descending();
        }
    }
}
