<div align= "center">
    <img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&height=180&text=주특기%20플러스 복습과제%20&animation=fadeIn&fontColor=000000&fontSize=60" />
    </div>
    <div align= "center"> 
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;">  </h2>  
    <div style="font-weight: 700; font-size: 15px; text-align: center; color: #282d33;">  </div> 
    </div>
    <div align= "center">
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🛠️ Tech Stacks </h2> <br> 
    <div style="margin: 0 auto; text-align: center;" align= "center"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
          <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=기술스택아이콘&logoColor=white">
          </div>
    </div>
    <div align= "center">
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🧑‍💻 Contact me </h2> <br> 
    <div align= "center"> <a href=https://valar01.tistory.com/> <img src="https://img.shields.io/badge/Tistory-000000?style=for-the-badge&logo=Tistory&logoColor=white&link=https://valar01.tistory.com/"> </a>
          </div>  <br> 
    <div align= "center">  </div> 
    </div>
    <div align= "center"> 
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🏅 Stats </h2> <div align= "center"> <img src="https://github-readme-stats.vercel.app/api?username=hysup&custom_title=hysup's Github Stat&bg_color=180,000000,&title_color=000000&text_color=000000"
        /> <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=hysup&layout=compact&bg_color=180,000000,&title_color=000000&text_color=000000"
           /> </div> 
    </div>


### 기술 스택
언어: kotlin

프레임워크: Spring Boot

데이터베이스: h2

인증: JWT(Json Web Token)



## 🛠 기능
## 회원 가입 API

사용자의 닉네임과 비밀번호를 입력받아 회원 가입을 처리

닉네임은 유효성 검사를 거쳐 최소 3자 이상의 알파벳 대소문자와 숫자로 구성

비밀번호는 최소 4자 이상이며, 닉네임과 중복X.

비밀번호 확인 필드를 통해 비밀번호 일치 여부를 확인.

## 로그인 API
등록된 사용자의 닉네임과 비밀번호를 받아 로그인을 처리

로그인 성공 시, 로그인에 성공한 유저의 정보를 JWT를 활용하여 클라이언트에게 Cookie로 전달

## 게시글  API

전체 게시글의 제목, 작성자 닉네임, 작성 날짜를 조회

특정 게시글의 제목, 작성자 닉네임, 작성 날짜, 작성 내용을 조회

인증된 사용자만 게시글을 작성가능 

게시글을 작성한 사용자만 해당 게시글을 수정

게시글을 작성한 사용자만 해당 게시글을 삭제

## 댓글 API

특정 게시글에 연결된 댓글 목록을 조회

댓글을 작성한 사용자만 해당 댓글을 수정 및 삭제 

## 좋아요 API 
사용자가 해당 게시글에 좋아요를 추가 및 삭제
