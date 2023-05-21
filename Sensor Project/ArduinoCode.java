#define BLYNK_TEMPLATE_ID "TMPL3bdNKdVeK"
#define BLYNK_TEMPLATE_NAME "IoT Based Noise Pollution Monitoring"
#define BLYNK_AUTH_TOKEN "rBeqmu70iOILCSV4shJ_i9zpyx3vClXF"

#define BLYNK_PRINT Serial
#include <WiFi.h>
#include <WiFiClient.h>
#include <BlynkSimpleEsp32.h>

int sound_sensor=34;
char auth[] = "rBeqmu70iOILCSV4shJ_i9zpyx3vClXF";

char ssid[] = "Sanchit";  
char pass[] = "123456788";  

BlynkTimer timer;

void sendSensor()
{
  
  int sound_value=analogRead(sound_sensor);
  Serial.print("Sound sensor data : ");

  Serial.print(sound_value);
  Serial.print("\nNoise : ");
  if(sound_value <= 2000){
    Serial.print("\sNormal\n");
  }else if (sound_value <= 2500){
    Serial.print("Moderate\n");
  }else if (sound_value > 2500){
    Serial.print("High");
  }
  Serial.print("\n");
  delay(1000);
  
  
    Blynk.virtualWrite(V0, sound_value);
    delay(500);
}
void setup()
{   
  
   Serial.begin(9600);

  Blynk.begin(auth, ssid, pass);
  timer.setInterval(100L, sendSensor);
 
  }

void loop()
{
  Blynk.run();
  timer.run();
 }