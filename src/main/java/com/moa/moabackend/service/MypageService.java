package com.moa.moabackend.service;

import com.moa.moabackend.S3.S3Uploader;
import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.user.MypageRequestDto;
import com.moa.moabackend.entity.user.User;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final S3Uploader s3Uploader;

    private final UserRepository userRepository;

    // 마이페이지 수정
    public ResponseDto<String> updateMypage(User user, MypageRequestDto requestDto, MultipartFile imgUrl) throws IOException {
        // 이미지 업로드 .upload(파일, 경로)
        String imgPath = s3Uploader.upload(imgUrl, "images");
        // requestDto의 imgUrl을 imgPath의 값으로 설정
        requestDto.setImgUrl(imgPath);

        user.updateUser(requestDto);
        userRepository.save(user);

        return ResponseDto.success("수정 완료!");
    }

    // 유저 정보 가져오기
    public ResponseDto<User> getUserDetail(User user) {
        return ResponseDto.success(user);
    }
}
