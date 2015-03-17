'''
Created on Nov 6, 2014

@author: dipanshu
'''
# Echo server program
import socket
import threading

class TASK(threading.Thread):
    def __init__(self,connection,address,addr_dict):
        self.addr=address
        self.conn=connection
        self.addr_dict=addr_dict
        threading.Thread.__init__(self)
    
    def run(self):
        '''
        Worker thread to process every request recieved
        '''
        while 1:
            data=self.conn.recv(1024)
            #data='HTTP/1.0 200 OK\r\n\r\n <html><body><h1> LOL</h1></body></html>' 
            print data
            self.conn.sendall(data)
        self.conn.close()



HOST = ''                # Symbolic name meaning all available interfaces
PORT = 5008              # Arbitrary non-privileged port
file_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
file_soc.bind((HOST, PORT))
file_soc.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)#reuse the port
file_soc.listen(5)
addr_dict = {} #store all user address
while 1 :
    conn,addr= file_soc.accept()
    addr_dict[addr]=conn
    background = TASK(conn,addr,addr_dict)
    background.start()
    