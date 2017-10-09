package org.lgangloff.nbd.service;

import java.io.IOException;
import java.util.UUID;

import org.lgangloff.nbd.domain.File;
import org.lgangloff.nbd.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	@Autowired
	private FileRepository fileRepository;
	
	public File store(MultipartFile multipartFile) throws IOException {

		File file = new File();
		
		file.setUuid(UUID.randomUUID().toString());
		file.setContentType(multipartFile.getContentType());
		file.setName(multipartFile.getOriginalFilename());
		file.setSize(multipartFile.getSize());
		file.setDatas(multipartFile.getBytes());
		
		return fileRepository.saveAndFlush(file);
	}
	
	public File findOneForDownload(String uuid) {
		return fileRepository.findOneByUuidWithDatas(uuid);
	}

	public Page<File> findAll(String query, Pageable pageable) {
		return fileRepository.findAll(query, pageable);
	}

	public File findOne(Long id) {
		return fileRepository.findById(id).orElse(null);
	}

	public void deleteFile(Long id) {
		fileRepository.deleteById(id);
	}

}
