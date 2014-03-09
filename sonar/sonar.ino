

#define echoPin 7 // Echo Pin
#define trigPin 8 // Trigger Pin
#define LEDPin 13 // Onboard LED

#define buffer_size 8

int maximumRange = 200; // Maximum range needed
int minimumRange = 0; // Minimum range needed
long duration, distance; // Duration used to calculate distance

int readings[buffer_size];
int buffer_index = 0;
int sum = 0;
int outrange_count = 0;


void setup() {
 Serial.begin (9600);
 pinMode(trigPin, OUTPUT);
 pinMode(echoPin, INPUT);
 pinMode(LEDPin, OUTPUT); // Use LED indicator (if required)
}

void loop() {
/* The following trigPin/echoPin cycle is used to determine the
 distance of the nearest object by bouncing soundwaves off of it. */ 
 digitalWrite(trigPin, LOW); 
 delayMicroseconds(2); 

 digitalWrite(trigPin, HIGH);
 delayMicroseconds(10); 
 
 digitalWrite(trigPin, LOW);
 duration = pulseIn(echoPin, HIGH);
 
 //Calculate the distance (in cm) based on the speed of sound.
 distance = duration/58.2;
 
 if (distance >= maximumRange || distance <= minimumRange){
 /* Send a negative number to computer and Turn LED ON 
 to indicate "out of range" */
 outrange_count++;
 if(outrange_count > 5) {
   Serial.println("-1");
   digitalWrite(LEDPin, HIGH); 
 }
 }
 else {
 /* Send the distance to the computer using Serial protocol, and
 turn LED OFF to indicate successful reading. */
 sum -= readings[buffer_index];
 readings[buffer_index] = distance;
 sum += distance;
 buffer_index = (buffer_index + 1) % buffer_size;
 outrange_count = 0;
 if (buffer_index == 0) Serial.println(sum/buffer_size);
 digitalWrite(LEDPin, LOW); 
 }
 
 //Delay 50ms before next reading.
 delay(8);
}
