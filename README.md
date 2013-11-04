# Bohol Earthquake - Sample Android Mapping App

A sample earthquake mapping app that displays the recent Bohol earthquake. It uses Google Maps Android API v2. 

# Requirements

Java Development Kit (JDK) [Download](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

Android SDK [Download](http://developer.android.com/sdk/index.html)

# Eclipse

## Download Eclipse
Eclipse is packaged with the [Android Developer Tools bundle](http://developer.android.com/sdk/index.html)


## Import the "Google play services lib" project to your workspace
  1. Choose File> Import...
  2. Choose Android > Existing Android Code Into Workspace
  3. Navigate to the your {sdk.dir} > extras > google > google_play_services > libproject
  4. You should see project - "google-play-services_lib"
  6. Press "Finish"
  
## Import the "BoholEarthquake" project to your workspace
  1. Choose File> Import...
  2. Choose Android > Existing Android Code Into Workspace
  3. Navigate to the checked out project
  4. You should see project - "BoholEarthquake"
  6. Press "Finish"
  
## Setup "BoholEarthquake" project dependency to "Google play services"
  1. Right-click on the project root
  2. Choose Properties
  3. On Properties window, click on Android section
  4. Add library and select "google-play-services_lib"
  6. Press "Ok"

## Add your API key
  1. Navigate to AndroidManifest.xml, under the "BoholEarthquake" project.
  2. Click the "AndroidManifest.xml" tab, next to "Instrumentation", to see the XML view.
  3. Replace "your_api_key" with an API key generated using [these instructions](https://developers.google.com/maps/documentation/android/start#the_google_maps_api_key)

## Run the app
  1. Ensure your phone is in plugged in, developer mode enabled and screen unlocked
  2. Press the green arrow, or choose Run > Run
  
## License
  Sample Earthquake Map App - Bohol Earthquake
  Copyright (C) 2013  Rolly Rulete
  
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
