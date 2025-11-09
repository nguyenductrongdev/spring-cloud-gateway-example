# Introduction

Sample repo for the Spring Cloud Config technical

# Command

Connect to ws

```shell
curl -i -N -H "Origin: http://127.0.0.1:5500" -H "Connection: Upgrade" -H "Upgrade: websocket" -H "Sec-WebSocket-Version: 13" -H "Sec-WebSocket-Key: test" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb21lb25lQHNvbWV0aGluZy5jb20iLCJpYXQiOjE3NjI2ODkwNzgsInVzZXJfaWQiOiJiNmI2ZGYwMS1iOTU1LTQzYWUtYThlNS0wNjNjOGRjNGNlOTUiLCJleHAiOjE3NjI2OTA4Nzh9.NwFEZspnJw88XL6koM_tObC01tgnIgIM-JrcGEN8zEDETiH7oTFOH0yP_ml7B9xk8ykhMocqHWBBs5pBK_joRA" "http://localhost:8080/ws-chat/ws"
```