
 const int buzzerPin = 2;
 int note;
 int num=1;
const int notes[] = {
  /*C3*/ 131,   /*D3*/ 147,   /*E3*/ 165,   /*F3*/ 175,   /*G3*/ 196,   /*A3*/ 220, 
  /*B3*/ 247,   /*C4*/ 262
};
void setup() {   }
void loop() {

  tone(buzzerPin, notes[note]);
  delay(2000);
  note= note+num;
  if(note<=0 || note>=7){
    num-=1;
    if (num<=-7){
      num=1;
    }
  }
 
    
  }
  
    
  
 




