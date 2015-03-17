'''
Created on Nov 6, 2014

@author: dipanshu
'''
import unittest
import socket

class Test(unittest.TestCase):


    def testData(self):
        '''
        Test the data recieved by server
        '''
        HOST = 'localhost'    # The remote host
        PORT = 5008             # The same port as used by the server
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((HOST, PORT))
        s.sendall('Hello, world')
        data = s.recv(1024)
        s.close()
        print 'Received', repr(data)
        self.assertEquals("Hello, world",data)
   
    def testMultiConnections(self):
        '''
        Test if server can handle concurrent connections
        '''
        HOST = 'localhost'    # The remote host
        PORT = 5008             # The same port as used by the server
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((HOST, PORT))
        s.sendall('Hello, world')
        data = s.recv(1024)
        s.close()
        print 'Received', repr(data)
        
       
        new_s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        new_s.connect((HOST, PORT))
        new_s.sendall('Hello, world')
        data += new_s.recv(1024)
        new_s.close()
        print 'Received', repr(data)
        self.assertEquals("Hello, worldHello, world",data)
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()