'''
Created on Nov 6, 2014

@author: dipanshu
'''
# Echo server program
import socket
import threading
import user_data

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
        message=['run']
        while message[0]!='close':
            data=self.conn.recv(1024)
            #data='HTTP/1.0 200 OK\r\n\r\n <html><body><h1> LOL</h1></body></html>' 
            message=['run']
            message=data.split(",")
            print message
            if message[0]=='Register':
                user.add_key_val(message[1], message[2])
                print user.get_all_key()
                self.conn.sendall(data)
            if message[0]=='REQUEST':
                data=user.get_all_key()
                data = '-'.join(data)
                data =data +'\n'
                self.conn.sendall(data)
            if message[0]=='PUT':
                if user.get_val(message[2])==[] :
                    recv_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                    recv_soc.connect(('localhost',int(user.get_val(message[1]))))
                    recv_soc.sendall("Reciever Unavailable \n")
                    recv_soc.close()
                else:
                    recv_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                    recv_soc.connect(('localhost',int(user.get_val(message[2]))))
                    recv_soc.sendall(message[3]+"-from "+message[1]+"\n")
                    recv_soc.close()
                    #self.conn.sendall("Message Send\n")
                message=['run']
        self.conn.close()



HOST = ''                # Symbolic name meaning all available interfaces
PORT = 5011              # Arbitrary non-privileged port
file_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
file_soc.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)#reuse the port
file_soc.bind((HOST, PORT))
file_soc.listen(5)
addr_dict = {} #store all user address
user= user_data.User()
while 1 :
    conn,addr= file_soc.accept()
    addr_dict[addr]=conn
    background = TASK(conn,addr,addr_dict)
    background.start()
    