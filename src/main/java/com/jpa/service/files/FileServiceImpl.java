package com.jpa.service.files;

import com.jpa.domain.FileDTO;
import com.jpa.repository.files.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    @Override
    public void register(FileDTO fileDTO) {
        fileRepository.save(toEntity(fileDTO));
    }
}
