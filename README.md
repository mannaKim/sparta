# Meet Our Team
> Firebase를 활용하여 멤버 관리 및 방명록 애플리케이션을 구현한 웹 애플리케이션입니다. 
> <br> Live demo [_here_](https://www.youtube.com/watch?v=z5FeonulKak&ab_channel=%EC%9D%B4%EC%A7%80%EC%9D%80).

<br>

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screeshots)
* [Usage](#usage)
* [Room for Improvement](#room-for-improvement)
* [Contact](#contact)
<!-- * [License](#license) -->

<br>

## General Information


**1. 메인 페이지 (index.html)**
- **팀 소개**: 팀의 목표, 특징, 약속 등 표시
- **멤버 관리**: 팀 멤버들의 정보를 추가하고, 각 멤버의 상세 정보를 확인할 수 있습니다.
  
  - **멤버 추가**
    
    - 사용자가 입력한 이름, 프로필 이미지 URL, 소개, MBTI, 장점, 비밀번호 등을 Firestore의 `members` 컬렉션에 저장합니다.
    - 필수 입력값이 비어 있으면 알림을 표시합니다.
    - 저장 완료 후 페이지를 새로고침합니다.
      
  - **멤버 목록 출력**

    - Firestore의 `members` 컬렉션에서 데이터를 가져와 카드 형태로 페이지에 표시합니다.    
    - 각 멤버 카드는 클릭 시 `members_page.html`로 이동하며, URL에 멤버 정보를 전달합니다.
    
- **네비게이션**

  - 앵커 태그를 사용하여 팀 소개, 멤버 소개로 이동
    
  - 링크를 통해 방명록 페이지로 이동

<br>

**2. 방명록 (guestbook.html)**

- **방명록 작성**

  - 이름, 비밀번호, 메시지를 입력하여 방명록에 글을 남길 수 있습니다. 이 정보는 작성 시간을 포함하여 Firestore의 `postings` 컬렉션에 저장됩니다. 
  
  - 저장 후 페이지를 새로고침합니다.

- **방명록 보기**
  
  - `postings` 컬렉션의 데이터를 작성 시간순으로 정렬하여 출력합니다.
  
- **방명록 수정 및 삭제**

  - 수정/삭제 버튼 클릭 시 비밀번호 입력 모달이 표시됩니다.
  
  - 입력한 비밀번호가 Firestore의 데이터와 일치하면 수정 또는 삭제 작업을 진행합니다.

<br>

**3. 멤버 상세 페이지 (members_page.html)**

- URL에서 전달받은 데이터를 사용하여 멤버 정보를 표시합니다.

- **멤버 상세 조회**: 이름, 프로필 이미지, 한 줄 소개, MBTI, 장점 등 조회할 수 있습니다.

- **멤버 수정**

  - 수정 버튼 클릭 시, Firestore에서 멤버의 최신 데이터를 가져와 모달에 표시합니다.
  
  - 수정 후 Firestore에 데이터를 업데이트하고, 페이지의 DOM도 갱신합니다.

- **멤버 삭제**

  - 삭제 버튼 클릭 시 비밀번호를 확인하고, Firestore에서 해당 데이터를 삭제합니다.
  
  - 삭제 완료 후 메인 페이지로 리다이렉트됩니다.

<br>

## Technologies Used
<div align=center> 
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
 <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <br>
<img src="https://img.shields.io/badge/firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

<br><br>


- **Firebase Firestore**

  - collection, addDoc, getDocs, getDoc, updateDoc, deleteDoc 등 Firestore 메서드를 활용해 CRUD 작업을 수행했습니다.

- **jQuery**

  - DOM 조작 및 이벤트 처리를 간편하게 구현했습니다.

- **Bootstrap**

  - 사용자 인터페이스 구성과 모달, 버튼 등의 스타일링에 사용되었습니다.

<br>

## Features

- 팀 멤버 관리: 멤버 추가, 수정, 삭제 기능을 제공합니다.
  
- 방명록: 방명록 작성, 보기, 수정 및 삭제 기능을 제공합니다.
  
- 부드러운 사용자 경험: 부드러운 스크롤 및 인터랙티브 UI
  
- URL 파라미터 처리: URLSearchParams를 사용해 URL에 전달된 데이터를 추출하고 활용했습니다.

<br>

## Screenshots

![](https://velog.velcdn.com/images/mk3701/post/52bb48b2-98a3-4d46-8fda-7dff997458e6/image.png)
![](https://github.com/user-attachments/assets/43471a58-2294-4376-96eb-d1fd6c579a87)
![](https://github.com/user-attachments/assets/43752572-3aa1-42a3-ac80-c5875723592f)
![](https://github.com/user-attachments/assets/9873d8b5-40ba-4ae9-88f0-dc59235d73bf)


<br>

## Usage

1. 메인 페이지에서 멤버 추가 및 팀 소개 확인
   
2. 방명록 페이지에서 메시지 작성
   
3. 멤버 상세 페이지에서 멤버 정보 수정 및 삭제

<br>

## Room for Improvement

- 방명록 검색 기능 추가

- UI 디자인 개선

- 반응형 웹 디자인 최적화


<br>

## Contact
Created by 
[@JoeMinKyung](https://github.com/JoeMinKyung)
[@mannaKim](https://github.com/mannaKim)
[@Ji-eun1](https://github.com/Ji-eun1)
[@Seungmin-J](https://github.com/Seungmin-J)
[@heesoohi](https://github.com/heesoohi)
<br>

문의사항이 있다면 언제든지 연락주세요!


<!-- Optional -->
<!-- ## License -->
<!-- This project is open source and available under the [... License](). -->

<!-- You don't have to include all sections - just the one's relevant to your project -->
