/*
  SD card datalogger

 This example shows how to log data from three analog sensors
 to an SD card using the SD library.

 The circuit:
 * analog sensors on analog ins 0, 1, and 2
 * SD card attached to SPI bus as follows:
 ** MOSI - pin 11
 ** MISO - pin 12
 ** CLK - pin 13
 ** CS - pin 4 (for MKRZero SD: SDCARD_SS_PIN)

 created  24 Nov 2010
 modified 9 Apr 2012
 by Tom Igoe

 This example code is in the public domain.

 */

#include <SPI.h>
#include <SD.h>

File myFile;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  pinMode(53, OUTPUT); // change this to 53 on a mega(don't follow this!!)
  digitalWrite(53, HIGH);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  SD.begin(53);
  Serial.print("Initializing SD card...");

  if (!SD.begin(53)) {
    Serial.println("initialization failed!");
    return;
  }
  Serial.println("initialization done.");

  myFile = SD.open("USS.WAV");

  if (!myFile) {
    // if the file didn't open, print an error:
    Serial.println("error opening file");
    while (1);
  }


  unsigned long start = micros();

  for (int i = 0; i < ((10 * 1024 * 1024) / 512); i++) {
    byte data[512];
    myFile.read(data, sizeof(data));
  }

  unsigned long end = micros();

  Serial.println(end - start);

  myFile.close();
}

void loop() {
}
