package com.moa.moabackend.sse;

import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@RestController
public class SseController {

    public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
    private final JwtUtil jwtUtil;

    @CrossOrigin
    @GetMapping(value = "/sub", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam String token) {

        // 토큰에서 user의 pk값 파싱
        String userName = jwtUtil.getUserIdFromToken(token);

        // 현재 클라이언트를 위한 SseEmitter 생성
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            // 연결!!
            sseEmitter.send(SseEmitter.event().name("connect"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // user의 pk값을 key값으로 해서 SseEmitter를 저장
        sseEmitters.put(userName, sseEmitter);

        sseEmitter.onCompletion(() -> sseEmitters.remove(userName));
        sseEmitter.onTimeout(() -> sseEmitters.remove(userName));
        sseEmitter.onError((e) -> sseEmitters.remove(userName));

        return sseEmitter;
    }

//    @CrossOrigin
//    @GetMapping(value = "/sub", consumes = MediaType.ALL_VALUE)
//    public SseEmitter subscribe(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        // 토큰에서 user의 pk값 파싱
////        String userId = jwtUtil.getUserIdFromToken(userDetails.getUser().getUserName());
//        String userName = userDetails.getUser().getUserName();
//
//        // 현재 클라이언트를 위한 SseEmitter 생성
//        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
//        try {
//            // 연결!!
//            sseEmitter.send(SseEmitter.event().name("connect"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // user의 pk값을 key값으로 해서 SseEmitter를 저장
//        sseEmitters.put(userName, sseEmitter);
//
//        sseEmitter.onCompletion(() -> sseEmitters.remove(userName));
//        sseEmitter.onTimeout(() -> sseEmitters.remove(userName));
//        sseEmitter.onError((e) -> sseEmitters.remove(userName));
//
//        return sseEmitter;
//    }
}
