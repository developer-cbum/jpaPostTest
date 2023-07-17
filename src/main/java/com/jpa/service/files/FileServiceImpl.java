package com.jpa.service.files;

import com.jpa.domain.FileDTO;
import com.jpa.entity.File;
import com.jpa.repository.files.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    @Override
    public void register(FileDTO fileDTO) {
        fileRepository.save(toEntity(fileDTO));
    }

    @Override
    public Optional<File> findById(Long id) {
        return fileRepository.findById(id);
    }
}
