'''
Created on Oct 1, 2014

@author: dipanshu
'''
import readData

schedule=readData.GraphReader()
print "WELCOME TO CS AIR \nSelect from options below \n" 
while 1:
    print "1.Enter 1 to see all flight routes"
    print "2.Enter 2 city to see details "
    print"3.Enter 3 to get statistical information"
    print"4.Enter 4 to plan a trip"
    input=raw_input("Please Enter");
    if input=='1' :
        data=schedule.printCities()
        print data
    if input=='2' :
        city=raw_input("Please Enter City Name");
        data=schedule.printCityInfo(city)
        print data
    if input =='3':
        data=schedule.getStats()
        print data
    if input=='4':
        data=schedule.printMap()
        print data
