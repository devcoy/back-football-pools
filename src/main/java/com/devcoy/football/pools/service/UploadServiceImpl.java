package com.devcoy.football.pools.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devcoy.football.pools.config.Env;

@Service
public class UploadServiceImpl implements UploadService, Env {

	@Override
	public Resource load(String name) throws MalformedURLException {

		Path path = getPath(name);
		Resource resource = new UrlResource(path.toUri());

		if (!resource.exists() && !resource.isReadable()) {
			throw new RuntimeException("Ha ocurrido un error, no se pudo cargar la archivo " + name);
		}

		return resource;
	}

	@Override
	public String save(MultipartFile file) throws IOException {

		String fileName = UUID.randomUUID().toString() + file.getOriginalFilename().replace(" ", "_");
		Path filePath = getPath(fileName);

		Files.copy(file.getInputStream(), filePath);

		return fileName;
	}

	@Override
	public boolean delete(String name) {

		if (name != null && name.length() > 0) {

			Path pathDB = Paths.get(DIR_UPLOADS).resolve(name).toAbsolutePath();
			File fileDb = pathDB.toFile();

			if (fileDb.exists() && fileDb.canRead()) {
				fileDb.delete();
				return true;
			}
		}

		return false;
	}

	@Override
	public Path getPath(String name) {

		return Paths.get(DIR_UPLOADS).resolve(name).toAbsolutePath();
	}

}
