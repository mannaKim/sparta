# 🧮 java-calculator

---

## 📑 Table of Contents
1. [Project Overview](#-project-overview)
2. [Project Structure](#-project-structure)
3. [Assignment Details](#-assignment-details)
    - [Week 3: 계산기 과제](#week-3-계산기-과제)
    - [Week 4: 계산기 예외 처리](#week-4-계산기-예외-처리)
4. [Features](#-ch-2-계산기-과제-features)
5. [Screenshots](#-screenshots)
6. [Getting Started](#-getting-started)
7. [Contact](#-contact)

---

## 📖 Project Overview

이 프로젝트는 Java를 활용하여 계산기를 단계적으로 구현했습니다.
Java의 기본 문법부터 고급 개념(Enum, 제네릭, 예외 처리 등)을 학습하고 적용한 프로젝트 입니다. 
<br> [_✏️프로젝트 회고_](https://mannakingdom.tistory.com/90).

---

## 🛠️ Project Structure

### 1. calculator
- 클래스 없이 기본적인 연산을 수행할 수 있는 계산기.

### 2. calculator2
- 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기.

### 3. calculator3
- Enum, 제네릭, 람다&스트림을 이해한 계산기.

### 4. online
- [Java 문법 종합반] 과제 패키지:
    - **week3**: 계산기 설계 및 구현 과제.
    - **week4**: 예외 처리 과제.

---

## 📋 Assignment Details
### [Java 문법 종합반] 과제 설명
### Week 3: 계산기 과제
1. **Step 1**: `Calculator` 클래스 구현
    - 사칙연산(`+`, `-`, `*`, `/`)을 수행하는 `calculate`메서드 작성.
    - 제어문(`if`, `switch`)을 사용해 연산자를 처리.

2. **Step 2**: 나머지 연산(%) 추가.

3. **Step 3**: 연산 클래스를 분리
    - `AddOperation`, `SubtractOperation`, `MultiplyOperation`, `DivideOperation` 클래스를 생성.
    - `Calculator` 클래스와 포함 관계를 설정.

4. **Step 4**: 연산 클래스를 추상화
    - `AbstractOperation` 추상 클래스를 도입하여 다형성을 활용.

### Week 4: 계산기 예외 처리
- 사용자 입력 검증과 예외 처리를 위한 `Parser` 클래스
    - 정규식(`Pattern.match()`)을 사용해 입력값을 검증.
    - 입력 오류 시 `BadInputException`을 활용하여 예외 처리.

---

## ✨ CH 2 계산기 과제 Features
- **Lv 1** : calculator
  - 사칙연산(더하기, 빼기, 곱하기, 나누기)을 처리합니다.
- **Lv 2** : calculator2
    - `Calculator` 클래스를 활용하여 연산 기능을 캡슐화했습니다.
      - 연산 결과를 저장할 수 있는 컬렉션 필드를 추가하였습니다.
        - 셍성한 필드는 캡슐화하여 Getter 메서드와 Setter 메서드 구현 
    - App 클래스에서 생성한 Calculate 클래스 활용하도록 했습니다.
- **Lv 3** : calculator3
  - Enum(`OperatorType`)을 활용하여 연산자 타입을 관리합니다.
  - 제네릭을 사용하여 다양한 숫자 타입을 지원합니다.
  - Lambda & Stream을 활용하여 결과를 필터링합니다.
  - 사용자 입력을 검증하여 연산이 올바르게 수행되도록 합니다.

---

## 📷 Screenshots
### calculator 3에 대한 Screenshots
### 첫 번째 숫자 입력을 잘못한 경우 
![](https://github.com/user-attachments/assets/818b0836-77e8-4692-aef0-6260638635ae)
### 두 번째 숫자 입력을 잘못한 경우
![](https://github.com/user-attachments/assets/8f68cef1-5cee-4c10-b7eb-a721e8ce4ca1)
### 나눗셈의 분모로 0을 입력한 경우 & 연산 기호를 잘못 입력한 경우
![](https://github.com/user-attachments/assets/298f52e3-a44f-4af7-ba8e-9e6998ca1217)
### 숫자를 실수로 입력한 경우
![](https://github.com/user-attachments/assets/ffbeb8d8-8423-4409-974b-26ab466b4ae5)

---

## 🚀 Getting Started
1. 이 저장소를 클론합니다:
   ```bash
   git clone https://github.com/mannaKim/java-calculator.git
2. 선호하는 Java IDE(IntelliJ 등)로 프로젝트를 엽니다.
3. 각 패키지의 App 클래스를 실행하여 계산기를 시작합니다.

---

## 🤓 Contact
[@mannaKim](https://github.com/mannaKim)