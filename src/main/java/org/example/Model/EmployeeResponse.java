package org.example.Model;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {

    private List<EmployeeModel> employee;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
