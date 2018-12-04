

#include <Phpoc.h>
#include <pcmConfig.h>
#include <pcmRF.h>
#include <SD.h>
#include <TMRpcm.h>
#define SD_ChipSelectPin 4

char server_name[] = "192.168.130.112"; 
PhpocClient client;
File myFile;
TMRpcm audio;

const int chipSelect = 4;
int full_length;
String start_request = "";
String end_request = "";
int len;


void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
  ;
  }
  SD.begin(SD_ChipSelectPin);
  Serial.print("Initializing SD card...");
  
  audio.CSPin = SD_ChipSelectPin;

  
 if (!SD.begin(4)) {
    Serial.println("initialization failed!");
    while(1);
    }
    
     myFile = SD.open("download.jpg", FILE_READ);
//    if (myFile) {
//    while (myFile.available()) {
//      Serial.write(myFile.read());
// }
     len =myFile.size();
    start_request = start_request +
 "\n--AaB03x\n" +
 "Content-Disposition: form-data; name=\"userfile\"; filename=\"download.jpg\"\n" +
 "Content-Transfer-Encoding: binary\n\n";
     end_request = end_request + "\n--AaB03x--\n"; 



     
     full_length = start_request.length() + len + end_request.length();
     
    if (!myFile) {
    // if the file didn't open, print an error and stop
    Serial.println("error opening ");
 
    }

    Phpoc.begin(PF_LOG_SPI | PF_LOG_NET);
   
    }

void loop() {
    
  if(client.available())
    {
    char c = client.read();
    Serial.print(c);
    }

    if(!client.connected())
    {
    Serial.println("disconnected");
    client.stop();
    delay(5000);
    

    if(client.connect(server_name, 80))
    {
   Serial.println("connected");
 
    Serial.println(full_length);
     client.println("POST /examp2.php HTTP/1.1");
     client.println("Host: 192.168.130.112:80");
     client.println("Content-Type: multipart/form-data; boundary=AaB03x");
     client.println("charset: utf-8");
    client.print("Content-Length: ");
    client.println(full_length);
    client.println();
    client.print(start_request);
   // client.write(myFile.read());
//  static const size_t bufferSize = 1024; // original value 4096 caused split pictures
//  static uint8_t buffer[bufferSize] = {0xFF};
//  while (len) {
//  size_t will_copy = (len < bufferSize) ? len : bufferSize;
//  if (!client.connected()) break;
//  client.write(&buffer[0], will_copy);
//  len -= will_copy; 
int wCount = 0; // For counting # of writes
 int transferBytes = 64;
 

 
      while (len > 0) {

        byte buffer[transferBytes];
              
        int bytesToRead = min(transferBytes, len); // change 32 to 64 for a speedup but may not work with all setups!
        buffer[wCount]=myFile.read();
        wCount += 1;   
       client.write(&buffer[0], bytesToRead);
       Serial.println(buffer[0]);
       Serial.println(wCount);    
      
        if(wCount >= transferBytes) { // Every 2K, give a little feedback so it doesn't appear locked up
          
          Serial.print('.');
          wCount = 0;
        } 
        len -= bytesToRead; 
        //delay(1);
      }
      
      
      client.print(end_request);
      client.println();

      myFile.close();

      Serial.println("Transmission is over");

 } else {

  
 }
}
}
//  
 
