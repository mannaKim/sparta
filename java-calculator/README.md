# 🧮 java-calculator

---

## 📖 Project Overview

이 프로젝트는 Java를 활용하여 계산기를 단계적으로 구현했습니다.
Java의 기본 문법부터 고급 개념(Enum, 제네릭, 예외 처리 등)을 학습하고 적용한 프로젝트 입니다.
<br>
<br>
[_✏️프로젝트 회고_](https://mannakingdom.tistory.com/90).

---

## 📑 Table of Contents
1. [Project Structure](#-project-structure)
2. [Assignment Details](#-assignment-details)
    - [Week 3: 계산기 과제](#week-3-계산기-과제)
    - [Week 4: 계산기 예외 처리](#week-4-계산기-예외-처리)
3. [Features](#-ch-2-계산기-과제-features)
    - [Lv 1: calculator](#lv-1-calculator)
    - [Lv 2: calculator2](#lv-2-calculator2)
    - [Lv 3: calculator3](#lv-3-calculator3)
4. [Screenshots](#-screenshots)
5. [Getting Started](#-getting-started)
6. [Contact](#-contact)

---

## 🛠️ Project Structure
```
src
└── main
    └── java
        └── com
            └── example
                ├── calculator
                │   └── Calculator.java
                ├── calculator2
                │   ├── App.java
                │   └── Calculator.java
                ├── calculator3
                │   ├── App.java
                │   ├── ArithmeticCalculator.java
                │   └── OperatorType.java
                └── online
                    ├── week3
                    │   └── calculator
                    │       ├── AbstractOperation.java
                    │       ├── AddOperation.java
                    │       ├── Calculator.java
                    │       ├── DivideOperation.java
                    │       ├── Main.java
                    │       ├── MultiplyOperation.java
                    │       └── SubstractOperation.java
                    └── week4
                        └── calculator
                            ├── AbstractOperation.java
                            ├── AddOperation.java
                            ├── BadInputException.java
                            ├── Calculator.java
                            ├── CalculatorApp.java
                            ├── DivideOperation.java
                            ├── Main.java
                            ├── MultiplyOperation.java
                            ├── Parser.java
                            └── SubstractOperation.java
```

### 1. calculator
- 클래스 없이 기본적인 연산을 수행할 수 있는 계산기.

### 2. calculator2
- 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기.

### 3. calculator3
- Enum, 제네릭, 람다&스트림을 이해한 계산기.

### 4. online
- [Java 문법 종합반] 과제 패키지:
    - **week3.calculator**: 계산기 설계 및 구현 과제.
    - **week4.calculator**: 예외 처리 과제.

---

## 📋 Assignment Details
### [Java 문법 종합반] 과제 설명
### Week 3: 계산기 과제
1. **Step 1**: `Calculator` 클래스 구현
    - 사칙연산(`+`, `-`, `*`, `/`)을 수행하는 `calculate`메서드 작성.
    - 제어문(`if`, `switch`)을 사용해 연산자를 처리.

2. **Step 2**: 나머지 연산(%) 추가.

3. **Step 3**: 연산 클래스를 분리
   ![image](https://github.com/user-attachments/assets/8f946655-2a13-4220-94ec-09a38426664c)
    - `AddOperation`, `SubtractOperation`, `MultiplyOperation`, `DivideOperation` 클래스를 생성.
    - `Calculator` 클래스와 포함 관계를 설정.

4. **Step 4**: 연산 클래스를 추상화
   ![image](https://github.com/user-attachments/assets/6d0196ac-4272-4018-b8eb-eed8d9a669bf)
    - `AbstractOperation` 추상 클래스를 도입하여 다형성을 활용.

### Week 4: 계산기 예외 처리
- 사용자 입력 검증과 예외 처리를 위한 `Parser` 클래스
    - 정규식(`Pattern.match()`)을 사용해 입력값을 검증.
    - 입력 오류 시 `BadInputException`을 활용하여 예외 처리.

---

## ✨ CH 2 계산기 과제 Features
### Lv 1: calculator
- **기능**:
    - 사용자로부터 두 개의 양의 정수(0 포함)와 사칙연산 기호를 입력받아 계산을 수행합니다.
    - 사칙연산 결과를 출력하고, 잘못된 입력(연산 기호, 0으로 나눗셈 등)에 대한 오류 메시지를 제공합니다.
    - 사용자가 "exit"를 입력하기 전까지 무한 반복으로 계산할 수 있습니다.
- **주요 특징**:
    - `if`와 `switch`를 활용한 연산 처리.
    - 나눗셈의 분모가 0일 경우에 대한 예외 처리.
    - "exit" 입력을 통한 종료.

### Lv 2: calculator2
- **기능**:
    - 사칙연산 결과를 저장하고 관리하는 `Calculator` 클래스를 추가했습니다.
    - 연산 결과를 저장하는 컬렉션 타입 필드(`List<Integer>`)와 캡슐화를 적용한 Getter/Setter 메서드를 구현했습니다.
    - 연산 결과 리스트의 가장 오래된 데이터를 삭제하는 `removeResult` 메서드를 제공합니다.
- **주요 특징**:
    - App 클래스에서 `Calculator` 객체를 활용해 계산 기능 수행.
    - 리스트에 연산 결과 저장, 관리, 삭제 기능 제공.

### Lv 3: calculator3
- **기능**:
    - 제네릭을 활용해 다양한 숫자 타입(`Number` 하위 클래스)을 지원합니다.
    - `OperatorType` Enum 클래스를 통해 연산자 타입 관리 및 각 연산별 메서드를 구현했습니다
    - Lambda & Stream을 활용해 입력값보다 큰 연산 결과를 필터링하고 출력합니다.
    - 최대 5개의 연산 결과를 저장하며, 이를 초과할 경우 가장 오래된 결과를 삭제합니다.
    - 사용자 입력을 검증하여 연산이 올바르게 수행되도록 합니다.
- **주요 특징**:
    - `ArithmeticCalculator` 클래스
      - `calculate` 메서드를 통해 제네릭 숫자 타입으로 연산을 수행.
      - 연산 결과를 저장하고 관리하는 `List` 필드 제공.
      - `filterResultsGreaterThan` 메서드로 Lambda와 Stream을 활용해 조건에 맞는 결과 출력.
    - `OperatorType` Enum
      - 사칙연산(`+`, `-`, `*`, `/`) 및 이모지 연산자(`➕`, `➖`, `✖`, `➗`) 지원.
      - 각 연산자에 대해 추상 메서드(`operate`) 구현.

---

## 📷 Screenshots
### calculator3에 대한 Screenshots
### 🖼️ 첫 번째 숫자 입력을 잘못한 경우 
![](https://github.com/user-attachments/assets/818b0836-77e8-4692-aef0-6260638635ae)
### 🖼️ 두 번째 숫자 입력을 잘못한 경우
![](https://github.com/user-attachments/assets/8f68cef1-5cee-4c10-b7eb-a721e8ce4ca1)
### 🖼️ 나눗셈의 분모로 0을 입력한 경우 & 연산 기호를 잘못 입력한 경우
![](https://github.com/user-attachments/assets/298f52e3-a44f-4af7-ba8e-9e6998ca1217)
### 🖼️ 숫자를 실수로 입력한 경우
![](https://github.com/user-attachments/assets/ffbeb8d8-8423-4409-974b-26ab466b4ae5)

---

## 🚀 Getting Started
1. 이 저장소를 클론합니다:
   ```bash
   git clone https://github.com/mannaKim/java-calculator.git
2. 선호하는 Java IDE(IntelliJ 등)로 프로젝트를 엽니다.
3. 각 패키지의 App 클래스를 실행하여 계산기를 시작합니다.
