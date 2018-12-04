#include <SPI.h>
#include <Phpoc.h>

char server_name[] = "192.168.130.112"; // server IP address or hostname
PhpocClient client;
int data_int = 0; // data to post

void setup() {
Serial.begin(9600);
while(!Serial)
;

Serial.println("Sending POST request to web server");

Phpoc.begin(PF_LOG_SPI | PF_LOG_NET);
}

void loop() {
if(client.available())
{
char c = client.read();
Serial.print(c);
}

if(!client.connected())
{
Serial.println("disconnected");
client.stop();
delay(5000); // wait 5 seconds
if(client.connect(server_name, 80))
{
//// user data to post
data_int++;
String data_str = "data=" + String(data_int); // POST format: data= xxx;

client.println("POST /sendvariabletest.php HTTP/1.1"); 
client.print("Host: ");
client.println(server_name);
client.println("Content-Type: application/x-www-form-urlencoded");
client.println("Connection: close");
client.println("User-Agent: Arduino/1.0");
client.print("Content-Length: ");
client.println(data_str.length());
client.println();
//
client.println(data_str); // data to post
//}
//else
//Serial.println("connection failed");
}
}}
