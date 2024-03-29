# Driver Riskometer

Soha Glal and Ashraf Khalil

suha.glal@gmail.com,ashraf.khalil@adu.ac.ae

Abu Dhabi University

This project was under the supervison of Dr. Ashraf Khalil

The application measures drivers’ distractions and promotes safe driving behaviour. Driver Riskometer exploits off-the-shelf, sensor-enabled mobile phones, requiring no additional hardware. It was developed using symbian C++ and J2ME.The tools used to develop it include Carbide C++ v2 , S60 SDK and NetBeans IDE.(November 2009 to March 2010). 

## 1.	Abstract
Use of mobile devices while driving has been widely shown to pose a serious safety risk. Various approaches aim at reducing the risk or reducing and even banning the use of devices while driving. Some initiatives have focused on increasing drivers’ awareness of the risk. Along that vein, we present a mobile application that assesses drivers’ personal risk with regard to speeding and cell phone use and raises awareness. Based on data collected during each driving episode, the application categorizes the driver into one of seven ranks, from very safe to very risky, thereby providing drivers a clear understanding of risk associated with specific behaviors: key interaction, number and length of phone calls, and speeding. User studies demonstrated the capabilities of the application in highlighting risky behavior, promoting awareness, and motivating better driving behavior.

## 2.	Introduction 
Road accidents are the number one killer in UAE. It is estimated that road accidents costs UAE a staggering Dh21bl a year [3]. Addressing road accidents is of strategic importance for UAE. Several studies have addressed the risk of using mobile phones while driving [1,2]. Virginia Tech Transportation Institute (VTTI) provides a clear picture of driver distraction and cell phone use under real world driving conditions [1]. In [1], it was shown that those who text while driving are 23 times more likely to crash or nearly crash than those who are not distracted. Comparatively, those who dial phone numbers are 2.8 times more likely to crash or nearly crash while those who talk on a cell phone are about 1.3 times as likely to crash.

![Alt text](/img/Picture1.png?raw=true "Figure 1. Architecture of the Driver Riskometer software.	")

Fig. 1 (a). Architecture of the Driver Riskometer software.	

The main aim of the Driver Riskometer application is to provide personal risk assessment for drivers and make them aware of their speeding and cell phone usage behavior. This is in turn expected to provide users with clear measures by which to reduce their driving risk. Our application assesses risks by tracking three measures: driving speed, the phone calls made and received during driving, and keypad interaction such as texting and emailing while driving. By giving different weight for each measure depending on its risk, usage frequency or magnitude, Riskometer builds a model for the driver’s risk.

## 3.	System Design and Implementation
Driver Riskometer comprises the following software components: Symbian Servers and J2ME midlet (see Figure 1). The Symbian C++ modules are daemons that produce data for related J2ME client methods through socket connections. They include: call log daemon, key presses daemon, accelerometer sensor daemon and daemons watcher daemon. Their functions are, respectively: detecting incoming/outgoing calls and their duration, detecting keypad presses and their duration, polling the accelerometer sensor readings, and starting the other daemons when the user starts Driver Riskometer midlet as well as restarting any daemons that exit while the midlet is still running. 
The accelerometer daemon automatically detects when a user is in a car which removes the burden from the user of having to remember to switch on the application whenever in the car. Once the classifier detects the car pattern it triggers the GPS client to start acquiring car speed, and activates the call log and key presses clients to notify the call log and key presses daemons about the start of a new journey. During the journey the GPS client is also responsible for saving instantaneous speed using the local storage client. At the end of the journey, the following information is stored: journey number, average speed, maximum speed, number of incoming/outgoing calls, call duration, number of key interactions, duration of key interactions, date and time, and journey duration. Using the GUI client, the local storage client, and the risk assessment client the user can view different information about his/her journeys.

### 3.1 The Risk Assessment Client 

![Alt text](/img/Picture2.png?raw=true "Figure 2. Risk status is “Ticking bomb” for level 7.")

Figure 2. Risk status is “Ticking bomb” for level 7.

Our risk assessment algorithm takes into account findings which state that texting is by far the most dangerous task while driving, followed by dialing a number, and finally talking on or listening to a cell phone [1]. The risk assessment client classifies data into journeys, days, and overall .Using the risk assessment algorithm, the overall risk for all the journeys is computed as well as the daily risk. The risk assessment algorithm employs a weighted formula to compute the risk. Every variable in the formula has a risk level and a weight. For each journey and day, the user can view his overall risk level as well as the breakdown of all risk factors that contributed to his overall score. We categorized the risk into seven different levels, one being the safest and seven being the most dangerous driver. A status message is displayed with each category to give more informative feedback for the user. For example level six displays a message saying “Don’t drive in my neighborhood”, level seven displays the message “Ticking bomb” while level two displays the message “You passed the school bus driving test”. User can also view a breakdown of different ranges of speed and the percentage of time the driver was within each range. 

## 4.	Conclusion
We designed and implemented Driver Riskometer, a mobile application that measures and evaluates driver behavior to improve drivers’ awareness and understanding of the risk associated with mobile phone use while driving as well as speeding. One of the main features of our application is that it does not require any hardware components and it is designed to be easily portable to other smart phone platforms. In addition to the self assessment, Driver Riskometer is very suitable for parental monitoring where parents can use it to monitor and assess their teenager on the road. It is worth mentioning that the application runs in the background and it is not designed to be used while driving. It provides information only after the end of the journey. This way we do not add one extra distraction to the driver. Moreover, the application does not record any private information such as the GPS locations, phone numbers, or text messages. The goal is to minimize privacy concerns and encourage further use of the application.

## REFERENCES
[1] Box, S.  New Data from VTTI Provides Insight into Cell Phone Use and Driving Distraction, 2009.
[2] Jane, C.S., Herman, F. H. and William W. H. Cell Phone Use While Driving: Results of a Statewide Survey. TRB 2003 Annual Meeting CD-Rom, 2003. 
[3] The national http://www.thenational.ae/apps/pbcs.dll/article?AID=/20100403/NATIONAL/704029790/1133


