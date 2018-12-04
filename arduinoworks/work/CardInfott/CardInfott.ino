#include <SD.h>

File myFile;

void setup() {
        // Open serial communications and wait for port to open:
        Serial.begin(9600);
        myFile.close();
        Serial.print("Initializing SD card...");
        pinMode(53, OUTPUT);
        
        // SD카드를 사용하기 위해서는 꼭 10번 핀을 출력모드로 설정해야 한다.
        // 아두이노 mega를 쓰시는분은 53번핀으로 바꿔준다.
        if (!SD.begin(53)) {
                Serial.println("initialization failed!");
                return;
        }
        Serial.println("initialization done.");

        // test.txt파일을 읽기모드로 연다.(한번에 한파일만 열수 있다.)
        myFile = SD.open("test.txt", FILE_WRITE);

        // 파일을 여는데 성공했으면 testing 1,2,3 이라는 텍스트를 저장
        if (myFile) {
                Serial.print("Writing to test.txt...");
                myFile.println("testing 1, 2, 3.");
        
                // 파일을 닫아준다.
                myFile.close(); 
                Serial.println("done.");
        
        // 파일이 열리지 않았다면 오류 메시지를 출력        
        } else {
                Serial.println("error opening test.txt");
        }

        // test파일을 다시 열어준다.
        myFile = SD.open("test.txt");
        
        if (myFile) {
                Serial.println("test.txt:");
                // test 파일의 내용을 시리얼 모니터에 출력한다.
                while (myFile.available()) { 
                Serial.write(myFile.read());
        }
        
        //파일을 닫아준다.
        myFile.close();
        
        } else {
                // 파일이 열리지 않았다면 오류 메시지를 출력
                Serial.println("error opening test.txt");
        }
}

void loop() {

}
