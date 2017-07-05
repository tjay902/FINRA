package com.file.upload.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.file.upload.dto.FileData;
import com.file.upload.service.UploadService;

@SpringBootTest(classes = UploadController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
public class UploadControllerTests 
{

	@InjectMocks
	UploadController uploadController;

	@Mock
	private UploadService uploadService;

	@Test
	public void uploadFilePositiveTest() 
	{
		FileData metadata = new FileData();
		metadata.setFileName("filename.txt");
		metadata.setContentType("filename");
		metadata.setFirstname("jay");
		metadata.setLastname("thakkar");
		Map<String, FileData> uploadedObj = new ConcurrentHashMap<>();
		MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain",
				"some file with sample data".getBytes());
		uploadedObj.put("filename.txt", metadata);
		when(uploadService.prepareFileObjectAndUpload(firstFile, "jay", "thakkar")).thenReturn(uploadedObj);

		ResponseEntity<String> result = uploadController.uploadFile(firstFile, "jay", "thakkar");
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void uploadFileObjectNullTest() {
		FileData metadata = new FileData();
		metadata.setFileName("filename.txt");
		metadata.setContentType("filename");
		metadata.setFirstname("jay");
		metadata.setLastname("thakkar");
		Map<String, FileData> uploadedObj = new ConcurrentHashMap<>();
		MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain",
				"some file with sample data".getBytes());

		when(uploadService.prepareFileObjectAndUpload(firstFile, "jay", "thakkar")).thenReturn(uploadedObj);
		ResponseEntity<String> result = uploadController.uploadFile(firstFile, "jay", "thakkar");

		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	public void uploadFileEmptyDataTest() {
		FileData metadata = new FileData();
		metadata.setFileName("filename.txt");
		metadata.setContentType("filename");
		metadata.setFirstname("jay");
		metadata.setLastname("thakkar");
		Map<String, FileData> uploadedObj = null;
		MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain",
				"some file with sample data".getBytes());
		when(uploadService.prepareFileObjectAndUpload(firstFile, "jay", "thakkar")).thenReturn(uploadedObj);

		ResponseEntity<String> result = uploadController.uploadFile(firstFile, "jay", "thakkar");
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
}
