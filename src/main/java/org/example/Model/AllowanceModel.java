package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllowanceModel {

    private Long id;
    private Long sum;
    private LocalDate month;
    private Long numberOfOrder;
    private LocalDate dateOfOrder;
    private Long employeeId;

}
