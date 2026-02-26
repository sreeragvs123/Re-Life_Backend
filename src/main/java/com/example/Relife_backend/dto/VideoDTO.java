package com.example.Relife_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    private Long id;

    private String title;

    // Full URL Flutter uses to play the video
    // e.g. "http://192.168.0.135:8080/api/videos/file/1714900000000_clip.mp4"
    private String url;

    private String status;

    private String uploader;

    private LocalDateTime createdAt;
}