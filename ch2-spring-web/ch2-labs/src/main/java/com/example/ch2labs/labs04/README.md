### 가위바위보 게임 API

- **GET** `/rps?user=scissors`
- 서버는 랜덤으로 `rock`, `paper`, `scissors` 중 하나 선택
- 승/패/무 결과를 텍스트 또는 JSON으로 반환

```json
{
  "user": "scissors",
  "server": "rock",
  "result": "You Lose!"
}