# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

textport = sys.argv[1]

# open a new socket
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = ('localhost', port)

# added a second server address to allow for resending files
server_address2 = ('localhost', 4)
s.bind(server_address)

while True:

    print ("Waiting to receive on port %d : press Ctrl-C or Ctrl-Break to stop " % port)

    buf, address = s.recvfrom(port)
    if not len(buf):
        break
    print ("Received %s bytes from %s %s: " % (len(buf), address, buf ))
    #changes byte datatype to string
    string = str(buf, 'utf-8')
    print (string)
 
    #sending back the data ackknlodging it 
    data = (string)
    print(data)
    arr = bytes(data, 'utf-8')
    print(arr)
#   s.sendall(data.encode('utf-8'))
    sent = s.sendto(arr, server_address2)


s.shutdown(1)
