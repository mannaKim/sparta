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
