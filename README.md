# 🍔 java-kiosk-project

---

## 📖 Project Overview
Java 문법 실습과 객체 지향 개념을 적용해 키오스크를 구현한 프로젝트입니다.
<br>
<br>
[_✏️프로젝트 회고_](https://mannakingdom.tistory.com)

---

## 📑 Table of Contents
1. [Project Structure](#-project-structure)
2. [Assignment Details](#-assignment-details)
    - [필수 기능](#필수-기능)
    - [도전 기능](#도전-기능)
3. [Features](#-features)
    - [Lv 1: 기본적인 키오스크 프로그래밍](#lv-1-기본적인-키오스크-프로그래밍)
    - [Lv 2: 햄버거 메뉴 관리](#lv-2-햄버거-메뉴-관리)
    - [Lv 3: 키오스크 프로그램 흐름 제어](#lv-3-키오스크-프로그램-흐름-제어)
    - [Lv 4: 메뉴 카테고리와 내역 관리](#lv-4-메뉴-카테고리와-내역-관리)
    - [Lv 5: 캡슐화 적용](#lv-5-캡슐화-적용)
    - [Lv 6: 장바구니 및 구매하기 기능 추가](#lv-6-장바구니-및-구매하기-기능-추가)
    - [Lv 7: Enum, 람다 & 스트림 활용](#lv-7-enum-람다--스트림-활용)
4. [Screenshots](#-screenshots)
5. [Getting Started](#-getting-started)

---

## 🛠️ Project Structure
```
src
└── main
    └── java
        └── com
            └── example
                └── kiosk
                    ├── exceptions
                    │   └── InvalidMenuSelectionException.java
                    ├── level1
                    │   └── Main.java
                    ├── level2
                    │   ├── Main.java
                    │   └── MenuItem.java
                    ├── level3
                    │   ├── Kiosk.java
                    │   ├── Main.java
                    │   └── MenuItem.java
                    ├── level4
                    │   ├── Kiosk.java
                    │   ├── Main.java
                    │   ├── Menu.java
                    │   └── MenuItem.java
                    ├── level5
                    │   ├── Kiosk.java
                    │   ├── Main.java
                    │   ├── Menu.java
                    │   └── MenuItem.java
                    ├── level6
                    │   ├── Cart.java
                    │   ├── Kiosk.java
                    │   ├── Main.java
                    │   ├── Menu.java
                    │   └── MenuItem.java
                    └── level7
                        ├── Cart.java
                        ├── Kiosk.java
                        ├── Main.java
                        ├── Menu.java
                        ├── MenuItem.java
                        └── UserType.java

```
각 `level` 패키지는 과제의 단계별 요구 사항을 반영합니다.
- `level1 ~ level5`: 기본적인 흐름 제어 및 메뉴 관리 구현.
- `level6`: 장바구니와 주문 기능 추가.
- `level7`: 고급 문법(Enum, 람다, 스트림)을 활용한 기능 개선.

점진적으로 객체지향 설계와 고급 자바 기능을 활용하는 방식으로 구현됐습니다.

---

## 📋 Assignment Details
### 필수 기능
- Java의 기초적인 흐름 제어와 객체 지향 설계를 구현합니다.
- Lv 1 ~ Lv 5

### 도전 기능
- 기존 구현에 고급 개념(Enum, 람다 & 스트림)을 적용하여 프로그램의 효율성과 가독성을 개선합니다.
- 클래스 간 연계를 통해 객체 지향 프로그래밍의 기본적인 설계를 익히고, 사용자 입력에 따른 프로그램 흐름 제어와 상태 관리를 학습합니다.
- Lv 6 ~ Lv 7

---

## ✨ Features
### Lv 1: 기본적인 키오스크 프로그래밍
- **기능**:
    - Scanner를 사용해 사용자 입력을 처리하고 간단한 햄버거 메뉴를 출력합니다.
    - 입력된 메뉴 번호에 따라 메뉴를 출력하거나 프로그램을 종료합니다.
    - 잘못된 입력에 대해 예외를 처리합니다.
- **주요 특징**:
  - 사용자 입력에 따른 예외 처리
    - 입력 받은 메뉴가 숫자가 아닌 경우 (`NumberFormatException`)
    - 입력 받은 메뉴가 범위를 벗어나는 경우 (custom exception: `InvalidMenuSelectionException`)
    - 그 외의 경우 (`Exception`)

### Lv 2: 햄버거 메뉴 관리
- **기능**:
    - `MenuItem` 클래스를 생성해 이름, 가격, 설명을 관리합니다.
    - 전체 메뉴 리스트를 출력하고, 특정 메뉴를 선택하면 해당 메뉴의 세부 정보를 출력합니다.
- **주요 특징**:
    - Lv 1에서 `List<String>`으로 관리하던 메뉴를 `MenuItem` 클래스로 개선

### Lv 3: 키오스크 프로그램 흐름 제어
- **기능**:
    - `Kiosk` 클래스를 생성하여 프로그램의 시작과 종료를 관리합니다.
    - 메뉴 카테고리를 추가하고 사용자의 입력에 따라 카테고리를 탐색하도록 구현합니다.
- **주요 특징**:
  - `main`함수에서 관리하던 입력과 반복문 로직을 `Kiosk`의 `start` 메서드로 이전
  - 키오스크의 프로그램 흐름(메뉴 탐색, 종료)를 관리

### Lv 4: 메뉴 카테고리와 내역 관리
- **기능**:
    - `Menu` 클래스를 추가해 메뉴를 카테고리로 관리합니다.
    - 각 카테고리와 메뉴 아이템의 리스트를 출력하고 선택된 메뉴를 상세히 보여줍니다.
- **주요 특징**
  - `Menu` 클래스를 추가하여 카테고리를 관리하고 각 카테고리에 속하는 `MenuItem` 리스트 관리
  - `Kiosk` 클래스에 `List<Menu>`를 필드로 설정해, 카테고리 리스트를 관리
  - 카테고리 출력 및 선택된 카테고리 내 메뉴 출력 기능 구현

### Lv 5: 캡슐화 적용
- **기능**:
    - 모든 클래스의 필드를 캡슐화하여 Getter와 Setter를 통해 접근하도록 수정합니다.
- **주요 특징**:
    - 모든 클래스의 필드를 `private`으로 변경

### Lv 6: 장바구니 및 구매하기 기능 추가
- **기능**:
    - `Cart` 클래스를 생성하여 장바구니에 메뉴를 추가 및 삭제할 수 있도록 구현합니다.
    - 장바구니의 전체 항목과 총 금액을 출력하며, 주문 시 장바구니를 초기화합니다.
- **주요 특징**:
  - `Cart` 클래스에 `HashMap<MenuItem, Integer>`를 필드로 사용하여 장바구니에 담긴 메뉴와 수량을 관리
  - `Kiosk` 클래스에서 주문과 취소 기능 구현

### Lv 7: Enum, 람다 & 스트림 활용
- **기능**:
    - 사용자 유형(Enum)을 정의하여 사용자별 할인율을 적용합니다.
    - 람다와 스트림을 활용해 장바구니에서 특정 메뉴를 필터링하거나 제거합니다.
    - 메뉴 출력 시 스트림을 활용해 정렬된 형태로 출력합니다.
- **주요 특징**:
  - `MenuItem` 클래스
    - `isSoldOut` 필드 추가로 품절 여부 관리
  - `Menu` 클래스
    - 람다와 스트림으로 메뉴 출력 로직 간소화
    - 품절 상태를 출력에 반영
  - `Cart` 클래스
    - 장바구니 품목 중 품절된 상품 자동 삭제
    - 할인율을 적용한 총 금액 계산 기능 추가
  - `UserType` Enum 클래스
    - 사용자 유형 및 할인율 상수화
    - 유효한 사용자 유형 반환
  - `Kiosk` 클래스
    - 사용자 유형을 입력받아 할인율 적용
    - 품절 메뉴 처리
      - 메뉴 출력 시 품절 상태 표시
      - 품절된 메뉴는 장바구니에 담을 수 없도록 제한
      - 주문 전 장바구니에 품절된 상품이 있으면 자동으로 삭제
    - 유효한 입력을 받을 때까지 반복 처리
      - 메뉴 선택, 장바구니 추가, 주문 등 사용자 입력이 유효할 때까지 재입력 요구

---

## 📷 Screenshots
### Lv 7에 대한 Screenshots

### 🖼️ 카테고리 메뉴 출력
![Image](https://github.com/user-attachments/assets/68e6d1b6-0a61-4bc6-90cd-9dc47b01f118)
![Image](https://github.com/user-attachments/assets/29ba5a81-d651-4bb6-8039-08dde4c4a379)
![Image](https://github.com/user-attachments/assets/df9ecadb-7d44-4272-823a-ff9fad9f42dd)

### 🖼️ 메뉴를 장바구니에 담기
메뉴 담기
<br>
![Image](https://github.com/user-attachments/assets/118a4407-08fc-4120-af7d-bc99803114a2)
메뉴 담기 취소
<br>
![Image](https://github.com/user-attachments/assets/f14d1c9a-a037-41c2-8f82-cc6e80648593)
품절된 메뉴 담기
<br>
![Image](https://github.com/user-attachments/assets/5f9b7852-50ef-4927-87f6-c1e7da5ac47c)

### 🖼️ 주문 하기
장바구니 확인: 장바구니에 담긴 메뉴와 합계 금액 출력
<br>
![Image](https://github.com/user-attachments/assets/f4365f9c-4ee9-4c19-8566-5a7b702ddd5d)
장바구니 취소: 장바구니 비우기
<br>
![Image](https://github.com/user-attachments/assets/58cb0d60-d132-42fb-ba1c-833dee7e1fd0)
장바구니에 담은 메뉴가 품절된 경우: 품절된 메뉴 삭제
<br>
![Image](https://github.com/user-attachments/assets/71bc05ac-43f6-4351-928f-2d55358a0ac7)
![Image](https://github.com/user-attachments/assets/d84d5366-8ddc-450e-a15b-dc64434fb596)

### 🖼️ 할인 선택 & 주문 완료
![Image](https://github.com/user-attachments/assets/38dfa58a-6558-49d5-9efd-bcc6ad06d310)

### 🖼️ 유효하지 않은 입력
![Image](https://github.com/user-attachments/assets/706c4d99-2b9f-45d3-b408-9d57f2bab2cc)

---

## 🚀 Getting Started
1. 이 저장소를 클론합니다:
   ```bash
   https://github.com/mannaKim/java-kiosk-project.git
2. 선호하는 Java IDE(IntelliJ 등)로 프로젝트를 엽니다.
3. 각 패키지의 Main 클래스를 실행하여 키오스크를 시작합니다.
