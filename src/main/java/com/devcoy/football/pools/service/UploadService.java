package com.devcoy.football.pools.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	public Resource load(String name) throws MalformedURLException;

	public String save(MultipartFile file) throws IOException;

	public boolean delete(String name);

	public Path getPath(String name);
}
