#include <SPI.h>
#include <WiFi.h>

char ssid[] = "AndroidHotspot6971";      //  연결하실 와이파이 SSID
char pass[] = "00001111";   // 네트워크 보안키

int status = WL_IDLE_STATUS;

WiFiServer server(80);  // 80포트를 사용하는 웹서버 선언

void setup() {
  Serial.begin(9600); 

  if (WiFi.status() == WL_NO_SHIELD) { // 현재 아두이노에 연결된 실드를 확인
    Serial.println("WiFi shield not present"); 
    while (true);  // 와이파이 실드가 아닐 경우 계속 대기
  } 

  // 와이파이에 연결 시도
  while ( status != WL_CONNECTED) { //연결될 때까지 반복
    Serial.print("Attempting to connect to SSID: ");
    Serial.println(ssid);
    status = WiFi.begin(ssid, pass);  // WPA/WPA2 연결
  } 

  server.begin();

  printWifiStatus();  // 연결 성공시 연결된 네트워크 정보를 출력
}


void loop() {
  int flexval = analogRead(A5);
  int ldrval = analogRead(A0);
  // flex 센서와 조도센서에서 값을 가져옴

  WiFiClient client = server.available();  // 들어오는 클라이언트를 수신한다.
  if (client) {  // 클라이언트를 수신 시
    Serial.println("new client");  // 클라이언트 접속 확인 메시지 출력
    boolean currentLineIsBlank = true;

    while (client.connected ()) { 
      if (client.available()) {
        char c = client.read();
        // 문자의 끝을 입력 받으면 http 요청이 종료되고, 답신을 보낼 수 있습니다.
        if (c == '\n' && currentLineIsBlank) {
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");
          client.println("Refresh: 1"); // 1초당 페이지 refresh
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<meta charset=utf-8/>");
          client.print("<meta name=view content=width=device-width, ");
          client.println("initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no />");
          client.println("<html>");
          client.println("<head>");  
          client.println("<title>Many Sensor</title>");
          client.println("</head>");
          client.println("<h1>Many Sensor</h1>");
          client.println("<div data-role=content>");
          client.print("Flex Sensor value : ");
          client.println(flexval);  // flex 센서 값 출력
          client.println("<br>");
          client.println("<br>");
          client.print("Ldr Sensor value : ");
          client.println(ldrval);  // 조도 센서 값 출력
          client.println("<br>");
          client.println("</div>");
          client.println("</body>");
          client.println("</html>");
          break;
        }
        if (c == '\n') { 
          currentLineIsBlank = true;
        }

        else if (c != '\r') {
          currentLineIsBlank = false;
        }
      }
    }
    delay(1);
    client.stop();
    Serial.println("client disonnected");
    // 클라이언트와 연결을 끊는다.
  }
}

void printWifiStatus() {  // 연결된 네트워크 정보 출력
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());
  // 네트워크 SSID 출력

  IPAddress ip = WiFi.localIP(); 
  Serial.print("IP Address: ");
  Serial.println(ip);
  // 네트워크 ip 출력

  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
  // 수신 강도 출력
}
