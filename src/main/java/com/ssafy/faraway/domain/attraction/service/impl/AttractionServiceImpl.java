package com.ssafy.faraway.domain.attraction.service.impl;

import com.ssafy.faraway.domain.attraction.entity.Sido;
import com.ssafy.faraway.domain.attraction.repository.SidoRepository;
import com.ssafy.faraway.domain.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final SidoRepository sidoRepository;

    @Override
    public List<Sido> findAllSido() {
        return sidoRepository.findAll();
    }
}
