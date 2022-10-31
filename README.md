# SpringKotlinTemplate
새로운 Kotlin 기반의 Spring 프로젝트를 시작할 때 사용할 템플릿 레포지토리입니다.

## 1.Introduction
새로운 프로젝트를 개발하고 관리하기 위해서는 테스트, CI/CD 등의 기능과 문서와 코드 컨벤션 등의 규칙을 정해야 합니다. 프로젝트를 개발할 때마다 이 요소들을 일일이 설정하는 것은 번거롭습니다. SpringKotlinTemplate은 이를 해결하기 위해 모범사례를 바탕으로 구성된 프로젝트 템플릿입니다. 

## 2.Feature
### 2.1 Test
이 프로젝트에서는 Kotest와 mockk를 이용합니다.
### 2.2 Test Coverage
테스트 커버리지를 측정하기 위해서 JaCoCo(Java Code Coverage)를 사용합니다. 
### 2.3 Swagger
API 문서를 자동화 하기 위해서 Spring Fox(Swagger)를 이용합니다.
### 2.4 CI/CD
CI에는 CircleCI를 사용하며 배포에는 Github Action를 사용한다.
### 2.5 Convention
코틀린 공식 사이트에서 제공하는 컨벤션을 활용합니다. 이 컨벤션은 Intellij 를 사용할 경우 Default 로 적용되어 있습니다.
## 3. More Feature
### 2.1 Swagger Docs with GitHub Page
Swagger 를 통해서 생성된 웹 페이지 문서를 GitHub Page 를 통해 브라우저에서 바로 확인할 수 있습니다.
### 2.2 Test Coverage Report Visualization
현재 테스트 커버리지를 Report 결과를 GitHub Page 를 통해 브라우저에서 바로 확인할 수 있습니다.


## Reference
1. Best Practices for Unit Testing in Kotlin, https://phauer.com/2018/best-practices-unit-testing-kotlin/

