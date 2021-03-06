# YouthCareApplication
2019-2 Software Development Practice4



### ■ 프로젝트  개요 및 목표
 교육부 공공데이터인 학생건강 기준표 정보를 제공하고 성장에 대한 동기부여를 주며 커뮤니티를 통해 정보를 주고 받을 수 있는 애플리케이션 개발을 목적으로 한다.
 







### ■ 프로젝트 수행 내용
 #### □ 프로젝트 명세서 작성
  주위의 여러가지 문제점들을 살펴보며 아이디어를 도출해내고 회의를 통해 프로젝트 배경과 필요성을 생각해봄으로서 아이디어를 구체화 하는 과정
  
 #### □ 프로젝트 요구사항 기술서 작성
  사용자의 요구사항을 작성하여 프로젝트의 구체적인 기능을 구상하고 안드로이드의 각 Acivity간의 화면 구성도를 작성하고 화면을 정의함.
| 요구사항 번호 | 요구사항 이름 | 요구사항 내용 | 중요도 |
|:---:|:---:|:---:|:---:|
|PR-1|실시간 알림 서비스| notification 기능을 통해 운동 알림 서비스를 제공함 | ★★★★☆ |
|PR-2|성장 기준표 비교| 성장 기준표를 제공하여 본인의 키와 비교할 수 있음 | ★★★★★ |
|PR-3|사용자 신체정보 입력| 신체정보 입력 항목을 세분화하여 구체적으로 입력 | ★★★☆☆ |
|PR-4|성장 커뮤니티| 커뮤니티 서비스를 통해 성장에 대한 정보를 공유할 수 있음 | ★★★★☆ |
|PR-5|애플리케이션 편의성|다양한 기능을 쉽게 접근할 수 있도록 UI 디자인 | ★★★☆☆ |

#### □ 프로젝트 개념적 데이터 모델링


![image](https://user-images.githubusercontent.com/53038387/82645427-d2b95e80-9c4d-11ea-9fb3-d83c10257e8f.png)  




#### □ 프로젝트 데이터 플로우 작성


![image](https://user-images.githubusercontent.com/53038387/82645557-03999380-9c4e-11ea-84c3-daa71b363f97.png)    







### ■ 프로젝트 주요기능 및 기술

| Navigation View |
|:---|
| 사이드 메뉴로 네비게이션 Drawable기능 구현   
총 4개의 Fragment로 화면전환 가능   
navigation drawable – 총 4개 메뉴는 fragment로 화면전환 |

| FCM Notification 기능 |
|:---|
| Google에서 제공하는 FireBase의 FCM 기능을 통해서 실시간 알림 서비스 제공
로그인할 경우 모바일 폰의 토큰 값이 FCM으로 전송이 되어 토큰 값이 저장된 디바이스에게 FCM 메시지 전송 기능을 수행 함. |

| Android → Node.js → Maria DB 통신 |
|:---|
|Android에서 입력 값을 입력하면 Json형식으로 포맷을 만들어 http 통신으로 POST 데이터 전송을 통해 Node.js 웹 서버에 전송되어 Insert 문을 통해서 해당 데이터를 Maria DB에 전송 |

| Android ← Node.js ← Maria DB 통신 |
|:---|
| Maria DB에 저장되어 있는 데이터를 사용자의 요청에 따라 REST API  요청 Retrofit2 통신방식을 이용하여 네트워크로부터 전달된 데이터를 필요한 형태의 객체로 받을 수 있음 |






### ■ 프로젝트 수행 결과
      1.IntroActivity, SignUpAcitivty, LoginActivity
        1. IntroActivity : 인트로 화면 출력
        2. SignUpActivity : 시스템에 회원가입을 할 수 있는 화면
        3. LoginActivity : 시스템에 로그인할 수 있는 화면
        
      2. MainActivity, MyInformationFragment, MyKeyFragment, MyCommunityFragment, MyDiaryFragment
         1. MainActivity : NavigationView 사용하여 4가지 Fragment 전환 가능
         2. MyInformationFragment : 현재 나의 신체정보 확인 및 평균 성장 수치 비교
         3. MyKeyFragment : 나의 신체정보 입력 및 업데이트할 수 있는 화면
         4. MyCommunityFragment : 성장 커뮤니티를 통해 정보를 교환할 수 있는 게시판
           1. CommunityItem : 게시판 목록 확인
           2. CommunityAdapter : 게시판 데이터 관리 및 목록 표시
           3. WriteActivity : 게시판 글쓰기 기능
         5. MyDiaryFragment : 하루동안 성장에 대한 할당량 체크를 달력에 표시하고 업데이트 가능한 화면
         


[![Watch the video](https://img.youtube.com/vi/wxQp5-TUEZ8/0.jpg)](https://www.youtube.com/watch?v=wxQp5-TUEZ8)





### ■ 기대효과 및 활용 방안
  1. 평균 수치와 입력된 값을 비교하여 현 위치를 제공함으로써 성장 동기부여
  2. 캘린더와 Push 알림을 통해 자기 주도적 습관형성 유도
  3. 청소년들이 서로 커뮤니티 서비스를 통해 성장 과정이나 고민의 해결방안 공유
  
      
