package com.moa.moabackend.sse;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.GroupRequestDto;
import com.moa.moabackend.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AlertController {
    private final AlertService alertService;

    // userName 유저에게 알람 보내기
//    @PostMapping("/alert/{userName}")
//    public ResponseDto<String> alertEvent(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable String userName) {
//        return alertService.alertEvent(userDetails.getUser(), userName);
//    }

//    // userName 유저에게 알람 보내기
//    @PostMapping("/alert/{userName}")
//    public ResponseDto<String> alertEvent(@PathVariable String userName) {
//        return alertService.alertEvent(userName);
//    }

    // 본인 닉네임으로 온 알람 모두 표시하기
    @GetMapping("/alerts")
    public ResponseDto<List<Alert>> getAllAlert(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return alertService.getAllAlert(userDetails.getUser());
    }

    @PostMapping("/alert/{alertId}")
    public ResponseDto<String> checkAlert(@PathVariable Long alertId) {
        return alertService.alertCheck(alertId);
    }

}
