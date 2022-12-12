# Moa 모아
![Moa_banner](https://user-images.githubusercontent.com/113873122/206840888-a08eaf56-0921-47a0-8e1c-21db8a242065.jpg)

## 🌟 프로젝트 소개

⏰모아(Moa)는 간편한 일정 작성을 도와주는 실시간 일정 등록, 관리 서비스입니다.⏰

친구 및 지인 등 함께하고자 하는사람들과 그룹을 만들고, 실시간으로 채팅을 하며 일정을 작성해보세요!
- 다음 일정까지의 시간을 한 눈에 보여줘요
- 채팅으로 친구들과 함께 일정을 정해요
- 지도 검색으로 간편하게 장소를 찾아요

### [> 모아 보러가기 < click!](https://moa99.site/)

### [> 모아 notion 보러가기 <](https://www.notion.so/99-2-4ce564cddbb54c01b47c5d73e7c1a2e6)

## 🚀 주요 기능

⏰캘린더 일정 목록 확인

🙋친구 추가, 친구 그룹 생성

💬실시간 채팅, 실시간 일정 작성

🧑‍🤝‍🧑그룹, 친구 추가 시 알림 기능

## ⚙️ 기술 스택
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"><img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white"><img src="https://img.shields.io/badge/AmazonRDS-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"><img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"><img src="https://img.shields.io/badge/websocket-444444?style=for-the-badge&logo=websocket&logoColor=white"><img src="https://img.shields.io/badge/STOMP-000000?style=for-the-badge&logo=STOMP&logoColor=white"><img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">

[//]: # (<img src="https://img.shields.io/badge/기술이름-#제외색상번호?style=for-the-badge&logo=아이콘이름&logoColor=white">)

## 🛠️ 서비스 아키텍처

<img width="1048" alt="스크린샷 2022-12-10 13 49 34" src="https://user-images.githubusercontent.com/113868566/206829731-694589f4-a2df-400f-92b7-14cd749c6a4a.png">

## ⚡ 트러블슈팅

<details>
<summary>MySQL 연동 후 기능 작동 에러</summary>
<div markdown="1">       

📌 문제 상황
- MySQL 연동 후 api테스트 시 500에러가 나며 실행되지 않음


💡 해결
- 테이블, 컬럼에 예약어를 사용하는지 확인 : group, check 등의 예약어를 사용하고 있었으나, 에러는 user테이블에서 발생하고 있었으며 globally_quoted_identifiers로 예약어 이름도 허용할 수 있게 해놓은 상태
- MySQL workbench에 연결해보니 DB에 테이블이 아예 생성이 되지 않는 것을 발견 : JPA 설정에서 database-platform을 h2에서 mySQL로 변경 후 정상 작동

</div>
</details>

<details>
<summary>SSE 연결 시 hikariCP connection 에러</summary>
<div markdown="1">

📌 문제 상황

- SSE 연결 한 뒤 다른 요청을 보냈을 때 30초간 지연이 되다 connection timeout에러가 발생


💡 해결
- hikari timeout 설정으로 시간 늘리기 : 늘린 시간만큼 지연되다 다시 에러 발생
- OSIV 설정을 false로 변경하여 트랜잭션을 종료한 뒤 DB 커넥션을 반환하게 함

</div>
</details>

<details>
<summary>SSE 연결 시 hikariCP connection 에러</summary>
<div markdown="1">

📌 문제 상황

- SSE 연결 한 뒤 다른 요청을 보냈을 때 30초간 지연이 되다 connection timeout에러가 발생

💡 해결
- hikari timeout 설정으로 시간 늘리기 : 늘린 시간만큼 지연되다 다시 에러 발생

</div>
</details>

<details>
<summary>web socket 유저 정보 요청</summary>
<div markdown="1">    

📌 문제 상황

- MessageMapping으로 소켓 메시지 전송 시 로그인한 유저의 정보를 받아오는 @AuthenticationPrincipal을 사용할 수 없음


💡 해결
- 소켓 통신은 http 와 다르게 header가 존재하지 않아 payload에 token을 포함시켜 보내서 유저 정보를 받음
</div>
</details>

<details>
<summary>채팅방 null 에러</summary>
<div markdown="1">

📌 문제 상황

- 채팅방에 접속중인 유저를 List 형식으로 받는데, 모든 유저가 나갈경우 List 값이 null이 되어 리스트 관련 함수(contains, isEmpty, size 등) 사용불가 (NullpointerException)


💡 해결
- 유저 리스트안에 모든 유저가 나가도 남아있는 값(chatRommId)을 default로 저장해둠
</div>
</details>

<details>
<summary>카카오 로그인 유저 정보</summary>
<div markdown="1">

📌 문제 상황

- 카카오 로그인시 일반 로그인과 다르게 DB에 유저정보를 저장하지 않아, 다른 서비스 이용시 유저정보를 사용할 수 없음


💡 해결
- 카카오에서 임의로 부여하는 ID가 있다는 것을 발견, 그것을 기본값으로 가지고 와서 유저 정보 생성

</div>
</details>


## 👨‍👩‍👧 팀원소개

|  이름   |             깃허브 주소              |  포지션  
|:-----:|:-------------------------------:|:-----:|
|  박종원  |   https://github.com/ParkBig    |  FE   |
|  윤상민  |   https://github.com/nimgnas    |  FE   |
|  이진호  |   https://github.com/Jino0403   |  FE   |
|  윤시원  |   https://github.com/yuns8708   |  BE   |
|  신현재  |  https://github.com/tmpanmitw   |  BE   |
|  이동진  | https://github.com/acutecritic  |  BE   |