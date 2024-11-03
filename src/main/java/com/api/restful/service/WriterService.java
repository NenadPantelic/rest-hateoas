package com.api.restful.service;

import com.api.restful.dto.WriterRepresentation;

import java.util.List;

public interface WriterService {

    WriterRepresentation getWriter(int writerId);

    List<WriterRepresentation> getAll();

}
