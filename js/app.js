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
// ================ 메인 페이지 시작 ================
// ================================================

$(document).ready(async () => {
  // 멤버 카드 create
  $("#saveBtn").click(async function () {
    let name = $("#name").val();
    let profileImg = $("#profileImg").val();
    let intro = $("#intro").val();
    let mbti = $("#mbti").val();
    let merit = $("#merit").val();
    let password = $("#password").val();

    let doc = {
      name: name,
      profileImg: profileImg,
      intro: intro,
      mbti: mbti,
      merit: merit,
      password: password,
    };

    if (
      name === "" ||
      profileImg === "" ||
      intro === "" ||
      mbti === "" ||
      merit === "" ||
      password === ""
    ) {
      alert("빈칸을 모두 채워주세요!");
      return;
    }

    await addDoc(collection(db, "members"), doc);
    alert("저장완료!");
    window.location.reload();
  });

  $("#closeBtn").click(async function () {
    window.location.reload();
  });

  // 멤버 카드 read
  let docs = await getDocs(collection(db, "members"));
  $("#cards").empty(); // 기존 데이터를 초기화
  docs.forEach(doc => {
    let row = doc.data();
    let id = doc.id; // 문서 ID
    let name = row["name"];
    let profileImg = row["profileImg"];
    let intro = row["intro"];
    let mbti = row["mbti"];
    let merit = row["merit"];

    let temp_html = `
                  <div class="col">
                    <a class="text-decoration-none" href="members_page.html?id=${id}&name=${encodeURIComponent(
      name
    )}&intro=${encodeURIComponent(intro)}&mbti=${encodeURIComponent(
      mbti
    )}&merit=${encodeURIComponent(merit)}&profileImg=${encodeURIComponent(
      profileImg
    )}">
      <div class="card shadow-sm">
        <img
          class="bd-placeholder-img card-img-top"
          width="100%"
          height="225px"
          src="${profileImg}"
        >
        </svg>
        <div class="card-body">
          <p class="card-text">
              ${intro}
          </p>
        </div>
      </div>
    </a>
  </div>
`;
    $("#cards").append(temp_html);
  });
});

// ================================================
// ================ 방명록 시작 ====================
// ================================================

// jQuery로 DOM이 준비되었을 때 실행
$(document).ready(async () => {
  // 방명록 남기기 버튼 클릭 이벤트
  $("#btn_posting").click(async function () {
    let username = $("#username").val();
    let password = $("#password").val();
    let message = $("#message").val();
    let createdAt = new Date();

    let doc = {
      username: username,
      password: password,
      message: message,
      createdAt: createdAt,
    };
    await addDoc(collection(db, "postings"), doc);

    window.location.reload();
  });

  // 방명록 입력란 접기/펼치기
  let boolean = true;
  $("#btn_toggle").click(function () {
    $("#postingbox").toggle();
    if (boolean) {
      $("#btn_toggle").text("펼치기");
      boolean = false;
    } else {
      $("#btn_toggle").text("접기");
      boolean = true;
    }
  });

  // postings 컬렉션 읽어오기
  const postingsRef = collection(db, "postings");
  const q = query(postingsRef, orderBy("createdAt", "desc"));
  let guestBooks = await getDocs(q);
  guestBooks.forEach(book => {
    let row = book.data();

    let username = row["username"];
    let message = row["message"];
    let id = book.id;

    let temp_html = `
          <div class="myCard card-body shadow-sm rounded p-4">
              <h5 class="card-title text-primary fw-bold">${username}</h5>
              <p class="card-text text-muted">${message}</p>
              <input class="cardId" type="hidden" value="${id}">
              <div class="d-flex justify-content-end">
                  <a href="#" class="btn btn-outline-primary btn-sm me-2 cardBtn cardUpdateBtn" data-bs-toggle="modal" data-bs-target="#passwordModal">수정하기</a>
                  <a href="#" class="btn btn-outline-danger btn-sm cardBtn cardDeleteBtn" data-bs-toggle="modal" data-bs-target="#passwordModal">삭제하기</a>   
              </div>
          </div>`;
    $("#guestBook").append(temp_html);
  });

  // 수정하기/삭제하기 버튼 클릭 이벤트 핸들러
  $(".cardBtn").on("click", async function () {
    let type;
    if ($(this).hasClass("cardUpdateBtn")) type = "update";
    else if ($(this).hasClass("cardDeleteBtn")) type = "delete";

    let docId = $(this).parent().siblings(".cardId").val();

    // 비밀번호 확인 버튼 클릭 이벤트 핸들러
    $("#checkPasswordBtn").on("click", async function () {
      let chkPw = $("#chkPw").val();

      const docRef = doc(db, "postings", docId);
      const docSnap = await getDoc(docRef);

      if (docSnap.data().password == chkPw) {
        // 수정인 경우
        if (type == "update") {
          $("#editUsername").val(docSnap.data().username);
          $("#editMessage").val(docSnap.data().message);
          cardUpdate(docId);
        }
        // 삭제인 경우
        else if (type == "delete") {
          cardDelete(docId);
        }
      } else {
        alert("비밀번호가 틀립니다.");
        return;
      }
    });
  });

  // 방명록 수정 함수
  function cardUpdate(docId) {
    $("#editModal").modal("show");
    $("#saveMessageBtn").on("click", async function () {
      let editMessage = $("#editMessage").val();
      const docRef = doc(db, "postings", docId);
      await updateDoc(docRef, {
        message: editMessage,
      });
      alert("수정 완료");
      window.location.reload();
    });
  }

  // 방명록 삭제 함수
  async function cardDelete(docId) {
    const docRef = doc(db, "postings", docId);
    await deleteDoc(docRef);
    alert("삭제 완료");
    window.location.reload();
  }
});

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
    ".member-intro p"
  ).textContent = `MBTI가 전원 IS로 시작하는 8조의 ${name}을(를) 소개합니다!🤓`;
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
