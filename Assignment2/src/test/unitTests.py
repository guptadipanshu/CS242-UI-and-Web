'''
Created on Oct 2, 2014

@author: dipanshu
'''
import unittest
import jsonParse.readData as readData

class Test(unittest.TestCase):


    def testGraph(self):
        data=readData.GraphReader()
        code=data.getCode("Sydney")
        self.assertEquals("SYD",code)
        code=data.getCode("Invalid")
        self.assertFalse("SYD",code)
    
    def testPrintCities(self):
        data=readData.GraphReader()
        allCities=data.printCities()
        self.assertFalse("HAHAHA",allCities)
    
    def testStatCities(self):
        data=readData.GraphReader()
        allCities=data.printCityInfo("NewYork")
        stats="Manila distance 6243\nJakarta distance 5502\nLos Angeles distance 12051\nContinent Australia timezone 10 code SYDcoordinates {u'S': 34, u'E': 151} population 4475000 region 4"
        self.assertEquals(stats,allCities)
   
    def testStats(self):
        data=readData.GraphReader()
        allStats=data.getStats()
        self.assertFalse("HAHAHA",allStats)
    
    
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()