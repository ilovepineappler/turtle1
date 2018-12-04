#include <SPI.h>
#include <Phpoc.h>
#include <pcmRF.h>
#include <SD.h>
#include <TMRpcm.h>
const int chipSelect = 4;  
char server_name[] = "192.168.130.112";
PhpocClient client;
File myFile;
uint16_t len;
String start_request;
String end_request;
                                    
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
   
//  if (myFile) {
//    Serial.println("test.txt:");
//
//    // read from the file until there's nothing else in it:
//    while (myFile.available()) {
//      Serial.write(myFile.read());
//    }
//    // close the file:
   
//  } else {
//    // if the file didn't open, print an error:
//    Serial.println("error opening test.txt");
//  }
 
  
  
 

  
  uint16_t wavlen = myFile.size();
  Serial.print("Storing ");
  Serial.print(wavlen, DEC);
  Serial.print(" byte ");

 
  start_request = "";
  end_request = "";
  start_request = start_request + "\n" + "--AaB03x" + "\n" + "Content-Disposition: form-data; name=\"userfile\"; filename=\"CAM.JPG\"" + "\n" + "Content-Type: image/jpeg" + "\n" + "Content-Transfer-Encoding: binary" + "\n" + "\n";  
  end_request = end_request + "\n" + "--AaB03x--" + "\n";

  
  uint16_t extra_length;
  extra_length = start_request.length() + end_request.length();
  Serial.println("Extra length:");
  Serial.println(extra_length);
  
  len = wavlen + extra_length;
  
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
      Serial.print(start_request);
        if (myFile) {
        Serial.println("in if(myFile):");


        byte clientBuf[150];
        int clientCount = 0;
        int readbytes = 0;

    // read from the file until there's nothing else in it:
            while (myFile.available()) {
              Serial.println("while myFile");
            clientBuf[clientCount] = myFile.read();
            Serial.println(clientBuf[clientCount]);
            client.write(clientBuf[clientCount]);
            Serial.print(clientCount);
          
            clientCount++;
           
            delay(1000); 
              if(clientCount > 1023)
              {
                Serial.println("clientCount is larger than 1023.");
                // Serial.println("Packet");
                client.write(clientBuf,sizeof(clientBuf));
                clientCount = 0;
                
                
              }
            
              
          }
           while (!myFile.available()) {
              client.println(end_request);
           }
             
    }
      


      myFile.close();
      client.println();
      client.stop();
      
  Serial.println("Transmission over");
        }
      }
    

  

void loop() {
 
  
      
    
  


}
  
   
  



  
