'''
Created on Oct 15, 2014

@author: dipanshu
'''
import Tkinter as ttk
from Tkinter import *
import ttk as ttk2

import urllib
from PIL import Image   
import TravelAgent
import QueryData


def getStats(*args):
    '''
    Get statistics on the GUI for the sourceCountry
    '''
    queryCountry= source.get()
    info= query.printCityInfo(queryCountry)
    resultsContents.set(info)

def getAllStats(*args):  
    '''
    Get statistics on the GUI for the entire Dataset
    '''
    info= query.getStats()
    resultsContents.set(info)

def removeCity(*args):  
    '''
    Remove all information on the GUI for the sourceCountry
    '''
    queryCountry= source.get()
    info=agent.removeCity(queryCountry)
    resultsContents.set(info)
    data=query.printCities()
    dest['values'] = data
    source['values'] = data

def removePath(*args):  
    '''
    Remove path information on the GUI for the sourceCountry destination
    '''
    queryCountry= source.get()
    finalCountry= dest.get()
    info=agent.removeRoute(queryCountry,finalCountry)
    resultsContents.set(info)
    data=query.printCities()
    dest['values'] = data
    source['values'] = data
    
def updateSet(*args):  
    '''
    Update the database, user needs to enter the file in textbox
    '''
    queryCountry= userInput.get()
    info=agent.updateDatabase(queryCountry)
    resultsContents.set(info)
    data=query.printCities()
    dest['values'] = data
    source['values'] = data   

def addRoute(*args):  
    '''
    Add a route between source and destination, user needs to enter distance 
    '''
    queryCountry= source.get()
    finalCountry= dest.get()
    distance=userInput.get()
    info=agent.addRoute(queryCountry,finalCountry,distance)
    resultsContents.set(info) 

def RouteStats(*args):  
    '''
    Get the statistics for the route between source and destination
    '''
    queryCountry= source.get()
    finalCountry= dest.get()
    info=agent.getRouteInfo(queryCountry,finalCountry)
    resultsContents.set(info) 

def SaveData(*args):  
    '''
    Save the state of system on disk
    '''
    info=agent.saveData()
    resultsContents.set(info) 
    
def showMap(*args):  
    '''
    Show the map with the route
    '''
    queryCountry= source.get()
    finalCountry= dest.get()
    info=query.getMapURL(queryCountry,finalCountry)
    info="http://www.gcmap.com/map?P="+info+"&MS=wls&MR=120&MX=720x360&PM=*"
    urllib.urlretrieve(info, "image2.gif")
    resultsContents.set(info) 
    img = Image.open('image2.gif')
    img.show()
    

def editCity(*args):  
    '''
    Edit a city , user needs to enter data 
    '''
    timezoneLabel.grid(row=8,column=0)
    userEntry = ttk.Entry(root, width=10,textvariable=timezone)
    userEntry.grid(row=8,column=1)
    
    coordLabel.grid(row=9,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=coord)
    userEntry.grid(row=9,column=1)
    
    populationLabel.grid(row=10,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=population)
    userEntry.grid(row=10,column=1)
    
    regionLabel.grid(row=11,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=region)
    userEntry.grid(row=11,column=1)
    
    codeLabel.grid(row=12,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=code)
    userEntry.grid(row=12,column=1)
    buttonSubmitEdit.grid(row=15,column=0)
    
    contientLabel.grid(row=13,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=continet)
    userEntry.grid(row=13,column=1)
    
def addCity(*args):  
    '''
    Edit a city , user needs to enter data 
    '''
    timezoneLabel.grid(row=8,column=0)
    userEntry = ttk.Entry(root, width=10,textvariable=timezone)
    userEntry.grid(row=8,column=1)
    
    coordLabel.grid(row=9,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=coord)
    userEntry.grid(row=9,column=1)
    
    populationLabel.grid(row=10,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=population)
    userEntry.grid(row=10,column=1)
    
    regionLabel.grid(row=11,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=region)
    userEntry.grid(row=11,column=1)
    
    codeLabel.grid(row=12,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=code)
    userEntry.grid(row=12,column=1)
    
    
    contientLabel.grid(row=13,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=continet)
    userEntry.grid(row=13,column=1)  
     
    cityLabel.grid(row=14,column=0)
    userEntry = ttk.Entry(root, width=10, textvariable=city)
    userEntry.grid(row=14,column=1)
    
    buttonSubmitAdd.grid(row=15,column=1)  
    #agent.editCity(continet,timezone,coord,population,region,code)
def submitEdit(*args):
    info=agent.editCity(source.get(),continet.get(),timezone.get(),coord.get(),
                   population.get(),region.get(),code.get())
    resultsContents.set(info) 

def submitAdd(*args):
    info=agent.addCity(city.get(),continet.get(),timezone.get(),coord.get(),
                   population.get(),region.get(),code.get())
   
    data=query.printCities()
    print data
    dest['values'] = data
    source['values'] = data 
    resultsContents.set(info)  

'''
Main UI
'''


root = ttk.Tk()
root.title("CS AIR 2.2")
frame = ttk.Frame(root)

query=QueryData.Query("datafile.json")
agent=TravelAgent.TravelAgent(query)
data=query.printCities()

sourceCountry = StringVar()
source = ttk2.Combobox(root, textvariable=sourceCountry)
source.grid(row=0)
source.set("Source")
source.bind('<<ComboboxSelected>>', "")
source['values'] = data

destCountry = StringVar()
dest = ttk2.Combobox(root, textvariable=destCountry)
dest.grid(row=0,column=1)
dest.set("Destination")
dest.bind('<<ComboboxSelected>>', "")
dest['values'] = data

buttonStats = ttk.Button(root, text='Get Statistical Information', command=getStats)
buttonStats.grid(row=1,column=0)

buttonAllStats = ttk.Button(root, text='Get ALLStatistical Information', command=getAllStats)
buttonAllStats.grid(row=1,column=1)

buttonRemoveCity = ttk.Button(root, text='Remove City', command=removeCity)
buttonRemoveCity.grid(row=2,column=0)

buttonRemoveRoute = ttk.Button(root, text='Remove Route Src-Dest', command=removePath)
buttonRemoveRoute.grid(row=2,column=1)

buttonUpdate = ttk.Button(root, text='Update Database', command=updateSet)
buttonUpdate.grid(row=4,column=1)

buttonAddRoute = ttk.Button(root, text='Add Route', command=addRoute)
buttonAddRoute.grid(row=4,column=0)

buttonRouteInfo = ttk.Button(root, text='Route Info', command=RouteStats)
buttonRouteInfo.grid(row=5,column=0)

buttonSave = ttk.Button(root, text='Save Changes', command=SaveData)
buttonSave.grid(row=5,column=1)

buttonMap = ttk.Button(root, text='Display Map', command=showMap)
buttonMap.grid(row=6,column=1)

buttonMap = ttk.Button(root, text='Edit City', command=editCity)
buttonMap.grid(row=6,column=0)

buttonMap = ttk.Button(root, text='Add City', command=addCity)
buttonMap.grid(row=7,column=0)

buttonSubmitEdit = ttk.Button(root, text='Submit EDIT', command=submitEdit)
buttonSubmitAdd = ttk.Button(root, text='Submit ADD', command=submitAdd)

userInput = StringVar()

textLabel = ttk.Label(root)
inputContents = StringVar()
textLabel['textvariable'] = inputContents
inputContents.set('Enter Update/New Route')
textLabel.grid(row=3,column=0)
userEntry = ttk.Entry(root, width=10, textvariable=userInput)
userEntry.grid(row=3,column=1)

label = ttk.Label(root)
resultsContents = StringVar()
label['textvariable'] = resultsContents
resultsContents.set('Info')
label.grid(row=17)


contientLabel = ttk.Label(root)
continet = StringVar()
continentContents = StringVar()
contientLabel['textvariable'] = continentContents   
continentContents.set('Contient')

timezoneLabel = ttk.Label(root)
timezone = StringVar()
timezoneContents = StringVar()
timezoneLabel['textvariable'] = timezoneContents   
timezoneContents.set('timezone')

coordLabel = ttk.Label(root)
coord = StringVar()
coordContents = StringVar()
coordLabel['textvariable'] = coordContents   
coordContents.set('Coord')

populationLabel = ttk.Label(root)
population = StringVar()
populationContents = StringVar()
populationLabel['textvariable'] = populationContents   
populationContents.set('population')

regionLabel = ttk.Label(root)
region = StringVar()
regionContents = StringVar()
regionLabel['textvariable'] = regionContents   
regionContents.set('Region')


codeLabel = ttk.Label(root)
code = StringVar()
codeContents = StringVar()
codeLabel['textvariable'] = codeContents   
codeContents.set('Code')

cityLabel = ttk.Label(root)
city = StringVar()
cityContents = StringVar()
cityLabel['textvariable'] = cityContents   
cityContents.set('City Name')
'''
image = PhotoImage(file="image.gif")
imagelabel = ttk.Label(root)
imagelabel['image'] = image
imagelabel.focus()
imagelabel.grid(row=18)
'''

frame.mainloop()
