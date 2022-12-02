package com.moa.moabackend.sse;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

import static com.moa.moabackend.sse.SseController.sseEmitters;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    // 그룹 알람 조회
    public ResponseDto<String> alertEventAddGroup(User user, Alert alert) {
        String userId = user.getUserId();
        if (sseEmitters.containsKey(userId)) {
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try {
                sseEmitter.send(SseEmitter.event().data(alert));
            } catch (Exception e) {
                sseEmitters.remove(userId);
            }
        }
        return ResponseDto.success("알람 보내기 완료");
    }

    public ResponseDto<String> alertEvent(String userName) {
        User user = userRepository.findByUserId(userName).get();
        String userId = user.getUserId();

        if (sseEmitters.containsKey(userId)) {
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try {
                List<Alert> alerts = alertRepository.findAllByReceiver(user.getUserName());
                for (Alert alert : alerts
                ) {
                    if (alert.isCheck() != true) {
                        sseEmitter.send(SseEmitter.event().data(alert));
                    }
                }
            } catch (Exception e) {
                sseEmitters.remove(userId);
            }
        }
        return ResponseDto.success("알람 보내기 완료");
    }

    // 알람 db에서 조회
    public ResponseDto<List<Alert>> getAllAlert(User user) {
        List<AlertResponseDto> alertList = new ArrayList<>();
        List<Alert> alerts = alertRepository.findAllByReceiver(user.getUserName());
        for (Alert alert : alerts) {
            alertList.add(
                    AlertResponseDto.builder()
                            .id(alert.getId())
                            .check(alert.isCheck())
                            .message(alert.getMessage())
                            .receiver(alert.getReceiver())
                            .build()
            );
        }
        return ResponseDto.success(
                alertRepository.findAllByReceiver(user.getUserName())
        );
    }

    // 알람 확인 시 check => true 변경
    public ResponseDto<String> alertCheck(Long alertId) {
        Alert alert = alertRepository.findById(alertId).get();
        alert.setCheck(true);
        alertRepository.save(alert);
        return ResponseDto.success("알람 확인 완료");
    }
}
