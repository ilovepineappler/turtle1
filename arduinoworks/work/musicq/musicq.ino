
#include <SPI.h>
#include <Phpoc.h>
char server_name[] = "192.168.120.41";

unsigned long lastConnectionTime = 0;            
const unsigned long postingInterval = 10L * 1000L; 
PhpocClient client;
int speakerPin = 9;
String title;
int length; 
char names[] = { 'c', 'd', 'e', 'f', 's', 'g', 'a', 'v', 'b', 'C', 'D', 'E' };
int tones[] = { 1915, 1700, 1519, 1432, 1352, 1275, 1136, 1073, 1014, 956, 852, 758 };
char notes[] = { 'c', 'd', 'e', 'f', 's', 'g', 'a', 'v', 'b', 'C', 'D', 'E' };
int beats[] = {1};


void playTone(int tone, int duration) {
  for (long i = 0; i < duration * 1000L; i += tone * 2) {
    digitalWrite(speakerPin, HIGH);
    delayMicroseconds(tone);
    digitalWrite(speakerPin, LOW);
    delayMicroseconds(tone);
  }
}
 
void playNote(char note, int duration) {
  
char names[] = "ggagsed deggsgg ggagsed deggsgg DCbCDbCbabCabagabgagsgasgsesgeseddeggsgg ";
int tones[] = { 2,2,1,1,1,1,4,2,2,2,2,2,2,4,2,2,2,2,1,1,1,1,4,2,2,2,2,2,2,4,2,2,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,2,2,2,2,2,2,4,2,2 };
 
 
  for (int i = 0; i < 8; i++) {
    if (names[i] == note) {
      playTone(tones[i], duration);
    }
  }
}
 
void setup() {
  Serial.begin(9600);
  Phpoc.begin(PF_LOG_SPI | PF_LOG_NET); 
  pinMode(speakerPin, OUTPUT);
}

void loop() {
if(client.available() ) {
     
      char musicnum = client.read();

      switch (musicnum) {
 

    case 1: 
    title= "Ding Dong Merrily on High";  
    length = 26;   
    notes[] = "eeeeeeegcde fffffeeeeddedg";
    beats[]= { 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2};       
       break;
     
    case 2:
    title=Ding dong
    length = 73;
    notes = "ggagsed deggsgg ggagsed deggsgg DCbCDbCbabCabagabgagsgasgsesgeseddeggsgg "; // a space represents a rest
    beats = { 2,2,1,1,1,1,4,2,2,2,2,2,2,4,2,2,2,2,1,1,1,1,4,2,2,2,2,2,2,4,2,2,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,2,2,2,2,2,2,4,2,2 };         
       break;
      
    case 3:
    title=God Rest Ye Merry Gentlemen
    length = 69;
    notes = "ddaagfedcdefga ddaagfedcdefga avgavCDagfdefgfgavaagfedfedgfgavCDagfed";
    beats = { 2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,2,4,2,2,2,2,2,2,4,1,1,2,4,2,2,2,2,2,2,2,2,2,2,8 };         
       break;
      
    case 4:   
    title=O Little Town of Bethlehem
    ength = 71;
    notes = "cfffgagavCavafggfcfffgagavCavafggffaCDCvagfgavCcfagfccfffgagavCavafggf ";
    beats = { 2,2,2,2,2,1,1,1,1,2,2,2,1,1,2,2,6,2,2,2,2,2,1,1,1,1,2,2,2,1,1,2,2,6,1,1,3,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,4,4,2,2,2,2,1,1,1,1,2,2,2,1,1,2,2,6,2 };      
      break;

   case 5:   
   title=While Shephards Watched
   length = 29;
   notes= "faagfvvagaCCbCaDCvagfeagffef ";
   beats= { 2,3,1,2,2,2,2,2,2,2,2,2,2,6,2,3,1,2,2,2,2,2,2,2,2,2,2,6,2 };  
      break;

}
 

for (int i = 0; i < length; i++) {


  int tempo = 300;
    if (notes[i] == ' ') {
      delay(beats[i] * tempo); // rest
    } else {
      playNote(notes[i], beats[i] * tempo);
    }
 
    delay(tempo / 2); 
  }
}
 

}
    
    }


}
void httpRequest() {
 
  client.stop();

  if (client.connect(server_name, 80)) { 
    client.println(title);
  
  } else {
    
  }

   
}









