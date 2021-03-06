# hyanghaeSubjectBoard
 + 간단한 CRUD와Oauth2 를 사용한 보드
 + CRUD를 활용한 스프링 JPA 프로젝트
 + 첫 개인 주특기 프로젝트
# 스케쥴링
2021/11/22 - 2021//11/27
* 11/22: 프로젝트 기획/ API 설계/ Entity 설계
* 11/23: 프로젝트 구현에있어 필요한 JPA 강의 일부 시청
* 11/24: Security 를 사용한 회원가입,일반 로그인과, Google,KaKao Oauth2 Login 구현
* 11/25: 로그인한 사용자 정보가 연관된 게시글 테이블 구현 ( 로그인, 회원가입 에서 발생하는 예외처리, 같은 아이디 회원, 비밀번호 불일치 등)
* 11/26: 게시글 상세페이지에서 여러유저가 삭제, 수정 할 수있는 게시글 구현
* 11/27: 전체적인 flow 테스트밑 에러, 테스트코드 적용

# 개발환경
* FRONT Language:HTML5, CSS{BootStrap}/ Javascript{jQuery 3.5.1}, Thymeleaf(Server Side Render)
* BACK-END: Spring Web , Spring DATA JPA, Spring Security, JUNIT 5
* DataBase: Mysql
* Server: AWS EC2 , DB: Redis 배포 주소 http://15.164.233.249/
* domain: 가비아 , 현재는 구동 x

# 추후 계획
* 페이징기능 추가
* UI 꾸미기
# Notice-Board
* 스프링부트, JPA를 이용한 게시판 구현
* 주요기능
* 회원가입 백엔드에서 유효성 검사
* 카카오 로그인 구현
* 스프링 시큐리티를 사용한 로그인 로그아웃
* 자신이 작성한 게시글, 댓글만 수정 삭제 가능
* API 리스트
* image

# 요구사항
## 회원 가입 페이지
 * 회원가입 버튼을 클릭하기
 * 닉네임, 비밀번호, 비밀번호 확인을 입력하기
 * 닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하기
 * 비밀번호는 최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패로 만들기
 * 비밀번호 확인은 비밀번호와 정확하게 일치하기
 * 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지를 프론트엔드에서 보여주기
 * 회원가입 버튼을 누르고 에러메세지가 발생하지 않는다면 로그인 페이지로 이동시키기
##  로그인 페이지
 * 로그인, 회원가입 버튼을 만들기
 * 닉네임, 비밀번호 입력란 만들기
 * 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인한 뒤, 하나라도 맞지 않는 정보가 있다면 "닉네임 또는 패스워드를 확인해주세요"라는 메세지를 프론트엔드에서 보여주기
 * 로그인 버튼을 눌러서 에러 메세지가 발생하지 않는다면 전체 게시글 목록 조회 페이지로 이동시키기
##  로그인 검사
 * 아래 페이지를 제외하고 모두 로그인 한 경우만 볼 수 있도록 하기 
   * 회원가입 페이지 
   * 로그인 페이지
   * 게시글 목록 조회 페이지
   * 게시글 조회 페이지
 * 로그인을 하지 않거나 올바르지 않은 경로로 접속한 사용자가 로그인이 필요한 경로에 접속한 경우 "로그인이 필요합니다."    라는 메세지를 프론트엔드에서 띄워주고 로그인 페이지로 이동시키기
   로그인 한 사용자가 로그인 페이지 또는 회원가입 페이지에 접속한 경우 "이미 로그인이 되어있습니다."라는 메세지를 띄우    고 전체 게시글 목록 조회 페이지로 이동시키기
## 소셜 로그인 기능 만들기
 * 카카오 소셜 로그인이 가능하도록 하기
## 게시글 조회 페이지 > 댓글 목록 조회
 * 로그인 하지 않은 사용자도 댓글 목록 조회가 가능하도록 하기
 * 현재 조회중인 게시글에 작성된 모든 댓글을 목록 형식으로 볼 수 있도록 하기
 * 댓글 목록 위에 댓글 작성란 만들기
   * 댓글 작성에 관한 기능은 5번 요구사항으로 연결하기
 * 댓글 목록 중, 내가 작성한 댓글인 경우 댓글 수정, 댓글 삭제 버튼 만들기
   * 댓글 수정 버튼을 누르면 6번 요구사항으로 연결하기
   * 댓글 삭제 버튼을 누르면 7번 요구사항으로 연결하기
 * 제일 최근 작성된 댓글을 맨 위에 띄우기
## 게시글 조회 페이지 > 댓글 작성
 * 로그인 한 사용자만 댓글 작성이 가능하도록 하기
 * 게시글 조회 페이지 하단에 댓글 내용을 입력할 수 있는 댓글 작성 버튼 만들기
 * 로그인 하지 않은 사용자가 댓글 작성란을 누르면 "로그인이 필요한 기능입니다." 라는 메세지를 띄우고 로그인 페이지로 이  동시키기
 * 댓글 내용란을 비워둔 채 댓글 작성 버튼을 누르면 "댓글 내용을 입력해주세요" 라는 메세지를 띄우기
 * 댓글 내용을 입력하고 댓글 작성 버튼을 누른 경우 작성한 댓글을 추가하기
## 게시글 조회 페이지 > 댓글 수정
 * 내가 작성한 댓글만 수정 가능하도록 하기
 * 댓글 본문이 사라지고, 댓글 내용, 저장 버튼 생성하기
 * 댓글 내용에는 이전에 입력했던 댓글 내용을 기본 값으로 채우기
 * 수정할 댓글 내용은 비어 있지 않도록 하기
 * 저장 버튼을 누른 경우 기존 댓글의 내용을 새로 입력한 댓글 내용으로 바꾸기
## 게시글 조회 페이지 > 댓글 삭제
 * 내가 작성한 댓글만 삭제 가능하도록 하기
 * "정말로 삭제하시겠습니까?" 메세지를 띄우고, 확인/취소 버튼 중 "확인" 버튼을 누른 경우 목록에서 해당 댓글을 삭제하기
   취소를 누른 경우 삭제되지 않고 그대로 유지하기
## 회원가입 테스트 코드 작성
 * 회원가입시 구현한 아래 조건을 검사하는 테스트 코드를 작성하기
   * 닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 이루어져 있어야 합니다.
   * 비밀번호는 최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패합니다.
   * 비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다.
   * 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지가 발생      합니다.
 * 테스트 코드 실행 시 실제로는 데이터베이스에 연결되지 않도록 하기
 * 각 조건 별로 2개 이상의 테스트 케이스가 존재하도록 하기
## 화면구현
로그인 image
![login](https://user-images.githubusercontent.com/25544668/143733778-ba82c30a-cdcd-4765-8da5-8da60c6ff39b.PNG)

회원가입 image
![signup](https://user-images.githubusercontent.com/25544668/143733784-e358fea8-914e-47b5-84c2-edfbb63736d7.PNG)

메인페이지 image
![mainboard](https://user-images.githubusercontent.com/25544668/143733786-93d5b1ae-496b-44eb-983b-b362f5f24d14.PNG)

게시글 상세페이지 & 댓글목록 image
![details](https://user-images.githubusercontent.com/25544668/143733788-ad7e6cf9-4ab5-4c08-9e8e-50d3e8fdf81f.PNG)

## entity 설계 구조
 
![diagram](https://user-images.githubusercontent.com/25544668/143748008-3d789bc9-b667-41c0-8e04-bd921d87e8b6.PNG)

