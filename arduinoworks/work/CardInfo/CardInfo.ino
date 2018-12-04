#include <SD.h>

File myFile;

void setup() {
        
        Serial.begin(9600);
        
        
        pinMode(53, OUTPUT);
        
      
       
        
        myFile = SD.open("test.txt");
        
        if (myFile) {
                Serial.println("test.txt:");
                // test 파일의 내용을 시리얼 모니터에 출력한다.
                while (myFile.available()) { 
                Serial.write(myFile.read());
        }
      
        myFile.close();
        
        } else {
              
                Serial.println("error opening test.txt");
        }
}

void loop() {

}
