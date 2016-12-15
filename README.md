# StockHawk
An android app which displays stocks information of the companies and shows a graphical representation of company’s stocks over time.

## Functionality
 The main screen of the app displays the stock information of the companies such as Google, Yahoo, Apple, Microsoft by using their stock symbols i.e. GOOG, YHOO, AAPL, MSFT on the left of the screen and their stock information on the right of the screen.
 <br/> 
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/home_screen.png" width="200" height = "350">
<br/>
* apart from the stock symbols displayed on the screen, User can add a new stock symbol which they want to keep track off by pressing the '+' button at the bottom of the screen.
<br/> 
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/fab_home_screen.png" width="200" height = "350">
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/new_stock_symbol.png" width="200" height = "350">
<br/>
* Users can tap on the dollar button in the action bar to switch between the numbers and percentages of the stock information on the main screen.
<br/> 
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/dollar_home_screen.png" width="200" height = "350">
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/dollar_percent.png" width="200" height = "350">
<br/>
* Users can see the stock information over a period of time, in the form of a graph by selecting a stock symbol in the main screen and setting the date range in the following screen.
<br/> 
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/symbol_select_home_screen.png" width="200" height = "350">
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/graph_date_screen.png" width="200" height = "350">
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/set_date_screen.png" width="200" height = "350">
 <img src="https://github.com/pa1-teja/StockHawk/blob/master/app/src/main/res/drawable/graph_screen.png" width="200" height = "350">
<br/>

## Project Details 
The app fetches the stock information details from the [Yahoo API](http://meumobi.github.io/stocks%20apis/2016/03/13/get-realtime-stock-quotes-yahoo-finance-api.html) and store those details locally in the SQLite database.

### Dependencies used in the project
Below are the dependencies used in the project.
`com.google.android.gms:play-services-gcm:8.4.0`
`com.squareup.okhttp:okhttp:2.5.0`
`net.simonvt.schematic:schematic:0.6.3`
`com.melnykov:floatingactionbutton:1.2.0`
`com.android.support:design:23.4.0`
`com.android.support:support-v4:23.4.0`
`com.android.support:appcompat-v7:23.1.0`
`com.squareup.retrofit2:retrofit:2.1.0`
`com.github.PhilJay:MPAndroidChart:v2.2.5`

## Install
To install the app on your device, there are three ways possible:

1.) Download the .apk file from the build directory and copy it on to your Android device and Install it.

2.) Import the project into the Android Studio IDE and hit the play button to install the app via ADB.

3.) Follow the commands to install the app via ADB using Command Prompt. 

## Compatibility
The App supports all the Android devices and Tablets which runs Android ICECREAM_SANDWICH and above versions. i.e API 15 and above.
