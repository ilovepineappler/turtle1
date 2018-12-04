#include <SPI.h>
#include <Phpoc.h>

 
PhpocServer server(8765);
char server_name[] = "192.168.120.41";
unsigned long lastConnectionTime = 0;            
const unsigned long postingInterval = 10L * 1000L; 
PhpocClient client;
int speakerPin = 9;
String title;
int length; 

char notes[5][74] = { "eeeeeeegcde fffffeeeeddedg",

                    "ggagsed deggsgg ggagsed deggsgg DCbCDbCbabCabagabgagsgasgsesgeseddeggsgg " ,

                    "ddaagfedcdefga ddaagfedcdefga avgavCDagfdefgfgavaagfedfedgfgavCDagfed",

                   "cfffgagavCavafggfcfffgagavCavafggffaCDCvagfgavCcfagfccfffgagavCavafggf ",

                   "faagfvvagaCCbCaDCvagfeagffef "};

int beats[5][74] = {{ 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2},

                  { 2,2,1,1,1,1,4,2,2,2,2,2,2,4,2,2,2,2,1,1,1,1,4,2,2,2,2,2,2,4,2,2,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1,3,1,2,2,2,2,2,2,4,2,2 },

                  { 2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,2,2,4,2,2,2,2,2,2,4,1,1,2,4,2,2,2,2,2,2,2,2,2,2,8 },

                  { 2,2,2,2,2,1,1,1,1,2,2,2,1,1,2,2,6,2,2,2,2,2,1,1,1,1,2,2,2,1,1,2,2,6,1,1,3,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,4,4,2,2,2,2,1,1,1,1,2,2,2,1,1,2,2,6,2 },

                  { 2,3,1,2,2,2,2,2,2,2,2,2,2,6,2,3,1,2,2,2,2,2,2,2,2,2,2,6,2 }

                  };

void playTone(int tone, int duration) { 
  for (long i = 0; i < duration * 1000L; i += tone * 2) { 
    digitalWrite(speakerPin, HIGH); 
    delayMicroseconds(tone);
    digitalWrite(speakerPin, LOW);
    delayMicroseconds(tone);

    Serial.print("not");
  }
}

void playNote(char note, int duration) {
   Serial.print("do");
  char names[] = { 'c', 'd', 'e', 'f', 's', 'g', 'a', 'v', 'b', 'C', 'D', 'E' };
  int tones[] = { 1915, 1700, 1519, 1432, 1352, 1275, 1136, 1073, 1014, 956, 852, 758 };
  Serial.print("do2");
  for (int i = 0; i < 12; i++) {

    Serial.print("do3");
    Serial.print(names[0]);
    Serial.print(note);
    
    if (names[i] == note) {

      Serial.print("do4");
      playTone(tones[i], duration);

     
    }
  }
}
 

void setup() {
  pinMode(speakerPin, OUTPUT);
  Serial.begin(9600);

 

  Phpoc.begin(PF_LOG_SPI | PF_LOG_NET); 
  server.begin();

  Serial.print("server address : ");
  Serial.println(Phpoc.localIP());  
  
 

} 

void loop() {
  PhpocClient client = server.available();
     
      char echoChar = 'E';
      server.write(echoChar);


 
  if(client.available() ) {
  int musicnum = client.read();

  Serial.println(musicnum);
  Serial.println("read Data : " + String(musicnum));

      
      switch (musicnum) {  
        case 49: 
        Serial.println("wyh");
    title= "Ding Dong Merrily on High";  
    length = 26;      
     
       break;     

      case 2:
    title="Ding dong";
    length = 73;
       break;

     case 3:
    title = "God Rest Ye Merry Gentlemen";
    length = 69;
       break;

     case 4:   
    title= "O Little Town of Bethlehem";
    length = 71;
      break;

     case 5:   

   title = "While Shephards Watched";

   length = 29;
      break;
 }


for (int i = 0; i < length; i++) { 
int tempo = 300;
    if (notes[musicnum-1][i] == ' ') {
      delay(beats[musicnum-1][i] * tempo); // rest

      Serial.println(2);
    } else {
      playNote(notes[musicnum-49][i], beats[musicnum-49][i] * tempo);

      Serial.println(3);
    }
    delay(tempo / 2); 
  }
}
}


