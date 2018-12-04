#include <SoftwareSerial.h>
int bluetoothTx = 10;  int bluetoothRx = 11; int LED=2;  //LED Pin
SoftwareSerial bluetooth(bluetoothTx, bluetoothRx);
void setup() {
  Serial.begin(9600);         delay(100);
  bluetooth.begin(9600);    pinMode(LED, OUTPUT);
  Serial.println("Start");
}
void loop() {
  if(bluetooth.available()) {
    char cmd = (char)bluetooth.read();
     Serial.println("read data : " + cmd);
    if(cmd == 'n') {
      Serial.println("Red LED ON!");
      digitalWrite(LED, HIGH);        bluetooth.write('n');
    }
    if(cmd == 'f') {
      Serial.println("Red LED OFF");
      digitalWrite(LED, LOW);       bluetooth.write('f');
    }
  }
}

