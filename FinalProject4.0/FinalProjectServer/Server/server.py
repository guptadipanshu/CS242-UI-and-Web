'''
Created on Nov 6, 2014

@author: dipanshu
'''
# Echo server program
import socket
import threading
import user_data
import time

def get_db():
    from pymongo import MongoClient
    client = MongoClient('localhost:27017')
    db = client.myFirstMB
    return db

class TASK(threading.Thread):
    
    def __init__(self,connection,address,addr_dict):
        '''
        
        :param connection: connection
        :param address: localhost
        :param addr_dict: internal ds
        '''
        self.addr=address
        self.conn=connection
        self.addr_dict=addr_dict
        threading.Thread.__init__(self)
    
    def get_message(self,message):
        '''
        return the message tyoe
        :param message: array of message
        '''
        return message[0]

    def get_history(self, message, recv_soc):
        '''
        Get user receiver history from database
        :param message: encoding to get message
        :param recv_soc: address at which client exists
        '''
        recv_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        recv_soc.connect(('localhost', int(user.get_val(message[1])))) #print (db.chat.find({"from": { "$in": message[1] }}))
        cursor = db.chat.find({"data.from":message[1]}, {"data":{"$elemMatch":{"from":message[1]}}})
        final_msg = ""
        for doc in cursor:
            for row in doc['data']:
                print row['message']
                final_msg = final_msg + row['message'] + "-" + row['time'] + "\t"
        
        print final_msg
        recv_soc.sendall(final_msg + "\n")
        print list(db.chat.find())
        recv_soc.close()


    def forward_msg(self, message):
        '''
        Send message from client to receiver
        :param message: message encoding to be send
        '''
        if user.get_val(message[2]) == []:
            recv_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            recv_soc.connect(('localhost', int(user.get_val(message[1]))))
            recv_soc.sendall("Reciever Unavailable \n")
            recv_soc.close()
        else:
            recv_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            recv_soc.connect(('localhost', int(user.get_val(message[2]))))
            recv_soc.sendall(message[3] + "-from " + message[1] + "\t\t\n")
            recv_soc.close()
            msg_time = time.strftime("%H:%M:%S")
            db.chat.insert({"data":[{"from":message[1], "message":message[3], "to":message[2], "time":msg_time}]})
        #self.conn.sendall("Message Send\n")
        return recv_soc


    def get_users(self, data):
        '''
        send all the peers in chat
        :param data: list of users in chat room
        '''
        data = user.get_all_key()
        data = '-'.join(data)
        data = data + '\n'
        self.conn.sendall(data)
        print data


    def register_user(self, message, data):
        '''
        register a user with the server end
        :param message: message encoding to register user
        :param data: reply for the user
        '''
        user.add_key_val(message[1], message[2])
        print user.get_all_key()
        self.conn.sendall(data)

    def run(self):
        '''
        Worker thread to process every request recieved
        '''
        message=['run']
        while self.get_message(message) !='close':
            data=self.conn.recv(1024)
            #data='HTTP/1.0 200 OK\r\n\r\n <html><body><h1> LOL</h1></body></html>' 
            message=['run']
            message=data.split(",")
            #print message
            if self.get_message(message)=='Register':
                self.register_user(message, data)
            if self.get_message(message)=='REQUEST':
                self.get_users(data)
            if self.get_message(message)=='PUT':
                recv_soc = self.forward_msg(message)
            if self.get_message(message)=='GET':
                self.get_history(message, recv_soc)
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
db = get_db() 
#db.chat.remove()
while 1 :
    conn,addr= file_soc.accept()
    addr_dict[addr]=conn
    background = TASK(conn,addr,addr_dict)
    background.start()
    