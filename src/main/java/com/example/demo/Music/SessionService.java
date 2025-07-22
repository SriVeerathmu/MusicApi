package com.example.demo.Music;

import java.util.HashMap;
import java.util.Map;
import com.example.demo.Music.MusicSession;
import org.springframework.stereotype.Service;


@Service
public class SessionService {
     private Map<String, MusicSession> sessions = new HashMap<>();

    public void startSession(String code, String track, String hostUser) {
        MusicSession session = new MusicSession();
        session.setSessionCode(code);
        session.setTrack(track);
        session.setHostUser(hostUser);
        session.setStartTime(java.time.Instant.now());
        sessions.put(code, session);
    }

    public MusicSession getSession(String code) {
        return sessions.get(code);
    }
}
