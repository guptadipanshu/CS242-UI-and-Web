'''
Created on Oct 1, 2014

@author: dipanshu
'''
import json
class Graph:
    '''
    General Graph class that implements graph as dictionary i.e Key,Value
    '''
    def __init__(self):
        self.dict = {}
    
    def add_key_val(self,key,value):
        '''
        Add data into dictionary
        :param key: key 
        :param value: value. Object of Route,NodeData or String
        '''
        self.dict[key]=value
        
    def get_val(self,key):
        '''
        Return the value for key
        :param key:key
        '''
        if self.dict.has_key(key):
            return self.dict[key]
        else:
            return []
    def remove_key(self,key):
        '''
        Removes the key value pair from dictionary
        :param key:
        '''
        if self.dict.has_key(key):
            self.dict.pop(key)
   
    def print_key_val(self):
        '''
        Print the dictionary content in <Key,Value>
        '''
        print self.dict
   
    def print_key(self):
        '''
        Prints all keys
        '''
        print self.dict.keys()
   
    def get_all_key(self):
        '''
        Returns a list of all keys in the dictionary
        '''
        arr= self.dict.keys()  
        return arr
    def encodeJson(self):
        with open('output.json', 'wb') as fp:
            json.dump(self.dict, fp)
    
class NodeData:
    '''
    Contains all information about a city, It will create the value object for dictionary in Graph class
    '''
    def __init__(self):
        '''
        Initialize properties of graph
        '''
        self.continent=""
        self.timezone=""
        self.coord=""
        self.population=""
        self.region=""
        self.code=""
               
class Route:
    '''
    Contains all information about a city's route, It will create the value object for dictionary in Graph class
    '''
    def __init__(self,neighbor,distance):
        self.destination=neighbor
        self.distance=distance