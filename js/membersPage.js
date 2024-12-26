// ================================================
// ================ firebase ====================
// ================================================

// Firebase SDK 라이브러리 가져오기
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.22.0/firebase-app.js";
import {
  getFirestore,
  doc,
  deleteDoc,
  updateDoc,
  collection,
  addDoc,
  getDocs,
  getDoc,
  query,
  orderBy,
} from "https://www.gstatic.com/firebasejs/9.22.0/firebase-firestore.js";

// Firebase 구성 정보 설정
const firebaseConfig = {
  apiKey: "AIzaSyBkQICiWSjg5HOoTbRABf_3vKGWiRuXjXk",
  authDomain: "sparta-99127.firebaseapp.com",
  projectId: "sparta-99127",
  storageBucket: "sparta-99127.firebasestorage.app",
  messagingSenderId: "21636541090",
  appId: "1:21636541090:web:53ae33565c0f1bed2745fb",
  measurementId: "G-J0J83TFTXV",
};

// Firebase 인스턴스 초기화
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);

// ================================================
// ================ 멤버페이지 시작 ================
// ================================================

// URL 파라미터 가져오기 함수
function getQueryParams() {
  const params = new URLSearchParams(window.location.search);
  return {
    id: params.get("id"),
    name: params.get("name"),
    intro: params.get("intro"),
    mbti: params.get("mbti"),
    merit: params.get("merit"),
    profileImg: params.get("profileImg"),
  };
}

// 페이지 로드 후 데이터 출력
window.onload = function () {
  const { id, name, intro, mbti, merit, profileImg } = getQueryParams();

  // HTML 요소에 데이터 삽입
  document.querySelector(
    ".pricing-header p"
  ).textContent = `${mbti}가 전원 IS로 시작하는 8조의 ${name}을(를) 소개합니다!🤓`;
  document.querySelector(".img-fluid").src = profileImg;
  document.querySelector(".img-fluid").alt = `${name} 프로필 이미지`;
  document.querySelector(".mb-content").innerHTML = `
      <p>이름 : ${name}</p>
      <p>한줄 소개 : ${intro}</p>
      <p>MBTI : ${mbti}</p>
      <p>자신의 장점 : ${merit}</p>
    `;

  // 삭제 버튼에 문서 ID 추가
  document.getElementById("deleteBtn").setAttribute("data-id", id);
};

// 수정 버튼 클릭 이벤트
document.getElementById("editBtn").addEventListener("click", async function () {
  const { id } = getQueryParams(); // URL에서 id만 가져옴

  try {
    // Firebase에서 최신 데이터 가져오기
    const docRef = doc(db, "members", id);
    const docSnap = await getDoc(docRef);

    if (docSnap.exists()) {
      const data = docSnap.data(); // 최신 데이터

      // 모달에 최신 데이터 삽입
      document.getElementById("name").value = data.name;
      document.getElementById("profileImg").value = data.profileImg;
      document.getElementById("intro").value = data.intro;
      document.getElementById("mbti").value = data.mbti;
      document.getElementById("merit").value = data.merit;

      // 비밀번호 필드 초기화
      document.getElementById("password").value = "";

      // 모달 띄우기
      const myModal = new bootstrap.Modal(
        document.getElementById("exampleModal")
      );
      myModal.show();

      // 기존 이벤트 리스너 제거 후 등록
      document
        .getElementById("saveBtn")
        .replaceWith(document.getElementById("saveBtn").cloneNode(true));
      document
        .getElementById("saveBtn")
        .addEventListener("click", async function () {
          const inputPassword = document.getElementById("password").value;

          // 비밀번호 확인
          if (data.password !== inputPassword) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
          }

          const updatedName = document.getElementById("name").value;
          const updatedProfileImg = document.getElementById("profileImg").value;
          const updatedIntro = document.getElementById("intro").value;
          const updatedMbti = document.getElementById("mbti").value;
          const updatedMerit = document.getElementById("merit").value;

          try {
            await updateDoc(doc(db, "members", id), {
              name: updatedName,
              intro: updatedIntro,
              mbti: updatedMbti,
              merit: updatedMerit,
              profileImg: updatedProfileImg,
            });

            alert("수정되었습니다.");

            // DOM 업데이트
            document.querySelector(
              ".pricing-header p"
            ).textContent = `우리 팀의 예쁘고 귀엽고 깜찍하고 잘생기고 멋있는 ${updatedName}을(를) 소개합니다!`;
            document.querySelector(".img-fluid").src = updatedProfileImg;
            document.querySelector(
              ".img-fluid"
            ).alt = `${updatedName} 프로필 이미지`;
            document.querySelector(".mb-content").innerHTML = `
                <p>이름 : ${updatedName}</p>
                <p>한줄 소개 : ${updatedIntro}</p>
                <p>MBTI : ${updatedMbti}</p>
                <p>자신의 장점 : ${updatedMerit}</p>
              `;

            // 모달 닫기
            myModal.hide();
          } catch (error) {
            console.error("수정 중 오류 발생:", error);
            alert("수정에 실패했습니다.");
          }
        });
    } else {
      alert("데이터를 찾을 수 없습니다.");
    }
  } catch (error) {
    console.error("데이터 가져오기 오류:", error);
    alert("데이터를 가져오는 데 실패했습니다.");
  }
});

// 삭제 버튼 클릭 이벤트
document.getElementById("deleteBtn").addEventListener("click", function () {
  const passwordModal = new bootstrap.Modal(
    document.getElementById("passwordModal")
  );
  passwordModal.show();

  // 기존 이벤트 리스너 제거 후 등록
  document
    .getElementById("confirmDeleteBtn")
    .replaceWith(document.getElementById("confirmDeleteBtn").cloneNode(true));
  document
    .getElementById("confirmDeleteBtn")
    .addEventListener("click", async function () {
      const password = document.getElementById("deletePassword").value;
      const memberId = document
        .getElementById("deleteBtn")
        .getAttribute("data-id"); // 문서 ID 가져오기

      try {
        // Firebase에서 비밀번호 가져오기
        const docRef = doc(db, "members", memberId); // 'members'는 Firestore 컬렉션 이름
        const docSnap = await getDoc(docRef);

        if (docSnap.exists()) {
          const data = docSnap.data();
          const correctPassword = data.password; // Firestore에 저장된 비밀번호

          // 비밀번호 검증
          if (!password || password !== correctPassword) {
            alert("비밀번호가 올바르지 않습니다.");
            return;
          }

          // 비밀번호가 일치하면 삭제 수행
          if (confirm("정말로 삭제하시겠습니까?")) {
            await deleteDoc(doc(db, "members", memberId));
            alert("삭제되었습니다.");
            window.location.href = "index.html"; // 메인 페이지로 리다이렉트
          }
        } else {
          alert("해당 멤버 정보를 찾을 수 없습니다.");
        }
      } catch (error) {
        console.error("비밀번호 확인 중 오류 발생:", error);
        alert("비밀번호 확인 중 문제가 발생했습니다.");
      }
    });
});
