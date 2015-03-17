'''
Created on Oct 9, 2014

@author: dipanshu
'''
import unittest
import QueryData

class Test(unittest.TestCase):

   
    def testGraph(self):
        query=QueryData.Query("datafile.json")
        data=query
        code=data.getCode("Sydney")
        self.assertEquals("SYD",code)
        
    def testPrintCities(self):
        query=QueryData.Query("datafile.json")
        data=query
        allCities=data.printCities()
        self.assertNotEqual("HAHAHA",allCities)
   
    def testStats(self):
        query=QueryData.Query("datafile.json")
        data=query
        allStats=data.getStats()
        self.assertNotEqual("WRONG ANSWER",allStats)
    
    def testPrintRoute(self):
        query=QueryData.Query("datafile.json")
        data=query
        allStats=data.findPath("Delhi","Sydney")
        arr=['DEL', 'CCU', 'BKK', 'JKT', 'SYD']
        self.assertEqual(arr,allStats)
    
    def testShortestRoute(self):
        query=QueryData.Query("datafile.json")
        data=query
        allStats=data.findPath("Delhi","Sydney")
        arr=['DEL','MUM', 'CCU', 'BKK', 'JKT', 'SYD']
        self.assertNotEqual(arr,allStats)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()