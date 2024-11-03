package com.api.restful.service;

import com.api.restful.dto.WriterRepresentation;
import com.api.restful.mapper.WriterMapper;
import com.api.restful.model.Writer;
import com.api.restful.repository.WriterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public WriterRepresentation getWriter(int writerId) {
        log.info("Get writer[id = {}]", writerId);
        Writer writer = writerRepository.findById(writerId).orElseThrow(
                () -> new RuntimeException(String.format("Writer[id = %d] not found.", writerId))
        );
        return WriterMapper.mapToRepresentation(writer);
    }

    @Override
    public List<WriterRepresentation> getAll() {
        log.info("Get all writers...");
        return WriterMapper.mapToRepresentationList(writerRepository.findAll());
    }

}
