package com.ssafy.faraway.domain.attraction.service;

import com.ssafy.faraway.domain.attraction.entity.Sido;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AttractionService {
    List<Sido> findAllSido();

}
