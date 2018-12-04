
#include <SPI.h>
#include <Phpoc.h>
#include <pcmConfig.h>
#include <pcmRF.h>
#include <SD.h>
#include <TMRpcm.h>


char server_name[] = "192.168.120.23"; // server IP address or hostname
PhpocClient client;
File myFile;
TMRpcm audio;
const int chipSelect = 4;

void setup() {
  
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }


  Serial.print("Initializing SD card...");

 Phpoc.begin(PF_LOG_SPI | PF_LOG_NET);
 
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
    if (!SD.begin(chipSelect)) {
      Serial.println("Card failed, or not present");
      // don't do anything more:
      while (1);
    }
 

    
    myFile = SD.open("test.txt");
    uint16_t len = myFile.size();
    Serial.write(myFile.read());
    String start_request = "";
    String end_request = "";
    start_request = start_request +
    "\n--AaB03x\n" +
    "Content-Disposition: form-data; name=\"userfile\"; filename=\"CAM.JPG\"\n" +
    "Content-Transfer-Encoding: binary\n\n";
    end_request = end_request + "\n--AaB03x--\n";
    uint16_t full_length;
    full_length = start_request.length() + len + end_request.length();
    Serial.println(full_length);
     client.println("POST /examp1.php HTTP/1.1");
     client.println("Host:192.168.120.23 ");
     
     client.println("Content-Type: multipart/form-data; boundary=AaB03x");
     client.print("Content-Length: ");
     client.println(full_length);
     client.print(start_request);
     
     
     client.write(myFile.read());
    
  
     
    client.print(end_request);
    client.println();
    myFile.close();
     
      
}

void loop() {
  
    
  }
 
  






