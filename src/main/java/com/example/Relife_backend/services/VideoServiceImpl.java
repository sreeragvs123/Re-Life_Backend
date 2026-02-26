package com.example.Relife_backend.services;


import com.example.Relife_backend.dto.VideoDTO;
import com.example.Relife_backend.entities.Video;
import com.example.Relife_backend.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    @Value("${app.video-upload-dir:uploads/videos}")
    private String uploadDir;

    @Value("${app.base-url:http://192.168.0.135:8080}")
    private String baseUrl;

    @Override
    public VideoDTO uploadVideo(String title, String uploader, MultipartFile file) {

        // FIX: Convert to absolute path first.
        // On Windows, file.transferTo() resolves relative paths against the
        // Tomcat temp directory, not the project root — causing FileNotFoundException.
        // toAbsolutePath() pins the path to the actual working directory so both
        // createDirectories() and Files.copy() operate on the same location.
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory: " + e.getMessage());
        }

        String originalName = file.getOriginalFilename() != null
                ? file.getOriginalFilename()
                : "video.mp4";
        String fileName = System.currentTimeMillis() + "_" + originalName.replaceAll("\\s+", "_");
        Path filePath = uploadPath.resolve(fileName);

        // FIX: Use Files.copy(InputStream) instead of file.transferTo(File).
        // transferTo() on Windows resolves the path internally again and can
        // hit the Tomcat temp dir. Files.copy from the stream writes directly
        // to the absolute path we resolved above.
        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save video file: " + e.getMessage());
        }

        Video video = new Video();
        video.setTitle(title);
        video.setUploader(uploader);
        video.setFileName(fileName);
        video.setStatus("pending");

        Video saved = videoRepository.save(video);
        return toDTO(saved);
    }

    @Override
    public List<VideoDTO> getAllVideos() {
        return videoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoDTO> getApprovedVideos() {
        return videoRepository.findByStatus("approved")
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDTO approveVideo(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found: " + id));
        video.setStatus("approved");
        return toDTO(videoRepository.save(video));
    }

    @Override
    public void deleteVideo(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found: " + id));

        // Same fix: resolve absolute path for delete too
        Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize()
                .resolve(video.getFileName());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            System.err.println("Could not delete video file: " + e.getMessage());
        }

        videoRepository.deleteById(id);
    }

    private VideoDTO toDTO(Video entity) {
        VideoDTO dto = modelMapper.map(entity, VideoDTO.class);
        dto.setUrl(baseUrl + "/api/videos/file/" + entity.getFileName());
        return dto;
    }
}