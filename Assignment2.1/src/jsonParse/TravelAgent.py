'''
Created on Oct 8, 2014

@author: dipanshu
'''
import graph


class TravelAgent:
    def __init__(self,query):
        '''
        Initialize dataStructure 
        :param query: query object of type QueryData
        '''
        self.agent=query
        self.graph=self.agent.getGraph()
        self.countryRoutes=self.agent.getCountryRoutes()
        self.countryDetails=self.agent.getCountryDetails()
        self.countryCode=self.agent.getCountryCode()
    
    def removeCity(self):
        '''
        Remove the details of the city entered by User
        '''
        print "Enter the name of city to delete information for"
        delCity=raw_input();
        arrlst=self.countryDetails.get_val(delCity)
        
        if arrlst==[] :
            return "Invalid City"
        
        self.countryDetails.remove_key(delCity)
        self.countryRoutes.remove_key(delCity)
        self.countryCode.remove_key(arrlst.code)
       
        "Now remove the city from paths of other cities"
        keyarr=self.countryRoutes.get_all_key()
        for i in range(keyarr.__len__()):
            newlist=[]
            arrlist=self.countryRoutes.get_val(keyarr[i])
            for j in range (arrlist.__len__()):
                if arrlist[j].destination !=delCity:
                    newlist.append(arrlist[j])
            self.countryRoutes.add_key_val(keyarr[i], newlist)
        return "Deleted"
    
    def removeRoute(self):
        '''
        Looks and removes the route entered by user to delete 
        '''
        
        sourceCity=raw_input("Please Enter Source City");
        srclist=self.countryDetails.get_val(sourceCity)
        if srclist==[]:
            return "Invalid Source"
        destCity=raw_input("Please Enter Destination City");
        dstlist=self.countryDetails.get_val(destCity)
        if dstlist==[]:
            return "Invalid Destination"
        newlist=[]
        arrlist=self.countryRoutes.get_val(sourceCity)
        for j in range (arrlist.__len__()):
                if arrlist[j].destination !=destCity:
                    newlist.append(arrlist[j])
        self.countryRoutes.add_key_val(sourceCity,newlist)
        return "Deleted Route"
    
    def addCity(self):
        '''
        Allows users to add City to the dataStructue
        '''
        
        newCity=raw_input("Please Enter New City");
        srclist=self.countryDetails.get_val(newCity)
        if srclist!=[]:
            return "City already exists"
        countryInfo=graph.NodeData()
        countryInfo.continent=raw_input("Please Enter Continent");
        countryInfo.timezone=raw_input("Please Enter Timezone");
        countryInfo.coord=raw_input("Please Enter Coordinates EX:N : 44, W : 80");
        countryInfo.population=raw_input("Please Enter Population");
        countryInfo.region=raw_input("Please Enter Region");
        countryInfo.code=raw_input("Please Enter City Code");   
        self.countryDetails.add_key_val(newCity,countryInfo)
        self.countryCode.add_key_val(countryInfo.code, newCity)
        return "Added Successfully"
    
    def addRoute(self):
        '''
        Adds a route to the dataStructure selected by User
        '''
        sourceCity=raw_input("Please Enter Source City");
        srclist=self.countryDetails.get_val(sourceCity)
        if srclist==[]:
            return "Invalid Source"
        destCity=raw_input("Please Enter Destination City");
        dstlist=self.countryDetails.get_val(destCity)
        if dstlist==[]:
            return "Invalid Destination"
        destDistance=raw_input("Please Enter Destination Distance");
        countryPath = graph.Route(destCity, destDistance)
        arrlist=self.countryRoutes.get_val(sourceCity)
        arrlist.append(countryPath)
        self.countryRoutes.add_key_val(sourceCity,arrlist)

        return "Added"
    
    def editCity(self):
        '''
        Allows user to edit Details of City in the DataStrucute
        '''
        city=raw_input("Please Enter City");
        srclist=self.countryDetails.get_val(city)
        if srclist==[]:
            return "City does not exists"
        countryInfo=graph.NodeData()
        countryInfo.continent=raw_input("Please Enter Continent");
        countryInfo.timezone=raw_input("Please Enter Timezone");
        countryInfo.coord=raw_input("Please Enter Coordinates EX:N : 44, W : 80");
        countryInfo.population=raw_input("Please Enter Population");
        countryInfo.region=raw_input("Please Enter Region");
        countryInfo.code=raw_input("Please Enter City Code");   
        self.countryDetails.add_key_val(city,countryInfo)
        self.countryCode.add_key_val(countryInfo.code, city)
        return "Added Successfully"
        
    def saveData(self):
        '''
        Save the current data set into a json file.
        Calls the graph library to do the encoding
        '''
        self.graph.encodeData(self.countryCode,self.countryDetails,self.countryRoutes)
        return "EncodedData"
        
    def getRouteInfo(self):
        '''
        Get the information about a route
        Call dijstraws algorithm from QueryData to get the shortest route
        '''
        sourceCity=raw_input("Please Enter Source City");
        srclist=self.countryDetails.get_val(sourceCity)
        if srclist==[]:
            return "Invalid Source"
        destCity=raw_input("Please Enter Destination City");
        dstlist=self.countryDetails.get_val(destCity)
        if dstlist==[]:
            return "Invalid Destination"
            
        transitRoute=self.agent.findPath(sourceCity,destCity)
        print "The shortest path followed by this route is"+transitRoute
        trip=0
        totalCost=0
        totalDistance=0
        totalTime=0;
        intialTime=.53323 #v=u+at where a=1406.5

        for i in range(transitRoute.__len__()-1):
            cityA=self.countryCode.get_val(transitRoute[i])
            cityB=self.countryCode.get_val(transitRoute[i+1])
            distanceAB=0
            costPerKm=.35
            arrlst=self.countryRoutes.get_val(cityA)
            for j in range(arrlst.__len__()):
                if arrlst[j].destination==cityB:
                    distanceAB=int(arrlst[j].distance)
                    if trip!=0 and costPerKm >=0:
                        costPerKm=costPerKm-.05
                    totalCost=totalCost+distanceAB*costPerKm
                    trip=trip+1
                    totalDistance=totalDistance+distanceAB
                    if distanceAB<400:
                        acc=(750*750)/(2*(distanceAB/2))
                        time=750/acc
                        totalTime=totalTime+2*time
                    else:
                        totalTime=totalTime+2*intialTime+(distanceAB-400)/750
                    arrlen=arrlst.__len__()
                    layofftime=2-((arrlen-1)*.1)
                    totalTime=totalTime+layofftime
        return "Total cost "+str(totalCost) +" Distance "+str(totalDistance)+" Time "+str(totalTime)
    
    def updateDatabase(self):
        '''
        Allows to update the current dataStructure from new JSON files
        Calls readGraph from readData graph library
        '''
        self.graph.readGraph(self.countryCode,self.countryDetails,self.countryRoutes,"")
        return "Updated"
        
    