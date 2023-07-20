package org.example.services;

import org.example.Repository.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    @Autowired
    public PositionRepo positionRepo;

}
