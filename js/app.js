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
    guestBooks.forEach((book) => {
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
