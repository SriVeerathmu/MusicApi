package com.example.demo.Music;


import com.example.demo.Music.MusicSession;
import com.example.demo.Music.SessionService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MusicController {

    private final SessionService sessionService;

    public MusicController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/music/{fileName}")
    public ResponseEntity<Resource> streamAudio(@PathVariable String fileName) throws Exception {
        Path audioPath = Paths.get("C:/Users/50002113/Downloads/demo/demo/src/main/resources/static/" + "sample_audio.mp3");
        Resource audioResource = new UrlResource(audioPath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audioResource);
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSession(@RequestParam String code,
                                               @RequestParam String fileName,
                                               @RequestParam String hostUser) {
        sessionService.startSession(code, fileName, hostUser);
        return ResponseEntity.ok("Session started");
    }

    @GetMapping("/join/{code}")
    public ResponseEntity<Map<String, Object>> joinSession(@PathVariable String code) {
        MusicSession session = sessionService.getSession(code);
        if (session == null) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body(Map.of("error", "Session not found"));
            }
        long offset = Duration.between(session.getStartTime(), java.time.Instant.now()).getSeconds();

        Map<String, Object> response = new HashMap<>();
        response.put("track", session.getTrack());
        response.put("offset", offset);
        return ResponseEntity.ok(response);
    }
}
