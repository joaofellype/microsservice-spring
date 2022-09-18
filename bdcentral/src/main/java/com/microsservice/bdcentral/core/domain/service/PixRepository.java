package com.microsservice.bdcentral.core.domain.service;

import com.microsservice.bdcentral.core.domain.aggregates.Pix;

import java.util.List;

public interface PixRepository {

    void save(Pix pix);

    Pix findById(String id);

    List<Pix> findAll();
}
