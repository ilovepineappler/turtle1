#include <SPI.h>
#include <Phpoc.h>
#include <pcmRF.h>
#include <SD.h>
#include <TMRpcm.h>
const int chipSelect = 4;  
char server_name[] = "192.168.130.112";
PhpocClient client;
File myFile;


                                    
void setup() {
 
  Serial.begin(9600);
  Serial.println("test");
  

  if (SD.begin(4)) {
    Serial.println("found:");
  } else {
    Serial.println("not found !");
    return;
  }
  
  myFile = SD.open("test.txt");
   
  if (myFile) {
    Serial.println("test.txt:");

    // read from the file until there's nothing else in it:
    while (myFile.available()) {
      Serial.write(myFile.read());
    }
    // close the file:
   
  } else {
    // if the file didn't open, print an error:
    Serial.println("error opening test.txt");
  }
 
  
  
 

  
  uint16_t wavlen = myFile.size();
  Serial.print("Storing ");
  Serial.print(wavlen, DEC);
  Serial.print(" byte ");

 
  String start_request = "";
  String end_request = "";
  start_request = start_request + "\n" + "--AaB03x" + "\n" + "Content-Disposition: form-data; name=\"userfile\"; filename=\"CAM.JPG\"" + "\n" + "Content-Type: image/jpeg" + "\n" + "Content-Transfer-Encoding: binary" + "\n" + "\n";  
  end_request = end_request + "\n" + "--AaB03x--" + "\n";

  
  uint16_t extra_length;
  extra_length = start_request.length() + end_request.length();
  Serial.println("Extra length:");
  Serial.println(extra_length);
  
  uint16_t len = wavlen + extra_length;
  
  Phpoc.begin(PF_LOG_SPI | PF_LOG_NET);
 
  Serial.println("Starting connection to server...");


  

   if(client.connect(server_name, 80))
    {
          if(client.available())
         {
          char c = client.read();
          Serial.print(c);
          }

  

         
      Serial.println(F("Connected !"));
      client.println(F("POST /examp2 HTTP/1.1"));
      client.println(F("Host: 192.168.130.112:80"));
      client.println(F("Content-Type: multipart/form-data; boundary=AaB03x"));
      client.print(F("Content-Length: "));
      client.println(len);
      client.print(start_request);
        
        if (myFile) {
        Serial.println("test.txt:");


        byte clientBuf[1024];
  

    
             
        client.write((const uint16_t*)clientBuf,c);            
            // close the file:
        myFile.close();
             }
            delay(1000);   
            client.stop();
          //stopping client
    }
      


      client.print(end_request);
      client.println();

      
  Serial.println("Transmission over");
        }
      }
    // close the file:
  
 
      // Read all the data up to # bytes!
//      byte wCount = 0; // For counting # of writes
//      while (wavlen > 0) {
//        
//        uint8_t *buffer;
//        uint8_t bytesToRead = min(1, wavlen); // change 32 to 64 for a speedup but may not work with all setups!
//        
//        buffer = myFile.read(bytesToRead,bytesToRead);
//        client.write(buffer, bytesToRead);
//    
//        if(++wCount >= 1) { // Every 2K, give a little feedback so it doesn't appear locked up
//          Serial.print('.');
//          wCount = 0;
//        }
//        wavlen -= bytesToRead; 
//        delay(100);
//      }
      
    
  



  
 

void loop() {
 
    
  



  
}
