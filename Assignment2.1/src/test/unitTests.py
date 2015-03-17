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
        allCities=data.printCityInfo("New York")
        stats="Madrid distance 5786\nLondon distance 5579\nWashington distance 334\nToronto distance 568\nContinent North America timezone -5 code NYC\ncoordinates {u'W': 74, u'N': 41} population 22200000 region 3"
        self.assertEquals(stats,allCities)
   
    def testStats(self):
        data=readData.GraphReader()
        allStats=data.getStats()
        self.assertFalse("HAHAHA",allStats)
    
    
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()