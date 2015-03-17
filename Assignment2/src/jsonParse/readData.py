'''
Created on Oct 1, 2014

@author: dipanshu
'''
import json 
import graph
from collections import deque
import webbrowser

class GraphReader:
    
    #Function that will create the graph
    
    def insertNode(self,jsondata,countryCode,countryDetails):
        '''
        Helper function to help read and create graph
        :param jsondata: filePointer to json file
        :param countryCode: graph with key=code,name=value
        :param countryDetails: graph with key=name value=Node
        '''
        for row in jsondata['metros']:
            key= row['code']
            value=row['name']
            countryCode.add_key_val(key, value)
            countryInfo=graph.NodeData()
            countryInfo.continent=row['continent']
            countryInfo.timezone=row['timezone']
            countryInfo.coord=row['coordinates']
            countryInfo.population=row['population']
            countryInfo.region=row['region']
            countryInfo.code=row['code']
            key=row['name']
            countryDetails.add_key_val(key, countryInfo)   
    
    def insertPath(self,jsondata,countryRoutes,countryCode):  
        '''
        :param jsondata:  filePointer to json file
        :param countryRoutes: graph with key=name value= array of routes and distance
        :param countryCode: graph with key=code,name=value
        '''
        for row in jsondata['routes']:
            #Find the name of country
            countryFrom=countryCode.get_val(row['ports'][0])
            countryTo=countryCode.get_val(row['ports'][1])
            distance=row['distance']
            self.insertRoute(graph, countryRoutes, countryFrom, countryTo, distance)
            self.insertRoute(graph, countryRoutes, countryTo, countryFrom , distance)
            
    def insertRoute(self,graph, countryRoutes, countryFrom, countryTo, distance):
        '''
        
        :param graph: graph
        :param countryRoutes:graph with key=name value= array of routes and distance
        :param countryFrom: key
        :param countryTo: value
        :param distance: value
        '''
        countryPath = graph.Route(countryTo, distance)
        arrlist = countryRoutes.get_val(countryFrom)
        arrlist.append(countryPath)
        countryRoutes.add_key_val(countryFrom, arrlist)         
            
    def readGraph(self,countryCode,countryDetails,countryRoutes):
        '''
        
        :param countryCode: graph with key=code,name=value
        :param countryDetails: graph with key=name,value=node
        :param countryRoutes: graph with key=name,value=distance and destinations
        '''
        with open('datafile.json') as filePointer:
            data=filePointer.read()
            jsondata=json.loads(data)
        self.insertNode(jsondata,countryCode,countryDetails)
        self.insertPath(jsondata,countryRoutes,countryCode)
    
    def __init__(self):
        '''
        Initialization function
        '''
        self.countryCode=graph.Graph()
        self.countryDetails=graph.Graph()
        self.countryRoutes=graph.Graph()
        self.readGraph(self.countryCode,self.countryDetails,self.countryRoutes)
    
    def printCities(self):
        self.countryRoutes.print_key() 
    
    def printCityInfo(self,city):
        '''
        Print the information about given city
        :param city: name of city to get data
        '''
        arrlist= self.countryRoutes.get_val(city)
        info=self.countryDetails.get_val(city)
        for i in range(arrlist.__len__()):
            print str(arrlist[i].destination) +' distance '+ str(arrlist[i].distance)
        if info!=[]:
            data= "Continent "+str(info.continent)+" timezone "+str(info.timezone)+" code "+str(info.code)+"\n"
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
             
    def printMap(self):
        '''
        Function which finds the URL for drawing route
        '''
        sourceCity=raw_input("Please Enter Source City");
        srclist=self.countryDetails.get_val(sourceCity)
        if srclist==[]:
            print "Invalid Source"
            return
        destCity=raw_input("Please Enter Destination City");
        dstlist=self.countryDetails.get_val(destCity)
        if dstlist==[]:
            print "Invalid Destination"
            return
        transitRoute=self.findPath(sourceCity,destCity)
        link="http://www.gcmap.com/mapui?P="+transitRoute
        webbrowser.open(link)
        return link
        
    def findPath(self,sourceCity,destCity):
        '''
        Helper function to Find route using BFS
        :param sourceCity: where the flight will start
        :param destCity: where the flight will end
        '''
        arrlist=self.countryDetails.get_all_key()
        labelGraph=graph.Graph()
        predGraph=graph.Graph()
        for i in range (arrlist.__len__()):
            labelGraph.add_key_val(arrlist[i], "UNEXPLORED")
            predGraph.add_key_val(arrlist[i], "UNEXPLORED")
        queue=deque([sourceCity])
        labelGraph.add_key_val(sourceCity, "EXPLORED")
        while queue.__len__()>0:
            currCity=queue.popleft()  
            neighbor=self.countryRoutes.get_val(currCity)
            #print "explore for"+currCity
            for i in range (neighbor.__len__()):
                if labelGraph.get_val(neighbor[i].destination)=="UNEXPLORED":
                    labelGraph.add_key_val(neighbor[i].destination,"EXPLORED")
                    predGraph.add_key_val(neighbor[i].destination,currCity)
                    queue.append(neighbor[i].destination)
                    # print neighbor[i].destination
        transitRoute=[self.countryDetails.get_val(destCity).code]       
        prevCity=predGraph.get_val(destCity)
        transitRoute.append(self.countryDetails.get_val(prevCity).code)
        while(prevCity!=sourceCity and prevCity!="UNEXPLORED"):
            #print str(prevCity)
            prevCity=predGraph.get_val(prevCity)  
            transitRoute.append(self.countryDetails.get_val(prevCity).code)
        transitRoute.reverse()
        token=""
        #print transitRoute
        i=0
        for i in range(transitRoute.__len__()-2):
            token+=str(transitRoute[i])+'-'+str(transitRoute[i+1])+','
        if i!=0:
            i=i+1
        token+=str(transitRoute[i])+'-'+str(transitRoute[i+1])
        return token
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




    

    