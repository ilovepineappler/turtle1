#include "SPI.h"
#include "Phpoc.h"
PhpocServer server(80);  const int PIN_LED = 2;
void setup() {
  Serial.begin(9600);  
  Phpoc.begin(PF_LOG_SPI | PF_LOG_NET);   pinMode(PIN_LED, OUTPUT);
  server.beginWebSocket("remote_push"); //내부 라이브러리 접속테스트
  Serial.print("WebSocket server address : ");    Serial.println(Phpoc.localIP());  
}
void loop() {
  PhpocClient client = server.available();
  if (client) {
    if (client.available() > 0) {
      char thisChar = client.read();
      if(thisChar == 'A'){        digitalWrite(PIN_LED,  HIGH);         Serial.println("button A press");      }
      if(thisChar == 'B'){        digitalWrite(PIN_LED, LOW);         Serial.println("button B press");        }
    }
  }
}

