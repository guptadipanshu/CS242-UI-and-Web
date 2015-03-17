'''
Created on Nov 14, 2014

@author: dipanshu
'''
class User:
    '''
    General Graph class that implements graph as dictionary i.e Key,Value
    '''
    def __init__(self):
        self.dict = {}
    
    def add_key_val(self,key,value):
        '''
        Add data into dictionary
        :param key: key 
        :param value: value.
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