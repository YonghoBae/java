---
description: src/inbox/ 의 풀이를 알고리즘 유형 폴더로 분류
---

`src/inbox/` 안의 `.java` 파일을 하나씩 읽고 알고리즘 유형별 폴더로 옮겨라.

1. 파일 주석의 문제 번호/제목과 코드 내용으로 핵심 알고리즘을 판단한다.
2. 기존 폴더(`ls -d src/*/`, inbox 제외) 중 맞는 곳을 고른다. 정말 맞는 게 없을 때만 새 폴더를 만든다. 폴더명은 영어 소문자 snake_case (예: `shortest_path`, `two_pointer`).
3. `git mv src/inbox/X.java src/<유형>/X.java` 로 옮기고, 파일 첫 줄 `package inbox;` 를 `package <유형>;` 으로 바꾼다.
4. 같은 이름 파일이 이미 있으면 옮기지 말고 보고만 한다.
5. 끝나면 `javac -nowarn -encoding UTF-8 -d <임시폴더> $(find src -name '*.java')` 로 컴파일 확인.
6. 결과를 `파일 -> 유형 (판단 근거 한 줄)` 표로 보고한다. 커밋은 하지 않는다.
