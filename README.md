# SpringKotlinTemplate
[![codecov](https://codecov.io/gh/BEOKS/SpringKotlinTemplate/branch/main/graph/badge.svg?token=75JRE4VYJ1)](https://codecov.io/gh/BEOKS/SpringKotlinTemplate)
[![api page](https://img.shields.io/badge/Spring_Rest_Docs-main-007396?logo=Read+the+Docs&logoColor=white)](https://htmlpreview.github.io/?https://github.com/BEOKS/SpringKotlinTemplate/blob/main/src/main/resources/static/docs/api-dcs.html)

새로운 Kotlin 기반의 Spring 프로젝트를 시작할 때 사용할 템플릿 레포지토리입니다.
# Contents
- [1.Introduction](#1introduction)
- [2.Feature](#2feature)
    * [2.1 Test](#21-test)
    * [2.2 Test Coverage](#22-test-coverage)
        + [2.2.1 Exclude Test Code](#221-exclude-test-code)
        + [2.2.2 Test Coverage Report](#222-test-coverage-report)
        + [2.4 Code Coverage Badge](#24-code-coverage-badge)
    * [2.3 Spring Rest Docs](#23-spring-rest-docs)
    * [2.4 CI/CD](#24-ci-cd)
    * [2.5 Convention](#25-convention)


# 1.Introduction
새로운 프로젝트를 개발하고 관리하기 위해서는 테스트, CI/CD, 문서작성 그리고 코드 컨벤션 등 프로그래밍 이외의 작업이 굉장히 많습니다. 프로젝트를 개발할 때마다 이들을 일일이 설정, 자동화하는 것은 매우 번거롭습니다. SpringKotlinTemplate은 이를 해결하기 위해 모범사례를 바탕으로 구성된 프로젝트 템플릿입니다. 새로운 프로젝트를 시작할 때 이 템플릿을 이용해서 개발에 필요한 설정과정을 생략해서 빠르게 개발을 진행할 수 있습니다.

# 2.Feature
## 2.1 Test
이 프로젝트에서는 Kotest와 mockk를 이용합니다.
## 2.2 Test Coverage
테스트 커버리지를 측정하기 위해서 JaCoCo(Java Code Coverage)를 사용합니다. 
새로운 PR을 생성하고 커밋이 갱신될때마다 테스트와 테스트 커버리지 체크가 수행됩니다. 
커버리지 체크가 수행된 후 README.md 의 커버리지 뱃지가 업데이트되며 클릭 시 
커버리지 Report에 접근할 수 있습니다.
### 2.2.1 Exclude Test Code
```SpringBootApplication``` 와 같이 테스트가 필요하지 않는 코드도 테스트 커버리지의 대상이 될 수 있습니다. 이를 차단하기 위해서 ```build.gradle.kts```에서 테스크 커버리지의 예외를 지정할 수 있습니다.
```kotlin
fun ConfigurableFileCollection.excludeSpringBootApplicationClass(){
    setFrom(
        files(files.map {
            fileTree(it) {
                exclude("com/example/springkotlintemplate/SpringKotlinTemplateApplication*")
            }
        })
    )
}
tasks.jacocoTestReport {
    reports {
        //...
    }
    classDirectories.excludeSpringBootApplicationClass()
}
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            classDirectories.excludeSpringBootApplicationClass()
            element = "CLASS"
            //...
        }
    }
}
```
### 2.2.2 Test Coverage Report
[CodeCov](https://about.codecov.io/)를 이용해서 커버리지 리포트를 확인할 수 있습니다. 
Github Action 을 활용해서 CI를 구축하여 자동으로 커버리티 리포트를 생성해 지속적인 업데이트가 가능합니다. 
1. CodeCov 가입
CodeCov 홈페이지에서 GitHub 계정을 통해 가입을 진행합니다.
2. Coverage Report HTML,XML 옵션 활성화

```build.gradle.kt```에서 아래와 같이 HTML,XML 옵션을 활성화합니다.
```kotlin
 tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(true) //For CodeCoverage
        csv.required.set(false)
    }
    //...
}
```
3. Github Action 설정

PR을 생성하거나 브랜치에 푸시가 진행될때 마다 자동으로 코드 커버리지 테스트와 리포트 업데이트를 위한 Github Action을 설정합니다.
```yaml
name: Spring/Kotlin Coverage Test on main

on:
  push:
    branches: [ "main","dev","release" ]
  pull_request:
    branches: [ "main","dev","release" ]

permissions:
  contents: write

jobs:
  test:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
        
    - name: Setup Gradle
      uses: gradle/gradle-build-action@v2
    
    - name: Build
      run: ./gradlew build
    
    - name: Test
      run: ./gradlew test
      
    - name: Create Test Coverage Report
      run: ./gradlew jacocoTestReport
      
    - name: Upload coverage to Codecov
      uses: codecov/codecov-action@v3.1.1
      with:
        file: ./build/reports/jacoco/test/jacocoTestReport.xml
```
### 2.4 Code Coverage Badge
마지막으로 코드 커버리지를 한눈에 파악하기 위한 뱃지를 등록할 수 있습니다.
CodeCov 에서 현재 선택한 Repository 에서 Settings 메뉴에 들어가면 CodeCov Badge를 사용할 수 있습니다.
## 2.3 Spring Rest Docs
API 문서를 자동화 하기 위해서 Spring Rest Docs를 이용합니다.
Swagger의 경우 브라우저에서 API 테스팅이 바로 가능한 것이 매력적이지만, 2020년 8월 14일 이후 업데이트 되지 않아 Deprecated 상태이기 때문입니다. 
## 2.4 CI/CD
CI에는 CircleCI를 사용하며 배포에는 Github Action를 사용한다.
## 2.5 Convention
코틀린 공식 사이트에서 제공하는 컨벤션을 활용합니다. 이 컨벤션은 Intellij 를 사용할 경우 Default 로 적용되어 있습니다.
# 3. More Feature
## 2.1 Spring Doc Docs with GitHub Page
Spring Doc 를 통해서 생성된 웹 페이지 문서를 GitHub Page 를 통해 브라우저에서 바로 확인할 수 있습니다.
## 2.2 Test Coverage Report Visualization
현재 테스트 커버리지를 Report 결과를 GitHub Page 를 통해 브라우저에서 바로 확인할 수 있습니다.


## Reference
1. Best Practices for Unit Testing in Kotlin, https://phauer.com/2018/best-practices-unit-testing-kotlin/

