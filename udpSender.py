# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

host = sys.argv[1]
textport = sys.argv[2]
n = int(sys.argv[3])

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)
server_address2 = (host, 4)
s.bind(server_address2)

while 1:
	for x in range(n):
	  print ("Enter data to transmit: ENTER to quit")
	  data = sys.stdin.readline().strip()
	  if not len(data):
		  break
#	  s.sendall(data.encode('utf-8'))
	  s.sendto(data.encode('utf-8'), server_address)

	  print ("Waiting to receive on port %d : press Ctrl-C or Ctrl-Break to stop " % port)
	  buf, address = s.recvfrom(4)
	  if not len(buf):
	    	  break
	  print ("Received %s bytes from %s %s: " % (len(buf), address, buf ))
	
s.shutdown(1)

