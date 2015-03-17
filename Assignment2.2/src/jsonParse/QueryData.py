'''
Created on Oct 8, 2014

@author: dipanshu
'''
import readData
import graph  
import webbrowser

class Query: 
    def __init__(self,name):
        self.schedule=readData.GraphReader(name)
        self.countryRoutes=self.schedule.getCountryRoutes()
        self.countryDetails=self.schedule.getCountryDetails()
        self.countryCode=self.schedule.getCountryCode()
    def printCities(self):
        
        cities= self.countryRoutes.get_all_key() 
        cities.sort()
        return cities
        
    def printCityInfo(self,city):
        '''
        Print the information about given city
        :param city: name of city to get data
        '''
        arrlist= self.countryRoutes.get_val(city)
        info=self.countryDetails.get_val(city)
        data=""
        for i in range(arrlist.__len__()):
            data+=""+ str(arrlist[i].destination) +' distance '+ str(arrlist[i].distance)+"\n"
        if info!=[]:
            data+= "Continent "+str(info.continent)+" timezone "+str(info.timezone)+" code "+str(info.code)+"\n"
            data+= "coordinates "+str(info.coord)+" population "+str(info.population)+" region "+str(info.region)
            return data
        else:
            data= "Invalid City"
            return data
     
    def getStats(self):
        '''
        Get the stats for the given DataSet
        '''
        data=""
        data+= self.getLongestFlight(self.countryRoutes)+"\n"
        data+= self.getShortestFlight(self.countryRoutes)+"\n"
        data+= self.getAverageDistance(self.countryRoutes)+"\n"
        data+= self.getBiggestCity(self.countryDetails)+"\n"
        data+= self.getSmallestCity(self.countryDetails)+"\n"
        data+= self.getAverageSize(self.countryDetails)+"\n"
        data+= self.getContinentCity(self.countryDetails)+"\n"
        data+= self.getHubCity(self.countryRoutes)+"\n"
        return data
    
    def getLongestFlight(self,countryRoutes):
        '''
        Get the longest routes
        :param countryRoutes: graph representing routes
        '''
        maxDist=0
        destName=""
        sourceName=""
        keyarr=countryRoutes.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryRoutes.get_val(keyarr[i])
            for j in range (arrlist.__len__()):
                if int(maxDist) < int(arrlist[j].distance):
                    maxDist=int(arrlist[j].distance)
                    destName=str(arrlist[j].destination)
                    sourceName=str(keyarr[i])
        data= "Longest route from "+sourceName+" to "+destName+" distance "+str(maxDist)   
        return data

    def getShortestFlight(self,countryRoutes):
        '''
        Get the shortest flight
        :param countryRoutes: graph representing routes
        '''
        minDist=999999999
        destName=""
        sourceName=""
        keyarr=countryRoutes.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryRoutes.get_val(keyarr[i])
            for j in range (arrlist.__len__()):
                if int(minDist) > int(arrlist[j].distance):
                    minDist=int(arrlist[j].distance)
                    destName=str(arrlist[j].destination)
                    sourceName=str(keyarr[i])
        data= "Shortest route from "+sourceName+" to "+destName+" distance "+str(minDist) 
        return data
                    
    def getAverageDistance(self,countryRoutes):
        '''
        Get the average distance of all routes
        :param countryRoutes: graph representing routes
        '''
        avg=0
        count=0;
        keyarr=countryRoutes.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryRoutes.get_val(keyarr[i])
            for j in range (arrlist.__len__()):
                count=count+1
                avg+=arrlist[j].distance            
        avg=avg/count
        data= "Average Distance of all flights is "+str(avg)
        return data
    
    def getBiggestCity(self,countryDetails):
        '''
        Get the city with maximum population
        :param countryDetails:graph representing city
        '''
        maxPop=0
        sourceName=""
        keyarr=countryDetails.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryDetails.get_val(keyarr[i])
            if int(maxPop) < int(arrlist.population):
                    maxPop=int(arrlist.population)
                    sourceName=str(keyarr[i])
        data= "Maximum population city is "+sourceName+ " With population "+ str(maxPop)
        return data
    
    def getSmallestCity(self,countryDetails):
        '''
        Get the smallest population city
        :param countryDetails:graph representing city
        '''
        minPop=99999999999
        sourceName=""
        keyarr=countryDetails.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryDetails.get_val(keyarr[i])
            if int(minPop) > int(arrlist.population):
                    minPop=int(arrlist.population)
                    sourceName=str(keyarr[i])
        data= "Minimum population city is "+sourceName+ " With population "+ str(minPop)
        return data
    
    def getAverageSize(self,countryDetails):
        '''
        Get the average population 
        :param countryDetails: graph representing city
        '''
        avgPop=0
        count=0
        keyarr=countryDetails.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryDetails.get_val(keyarr[i])
            avgPop+=int(arrlist.population)
            count=count+1
        avgPop=avgPop/count    
        data= "Average population of city we fly is "+str(avgPop)
        return data
    
    def getContinentCity(self,countryDetails):
        '''
        Find city by continent
        :param countryDetails:graph representing city
        '''
        continentGraph=graph.Graph()
        keyarr=countryDetails.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryDetails.get_val(keyarr[i])
            contlist=continentGraph.get_val(arrlist.continent)
            contlist.append(keyarr[i])
            continentGraph.add_key_val(arrlist.continent,contlist)
        keyarr=continentGraph.get_all_key()
        data=""
        for i in range(keyarr.__len__()):
            contlist=continentGraph.get_val(keyarr[i])
            data+= "For continent "+keyarr[i]+" Places visited "+str(contlist)+"\n"
        return data
    
    def getHubCity(self,countryRoutes):
        '''
        Get the city with maximum stops
        :param countryRoutes:graph representing routes
        '''
        maxPlace=0
        hubCity=""
        keyarr=countryRoutes.get_all_key()
        for i in range(keyarr.__len__()):
            arrlist=self.countryRoutes.get_val(keyarr[i])
            if int(maxPlace)< arrlist.__len__():
                maxPlace=arrlist.__len__()
                hubCity=keyarr[i]
        data= "HubCity "+str(hubCity)+" with "+str(maxPlace)+" Connections "  
        return data  
             
    def printMap(self,sourceCity,destCity):
        '''
        Function which finds the URL for drawing route
        '''
        srclist=self.countryDetails.get_val(sourceCity)
        if srclist==[]:
            return "Invalid Source"
       
        dstlist=self.countryDetails.get_val(destCity)
        if dstlist==[]:
            return "Invalid Destination"
            
        transitRoute=self.findPath(sourceCity,destCity)
        token=""
        i=0
        for i in range(transitRoute.__len__()-2):
            token+=str(transitRoute[i])+'-'+str(transitRoute[i+1])+','
        if i!=0:
            i=i+1
        token+=str(transitRoute[i])+'-'+str(transitRoute[i+1])
        link="http://www.gcmap.com/mapui?P="+token
        webbrowser.open(link)
        return link
    
    
    def getMapURL(self,sourceCity,destCity):
        '''
        Function which finds the URL for drawing route
        '''
        srclist=self.countryDetails.get_val(sourceCity)
        if srclist==[]:
            return "Invalid Source"
       
        dstlist=self.countryDetails.get_val(destCity)
        if dstlist==[]:
            return "Invalid Destination"
            
        transitRoute=self.findPath(sourceCity,destCity)
        token=""
        i=0
        for i in range(transitRoute.__len__()-2):
            token+=str(transitRoute[i])+'-'+str(transitRoute[i+1])+','
        if i!=0:
            i=i+1
        token+=str(transitRoute[i])+'-'+str(transitRoute[i+1])
        return token
    
    def getMinVertex(self,queue,distGraph):
        minDist=9999999999
        name=""
        for i in range (self.queue.__len__()):
            if self.distGraph.get_val(self.queue[i])<minDist:
                minDist=self.distGraph.get_val(self.queue[i])
                name=queue[i]
        return name       
            
    def findPath(self,sourceCity,destCity):
        '''
        Helper function to Find route using Dijkstra's
        :param sourceCity: where the flight will start
        :param destCity: where the flight will end
        '''
        arrlist=self.countryDetails.get_all_key()
        predGraph=graph.Graph()
        self.distGraph=graph.Graph()
        self.queue=[]
        self.distGraph.add_key_val(sourceCity, 0)
        for i in range (arrlist.__len__()):
            if arrlist[i]!=sourceCity:
                predGraph.add_key_val(arrlist[i], "UNEXPLORED")
                self.distGraph.add_key_val(arrlist[i], 99999999)
            self.queue.append(arrlist[i])
        while self.queue.__len__()>0:
            minVertex=self.getMinVertex(self.queue,self.distGraph)  
            self.queue.remove(minVertex)
            neighbor=self.countryRoutes.get_val(minVertex)
        
            for i in range (neighbor.__len__()):
                altPath=self.distGraph.get_val(minVertex)+neighbor[i].distance
                if altPath<self.distGraph.get_val(neighbor[i].destination):
                    self.distGraph.add_key_val(neighbor[i].destination, altPath)
                    predGraph.add_key_val(neighbor[i].destination,minVertex)
                    # print neighbor[i].destination
        transitRoute=[self.countryDetails.get_val(destCity).code]       
        prevCity=predGraph.get_val(destCity)
        transitRoute.append(self.countryDetails.get_val(prevCity).code)
        while(prevCity!=sourceCity and prevCity!="UNEXPLORED"):
            prevCity=predGraph.get_val(prevCity)  
            transitRoute.append(self.countryDetails.get_val(prevCity).code)
        transitRoute.reverse()
        return transitRoute
        
        #print transitRoute
       
    
    def getCountryRoutes(self):
        return self.countryRoutes
    def getCountryDetails(self):
        return self.countryDetails
    def getCountryCode(self):
        return self.countryCode
    def getGraph(self):
        return self.schedule
    ##########################Unit Test Helper###################
    def getCode(self,name):
        '''
        For debugging
        :param name: name of city to get data
        '''
        arr=self.countryDetails.get_val(name)
        if arr!=[]:
            return arr.code
        else: 
            return []

