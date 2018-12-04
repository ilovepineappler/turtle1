#include <pcmConfig.h>
#include <pcmRF.h>
#include <SD.h>
#include <SPI.h>
#include <TMRpcm.h>
#define SD_ChipSelectPin 53


File myFile;
TMRpcm audio;



void setup() {
  Serial.begin(9600);
  Serial.println("0");
  pinMode(A0, INPUT);
 
 Serial.println("0");
  SD.begin(SD_ChipSelectPin);
  if(!SD.begin(SD_ChipSelectPin)){
    Serial.println("erroe");
  }
   Serial.println("00");
  audio.CSPin = SD_ChipSelectPin;
   Serial.println("1");
      audio.startRecording("1.wav", 16000, A0); 
      
      delay(60000);
      
      Serial.println("2");
      audio.stopRecording("1.wav"); 
      Serial.println("3");
      
     
}

void loop() {
}



