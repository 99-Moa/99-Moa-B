package com.moa.moabackend.service;

import com.moa.moabackend.entity.ResponseDto;
import com.moa.moabackend.entity.user.RefreshToken;
import com.moa.moabackend.entity.friend.FriendResponseDto;
import com.moa.moabackend.entity.user.*;
import com.moa.moabackend.jwt.JwtUtil;
import com.moa.moabackend.jwt.TokenDto;
import com.moa.moabackend.repository.RefreshTokenRepository;
import com.moa.moabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final FriendService friendService;

    // 회원가입
    public ResponseDto<String> signup(UserRequestDto userRequestDto){
        // userid 중복 검사
        // Exception 값 컨펌!
        if(userRepository.findByUserId(userRequestDto.getUserId()).isPresent()){
            throw new RuntimeException("Overlap Check");
        }
        // passwordCheck와 같은지 확인
        if (!userRequestDto.getPasswordCheck().equals(userRequestDto.getPassword())) {
            return ResponseDto.fail(500, "Bad Request", "비밀번호 확인이 일치하지 않습니다");
        }

        // password 값 인코딩 후 Dto 에 재주입 후 Dto 를 Entity 로  변환
        userRequestDto.setEncodePwd(passwordEncoder.encode(userRequestDto.getPassword()));
        User user = new User(userRequestDto);

        userRepository.save(user);
        return ResponseDto.success(
                "회원가입 성공"
        );
    }

    // ID 중복확인
    public ResponseDto<String> idCheck(IdCheckRequestDto idCheckRequestDto){
        String result = "";
        if(userRepository.findByUserId(idCheckRequestDto.getUserId()).isPresent()) {
            result = "ID가 이미 존재합니다.";
        }else{
            result = "사용가능한 ID 입니다.";
        }
        return ResponseDto.success(result);
    }

    //닉네임 중복확인
    public ResponseDto<String> nameCheck(NameCheckRequestDto nameCheckRequestDto){
        String result = "";
        if(userRepository.findByUserName(nameCheckRequestDto.getUserName()).isPresent()) {
            result = "닉네임이 이미 존재합니다.";
        }else{
            result = "사용가능한 닉네임 입니다.";
        }
        return ResponseDto.success(result);
    }

    // 로그인
    @Transactional
    public ResponseDto<String> signin(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // userId 로 user 정보 호출
        User user = userRepository.findByUserId(loginRequestDto.getUserId()).orElseThrow(
                () -> new RuntimeException("Not found Account")
        );


        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            return ResponseDto.fail(500, "비밀번호가 일치하지 않습니다", "Bad Request");
        }

//        // password 일치 여부 확인 -> password 인코딩
//        if (passwordEncoder.matches(passwordEncoder.encode(loginRequestDto.getPassword()), user.getPassword())) {
//            throw new RuntimeException("Not matches Password");
//        }

        // userId 값을 포함한 토큰 생성 후 tokenDto 에 저장
        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getUserId());

        // userId 값에 해당하는 refreshToken 을 DB에서 가져옴
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountUserId(loginRequestDto.getUserId());

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getUserId());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return ResponseDto.success(
                "로그인 성공"
        );
    }

    // 로그아웃
    @Transactional
    public ResponseDto<String> signout(String userId){
        // 해당 유저의 refreshtoken 이 없을 경우
        if (refreshTokenRepository.findByAccountUserId(userId).isEmpty()){
            return ResponseDto.fail(404,"Bad Request", "로그인을 해주세요.");
        }
        // 자신의 refreshtoken 만 삭제 가능
        String userIdrepo = refreshTokenRepository.findByAccountUserId(userId).get().getAccountUserId();
        if(userId.equals(userIdrepo)){
            refreshTokenRepository.deleteByAccountUserId(userId);
            return ResponseDto.success("로그아웃 성공");
        }else{
            return ResponseDto.fail(401,"Unauthorized","refreshtoken 삭제 권한이 없습니다.");
        }
    }

    // 토큰 재발행
    public ResponseDto<String> issuedToken(String userId, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userId, "Access"));
        return ResponseDto.success("토큰재발행 성공");
    }


    // 친구 찾기
    public ResponseDto<FriendResponseDto.SearchFriendResDto> searchFriend(User user, String friendUsername ) {

        // 친구에 해당하는 존재여부 -> Null값 예외처리 추가
        if (userRepository.findByUserName((friendUsername)).isEmpty()){
            return ResponseDto.fail(400, "Bad Request", "등록되지 않은 유저입니다.");
        }
        User userFriend = userRepository.findByUserName(friendUsername).get();
            // 이미 추가된 친구 제외
        if (friendService.checkMyFriend(user.getUserId(), friendUsername)) {
            return ResponseDto.fail(400, "Bad Request", "이미 등록된 친구입니다.");
            // 자기 자신을 추가
        } else if (user.getUserName().equals(friendUsername)) {
            return ResponseDto.fail(400, "Bad Request", "자기자신을 검색할 수 없습니다.");
        } else {
            return ResponseDto.success(
                    FriendResponseDto.SearchFriendResDto.builder()
                            .userId(userFriend.getUserId())
                            .friendUsername(userFriend.getUserName())
                            .imgUrl(userFriend.getImgUrl())
                            .build());
        }
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

    // userId 로 userName 추축
    public String getUserNameByUserId(String userId){
        return userRepository.findByUserId(userId).get().getUserName();
    }
}