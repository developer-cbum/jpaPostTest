package com.jpa.entity;

import com.jpa.domain.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileRepositoryTests {

    public void saveTest(){
        File file = new File();
        PostDTO postDTO = new PostDTO();



        file.setFileName("대충");
        file.setFilePath("넣어");
        file.setFileUuid("대충");
        file.setFileSize(10L);
    }
}
