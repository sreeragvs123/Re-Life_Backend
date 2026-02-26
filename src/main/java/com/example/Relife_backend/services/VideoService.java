package com.example.Relife_backend.services;

import com.example.Relife_backend.dto.VideoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    VideoDTO uploadVideo(String title, String uploader, MultipartFile file);

    List<VideoDTO> getAllVideos();

    List<VideoDTO> getApprovedVideos();

    VideoDTO approveVideo(Long id);

    void deleteVideo(Long id);
}