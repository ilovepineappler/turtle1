#include <SPI.h>
#include <Phpoc.h>
void setup() {
  Serial.begin(9600);

  Phpoc.begin(PF_LOG_SPI | PF_LOG_NET);

  Serial.println(Phpoc.localIP());  
}

void loop() {

}

