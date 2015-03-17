'''
Created on Oct 1, 2014

@author: dipanshu
'''

import TravelAgent
import QueryData

query=QueryData.Query("datafile.json")
agent=TravelAgent.TravelAgent(query)
print "WELCOME TO CS AIR \nSelect from options below \n" 
while 1:
    print "1.Enter 1 to see all flight routes"
    print "2.Enter 2 city to see details "
    print"3.Enter 3 to get statistical information"
    print"4.Enter 4 to plan a trip"
    print"5.Enter if you are a Travel Agent"
    userInput=raw_input("Please Enter");
    if userInput=='1' :
        data=query.printCities()
        print data
    if userInput=='2' :
        city=raw_input("Please Enter City Name");
        data=query.printCityInfo(city)
        print data
    if userInput =='3':
        data=query.getStats()
        print data
    if userInput=='4':
        sourceCity=raw_input("Please Enter Source City");
        destCity=raw_input("Please Enter Destination City");
        data=query.printMap(sourceCity,destCity)
        print data
    if userInput=='5':
        print "1.Enter 1 to remove a city"
        print "2.Enter 2 to remove a route"
        print "3.Enter 3 to Add a city"
        print "4.Enter 4 to Add new Route"
        print "5.Enter 5 to edit Existing city Info"
        print "6.Enter 6 to save Data to disk"
        print "7.Enter 7 to get route info"
        print "8.Enter 8 to UpdateDatabase"
        choice=raw_input();
        if choice=='1':
            print "Enter the name of city to delete information for"
            delCity=raw_input();
            data=agent.removeCity(delCity)
            print data
        if choice=='2':
            destCity=raw_input("Please Enter Destination City");
            sourceCity=raw_input("Please Enter Source City");
            destDistance=raw_input("Please Enter Distance")
            print agent.removeRoute(sourceCity,destCity)
        if choice=='3':
            continet=raw_input("Please Enter Continent");
            timezone=raw_input("Please Enter Timezone");
            coord=raw_input("Please Enter Coordinates EX:N : 44, W : 80");
            population=raw_input("Please Enter Population");
            region=raw_input("Please Enter Region");
            code=raw_input("Please Enter City Code"); 
            city=raw_input("Please Enter City");  
            print agent.addCity(city,continet,timezone,coord,population,region,code)
        if choice=='4':
            sourceCity=raw_input("Please Enter Source City");
            destCity=raw_input("Please Enter Destination City");
            distance=raw_input("Please Enter Distance between City");
            print agent.addRoute(sourceCity,destCity,distance)
        if choice=='5':
            continet=raw_input("Please Enter Continent");
            timezone=raw_input("Please Enter Timezone");
            coord=raw_input("Please Enter Coordinates EX:N : 44, W : 80");
            population=raw_input("Please Enter Population");
            region=raw_input("Please Enter Region");
            code=raw_input("Please Enter City Code"); 
            city=raw_input("Please Enter City");  
            print agent.editCity(city,continet,timezone,coord,population,region,code)
        if choice=='6':
            print agent.saveData()
        if choice=='7':
            sourceCity=raw_input("Please Enter Source City");
            destCity=raw_input("Please Enter Destination City");
            print agent.getRouteInfo(sourceCity,destCity)
        if choice=='8':
            print agent.updateDatabase("")
