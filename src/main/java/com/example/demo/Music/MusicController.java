package com.example.demo.Music;

import com.example.demo.Music.MusicSession;
import com.example.demo.Music.SessionService;
import jakarta.servlet.http.HttpServletRequest; // Added import
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders; // Added import
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException; // Added import
import java.nio.file.Files; // Added import
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional; // Added import
@RestController
@RequestMapping("/api")
public class MusicController {

    private final SessionService sessionService;

    public MusicController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    // @GetMapping("/music/{fileName}")
    // public ResponseEntity<Resource> streamAudio(@PathVariable String fileName) throws Exception {
    //     Path audioPath = Paths.get("C:/Users/50002113/Downloads/demo/demo/src/main/resources/static/" + fileName);
    //     Resource audioResource = new UrlResource(audioPath.toUri());

    //     return ResponseEntity.ok()
    //             .contentType(MediaType.parseMediaType("audio/mpeg"))
    //             .body(audioResource);
    // }
    @GetMapping("/music/{fileName}")
    public ResponseEntity<Resource> streamAudio(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        Path audioPath = Paths.get("C:/Users/50002113/Downloads/demo/demo/src/main/resources/static/" + fileName);
        if (!Files.exists(audioPath)) {
            return ResponseEntity.notFound().build();
        }

        long fileSize = Files.size(audioPath);
        String rangeHeader = request.getHeader(HttpHeaders.RANGE);

        if (rangeHeader == null) {
            // No range header, serve the whole file
            Resource audioResource = new UrlResource(audioPath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .body(audioResource);
        }

        try {
            long rangeStart = 0;
            long rangeEnd = fileSize - 1;
            String[] ranges = rangeHeader.replace("bytes=", "").split("-");
            rangeStart = Long.parseLong(ranges[0]);
            if (ranges.length > 1 && !ranges[1].isEmpty()) {
                rangeEnd = Long.parseLong(ranges[1]);
            }

            if (rangeStart < 0 || rangeStart >= fileSize || rangeEnd < rangeStart || rangeEnd >= fileSize) {
                return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
                        .header(HttpHeaders.CONTENT_RANGE, "bytes */" + fileSize)
                        .build();
            }

            long contentLength = rangeEnd - rangeStart + 1;
            Resource audioResource = new ByteRangeResource(audioPath.toUri(), rangeStart, contentLength);

            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                    .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength))
                    .header(HttpHeaders.CONTENT_RANGE, "bytes " + rangeStart + "-" + rangeEnd + "/" + fileSize)
                    .body(audioResource);

        } catch (NumberFormatException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
