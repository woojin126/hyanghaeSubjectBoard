# hyanghaeSubjectBoard

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
1.회원 가입 페이지
 * 회원가입 버튼을 클릭하기
 * 닉네임, 비밀번호, 비밀번호 확인을 입력하기
 * 닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하기
 * 비밀번호는 최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패로 만들기
 * 비밀번호 확인은 비밀번호와 정확하게 일치하기
 * 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지를 프론트엔드에서 보여주기
 * 회원가입 버튼을 누르고 에러메세지가 발생하지 않는다면 로그인 페이지로 이동시키기
2. 로그인 페이지
 * 로그인, 회원가입 버튼을 만들기
 * 닉네임, 비밀번호 입력란 만들기
 * 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인한 뒤, 하나라도 맞지 않는 정보가 있다면 "닉네임 또는 패스워드를 확인해주세요"라는 메세지를 프론트엔드에서 보여주기
 * 로그인 버튼을 눌러서 에러 메세지가 발생하지 않는다면 전체 게시글 목록 조회 페이지로 이동시키기
3. 로그인 검사
 * 아래 페이지를 제외하고 모두 로그인 한 경우만 볼 수 있도록 하기 
   * 회원가입 페이지 
     1. 로그인 페이지
게시글 목록 조회 페이지
게시글 조회 페이지
로그인을 하지 않거나 올바르지 않은 경로로 접속한 사용자가 로그인이 필요한 경로에 접속한 경우 "로그인이 필요합니다." 라는 메세지를 프론트엔드에서 띄워주고 로그인 페이지로 이동시키기
로그인 한 사용자가 로그인 페이지 또는 회원가입 페이지에 접속한 경우 "이미 로그인이 되어있습니다."라는 메세지를 띄우고 전체 게시글 목록 조회 페이지로 이동시키기
소셜 로그인 기능 만들기
카카오 소셜 로그인이 가능하도록 하기
게시글 조회 페이지 > 댓글 목록 조회
로그인 하지 않은 사용자도 댓글 목록 조회가 가능하도록 하기
현재 조회중인 게시글에 작성된 모든 댓글을 목록 형식으로 볼 수 있도록 하기
댓글 목록 위에 댓글 작성란 만들기
댓글 작성에 관한 기능은 5번 요구사항으로 연결하기
댓글 목록 중, 내가 작성한 댓글인 경우 댓글 수정, 댓글 삭제 버튼 만들기
댓글 수정 버튼을 누르면 6번 요구사항으로 연결하기
댓글 삭제 버튼을 누르면 7번 요구사항으로 연결하기
제일 최근 작성된 댓글을 맨 위에 띄우기
게시글 조회 페이지 > 댓글 작성
로그인 한 사용자만 댓글 작성이 가능하도록 하기
게시글 조회 페이지 하단에 댓글 내용을 입력할 수 있는 댓글 작성 버튼 만들기
로그인 하지 않은 사용자가 댓글 작성란을 누르면 "로그인이 필요한 기능입니다." 라는 메세지를 띄우고 로그인 페이지로 이동시키기
댓글 내용란을 비워둔 채 댓글 작성 버튼을 누르면 "댓글 내용을 입력해주세요" 라는 메세지를 띄우기
댓글 내용을 입력하고 댓글 작성 버튼을 누른 경우 작성한 댓글을 추가하기
게시글 조회 페이지 > 댓글 수정
내가 작성한 댓글만 수정 가능하도록 하기
댓글 본문이 사라지고, 댓글 내용, 저장 버튼 생성하기
댓글 내용에는 이전에 입력했던 댓글 내용을 기본 값으로 채우기
수정할 댓글 내용은 비어 있지 않도록 하기
저장 버튼을 누른 경우 기존 댓글의 내용을 새로 입력한 댓글 내용으로 바꾸기
게시글 조회 페이지 > 댓글 삭제
내가 작성한 댓글만 삭제 가능하도록 하기
"정말로 삭제하시겠습니까?" 메세지를 띄우고, 확인/취소 버튼 중 "확인" 버튼을 누른 경우 목록에서 해당 댓글을 삭제하기
취소를 누른 경우 삭제되지 않고 그대로 유지하기
회원가입 테스트 코드 작성
회원가입시 구현한 아래 조건을 검사하는 테스트 코드를 작성하기
닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 이루어져 있어야 합니다.
비밀번호는 최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패합니다.
비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다.
데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지가 발생합니다.
테스트 코드 실행 시 실제로는 데이터베이스에 연결되지 않도록 하기
각 조건 별로 2개 이상의 테스트 케이스가 존재하도록 하기
화면구현
로그인 image

회원가입 image

메인페이지 image

게시글 상세페이지 & 댓글목록 image

게시글 작성페이지 image
