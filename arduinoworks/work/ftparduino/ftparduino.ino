#include <SD.h>
#include <SPI.h>
#include <Ethernet.h>
#include <Phpoc.h>
#define FTPWRITE
#define SD_ChipSelectPin 4


// change to your server
char server_name[] = "192.168.130.120"; 

PhpocClient client;
PhpocClient dclient;

char outBuf[128];
char outCount;

// change fileName to your file (8.3 format!)
char fileName[13] = "3.wav";

void setup()
{
  Serial.begin(9600);

  
  if(SD.begin(4) == 0)
  {
    Serial.println(F("SD init fail"));          
  }
 Phpoc.begin(PF_LOG_SPI | PF_LOG_NET);
   
  
}

void loop()
{
  byte inChar;

  inChar = Serial.read();

  if(inChar == 'f')
  {
    if(doFTP()) Serial.println(F("FTP OK"));
    else Serial.println(F("FTP FAIL"));
  }

  if(inChar == 'r')
  {
    readSD();    
  }

}
 

  



File fh;

byte doFTP()
{
#ifdef FTPWRITE
  fh = SD.open(fileName,FILE_READ);
 
#else
  SD.remove(fileName);
  fh = SD.open(fileName,FILE_WRITE);
 
#endif

  if(!fh)
  {
    Serial.println(F("SD open fail"));
    return 0;    
  }

#ifndef FTPWRITE  
  if(!fh.seek(0))
  {
    Serial.println(F("Rewind fail"));
    fh.close();
    return 0;    
  }
#endif

  Serial.println(F("SD opened"));

  if (client.connect(server_name,21)) {
    Serial.println(F("Command connected"));
  } 
  else {
    fh.close();
    Serial.println(F("Command connection failed"));
    return 0;
  }

  if(!eRcv()) return 0;

  client.println(F("USER test"));

  if(!eRcv()) return 0;

  client.println(F("PASS centoscentos"));



  if(!eRcv()) return 0;

  client.println(F("SYST"));

  if(!eRcv()) return 0;

  client.println(F("Type I"));

  if(!eRcv()) return 0;

  client.println(F("PASV"));

  if(!eRcv()) return 0;

  char *tStr = strtok(outBuf,"(,");
  int array_pasv[6];
  for ( int i = 0; i < 6; i++) {
    tStr = strtok(NULL,"(,");
    array_pasv[i] = atoi(tStr);
    if(tStr == NULL)
    {
      Serial.println(F("Bad PASV Answer"));    

    }
  }

  unsigned int hiPort,loPort;

  hiPort = array_pasv[4] << 8;
  loPort = array_pasv[5] & 255;

  Serial.print(F("Data port: "));
  hiPort = hiPort | loPort;
  Serial.println(hiPort);

  if (dclient.connect(server_name,hiPort)) {
    Serial.println(F("Data connected"));
  } 
  else {
    Serial.println(F("Data connection failed"));
    client.stop();
    fh.close();
    return 0;
  }

#ifdef FTPWRITE 
  client.print(F("STOR "));
  client.println(fileName);
#else
  client.print(F("RETR "));
  client.println(fileName);
#endif

  if(!eRcv())
  {
    dclient.stop();
    return 0;
  }

#ifdef FTPWRITE
  Serial.println(F("Writing"));

  byte clientBuf[64];
  int clientCount = 0;

  while(fh.available())
  {
    
   
    clientBuf[clientCount] = fh.read();

     Serial.println(clientBuf[clientCount]);
   
    clientCount++;

    if(clientCount >63)
    {
      dclient.write(clientBuf,64);
      Serial.println(dclient);
      clientCount = 0;
      
    }
  }

  if(clientCount > 0)
  dclient.write(clientBuf,clientCount);

#else
  while(dclient.connected())
  {
    while(dclient.available())
    {
      char c = dclient.read();
      fh.write(c);      
      Serial.write(c); 
    }
  }
#endif

  dclient.stop();
  Serial.println(F("Data disconnected"));

  if(!eRcv()) return 0;

  client.println(F("QUIT"));

  if(!eRcv()) return 0;

  client.stop();
  Serial.println(F("Command disconnected"));

  fh.close();
  Serial.println(F("SD closed"));
  return 1;
}

byte eRcv()
{
  byte respCode;
  byte thisByte;

  while(!client.available()) delay(1);

  respCode = client.peek();

  outCount = 0;

  while(client.available())
  {  
    thisByte = client.read();    
    Serial.write(thisByte);

    if(outCount < 127)
    {
      outBuf[outCount] = thisByte;
      outCount++;      
      outBuf[outCount] = 0;
    }
  }

  if(respCode >= '4')
  {
    efail();
    return 0;  
  }

  return 1;
}


void efail()
{
  byte thisByte = 0;

  client.println(F("QUIT"));

  while(!client.available()) delay(1);

  while(client.available())
  {  
    thisByte = client.read();    
    Serial.write(thisByte);
  }

  client.stop();
  Serial.println(F("Command disconnected"));
  fh.close();
  Serial.println(F("SD closed"));
}

void readSD()
{
  fh = SD.open(fileName,FILE_READ);

  if(!fh)
  {
    Serial.println(F("SD open fail"));
    return;    
  }

  while(fh.available())
  {
    Serial.write(fh.read());
  }

  fh.close();
}
