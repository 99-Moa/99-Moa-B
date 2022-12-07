package com.moa.moabackend.service;

import com.moa.moabackend.S3.S3Uploader;
import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.group.Group;
import com.moa.moabackend.entity.user.mypage.MypageRequestDto;
import com.moa.moabackend.entity.user.mypage.MypageResponseDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.GroupRepository;
import com.moa.moabackend.repository.UserRepository;
import com.moa.moabackend.sse.Alert;
import com.moa.moabackend.sse.AlertRepository;
import com.moa.moabackend.sse.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final S3Uploader s3Uploader;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final AlertRepository alertRepository;

    // 마이페이지 수정
    public ResponseDto<String> updateMypage(User user, MypageRequestDto requestDto, MultipartFile imgUrl) throws IOException {
        // 바꾸기 전 닉네임 저장
        String preUserName = user.getUserName();

        // 알람에서 보낸사람 닉네임 업데이트
        List<Alert> alertList = alertRepository.findAllBySender(user.getUserName());

        // 그룹 유저리스트에서 로그인한 유저 닉네임 삭제
        List<Group> groups = groupRepository.findAll();
        List<Group> myGroups = new ArrayList<>();
        for (Group group : groups) {
            for (int i = 0; i <= group.getUsers().size() - 1; i++) {
                // 로그인한 유저의 닉네임과 그룹 유저 리스트의 닉네임이 일치하면
                if (group.getUsers().get(i).equals(preUserName)) {
                    group.getUsers().remove(i);
                    myGroups.add(group);
                }
            }
        }

        // 닉네임 중복 검사, 공백일 시 닉네임 그대로
        if (userRepository.findByUserName(requestDto.getUserName()).isPresent()) {
            return ResponseDto.fail(409, "중복된 닉네임입니다.", "Conflict");
        }
        if (requestDto.getUserName().equals("")) {
            requestDto.setUserName(user.getUserName());
        }

        // 공백일 시 imgUrl 그대로
        if (imgUrl == null) {
            requestDto.setImgUrl(user.getImgUrl());
        } else {
            // 이미지 업로드 .upload(파일, 경로)
            String imgPath = s3Uploader.upload(imgUrl, "images");
            // requestDto의 imgUrl을 imgPath의 값으로 설정
            requestDto.setImgUrl(imgPath);
        }
        user.updateUser(requestDto);
        userRepository.save(user);

        // 그룹 유저리스트에서 로그인한 유저 닉네임 등록
        for (Group group : myGroups) {
            group.updateGroup(user.getUserName());
            groupRepository.save(group);
        }

        // 알람에서 보낸사람 닉네임 업데이트
        for (Alert alert : alertList) {
            alert.updateAlert(user.getUserName(), user.getImgUrl());
            alertRepository.save(alert);
        }
        return ResponseDto.success("수정 완료!");
    }

    // 유저 정보 가져오기
    public ResponseDto<MypageResponseDto> getUserDetail(User user) {
        return ResponseDto.success(
                MypageResponseDto.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .imgUrl(user.getImgUrl())
                        .build()
        );
    }
}
