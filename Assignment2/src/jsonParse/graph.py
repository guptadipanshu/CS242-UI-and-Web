'''
Created on Oct 1, 2014

@author: dipanshu
'''
class NodeData:
    def __init__(self):
        self.continent=""
        self.timezone=""
        self.coord=""
        self.population=""
        self.region=""
        self.code=""
class Graph:
    def __init__(self):
        self.dict = {}
    
    def add_key_val(self,key,value):
        self.dict[key]=value
        
    def get_val(self,key):
        if self.dict.has_key(key):
            return self.dict[key]
        else:
            return []
    def print_key_val(self):
        print self.dict
    def print_key(self):
        print self.dict.keys()
    def get_all_key(self):
        arr= self.dict.keys()  
        return arr
        
class Route:
    def __init__(self,neighbor,distance):
        self.destination=neighbor
        self.distance=distance