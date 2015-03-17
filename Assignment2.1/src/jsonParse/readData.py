'''
Created on Oct 1, 2014

@author: dipanshu
'''
import json 
import graph
from reportlab.pdfbase.pdfdoc import Destination
from fileinput import filename


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
            
    def readGraph(self,countryCode,countryDetails,countryRoutes,fileName):
        '''
        
        :param countryCode: graph with key=code,name=value
        :param countryDetails: graph with key=name,value=node
        :param countryRoutes: graph with key=name,value=distance and destinations
        '''
        if fileName=="":
            name=raw_input("Enter JSON File name like datafile.json")
        else:
            name=fileName
        with open(name) as filePointer:
            data=filePointer.read()
            jsondata=json.loads(data)
        self.insertNode(jsondata,countryCode,countryDetails)
        self.insertPath(jsondata,countryRoutes,countryCode)
    
    def __init__(self,name):
        '''
        Initialization function
        '''
        self.countryCode=graph.Graph()
        self.countryDetails=graph.Graph()
        self.countryRoutes=graph.Graph()
        self.readGraph(self.countryCode,self.countryDetails,self.countryRoutes,name)
    
    def encodeData(self,countryCode,countryDetails,countryRoutes):
        
        print "Starting to encode"
        keys=countryDetails.get_all_key()
        self.data=graph.Graph()
        self.mainDict=graph.Graph()
        allKey={}
        allRoutes={}
        for i in range (keys.__len__()):
            self.temp={}
            self.temp['continent']= countryDetails.get_val(keys[i]).continent
            self.temp['timezone']=countryDetails.get_val(keys[i]).timezone
            self.temp['coordinate']=countryDetails.get_val(keys[i]).coord
            self.temp['population']=countryDetails.get_val(keys[i]).population
            self.temp['region']= countryDetails.get_val(keys[i]).region
            self.temp['code']= countryDetails.get_val(keys[i]).code
            allKey[keys[i]]=self.temp
        self.mainDict.add_key_val("metros", allKey)
        keys=countryRoutes.get_all_key()
        routeKey={}
        for i in range (keys.__len__()):
            self.temp={}
            sourceCode=countryDetails.get_val(keys[i]).code
            destArr=countryRoutes.get_val(keys[i])
            for j in range (destArr.__len__()):
                destName=destArr[j].destination
                destCode=countryDetails.get_val(destName).code
                arr=[sourceCode,destCode]
                self.temp['ports']= arr
                self.temp['distance']=destArr[j].distance
            routeKey[keys[i]]=self.temp
        self.mainDict.add_key_val("Routes", routeKey)
        self.mainDict.encodeJson()
    
    def getCountryRoutes(self):
        return self.countryRoutes
  
    def getCountryDetails(self):
        return self.countryDetails
  
    def getCountryCode(self):
        return self.countryCode
    
    



    

    