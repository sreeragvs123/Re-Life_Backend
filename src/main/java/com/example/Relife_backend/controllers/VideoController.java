package com.example.Relife_backend.controllers;

import com.example.Relife_backend.dto.VideoDTO;
import com.example.Relife_backend.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VideoController {

    private final VideoService videoService;

    @Value("${app.video-upload-dir:uploads/videos}")
    private String uploadDir;

    // ── POST /api/videos ──────────────────────────────────────────────────────
    // Volunteer uploads a video (multipart form)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoDTO> uploadVideo(
            @RequestParam("title") String title,
            @RequestParam("uploader") String uploader,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(videoService.uploadVideo(title, uploader, file));
    }

    // ── GET /api/videos ───────────────────────────────────────────────────────
    // Admin: all videos regardless of status
    @GetMapping
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

    // ── GET /api/videos/approved ──────────────────────────────────────────────
    // User / Volunteer: only approved videos
    @GetMapping("/approved")
    public ResponseEntity<List<VideoDTO>> getApprovedVideos() {
        return ResponseEntity.ok(videoService.getApprovedVideos());
    }

    // ── PUT /api/videos/{id}/approve ──────────────────────────────────────────
    // Admin: approve a pending video
    @PutMapping("/{id}/approve")
    public ResponseEntity<VideoDTO> approveVideo(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.approveVideo(id));
    }

    // ── DELETE /api/videos/{id} ───────────────────────────────────────────────
    // Admin / Volunteer: delete a video and its file from disk
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }

    // ── GET /api/videos/file/{fileName} ──────────────────────────────────────
    // Serves the raw video file — used by Flutter's VideoPlayerController
    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + fileName + "\"")
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}