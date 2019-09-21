import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPSender {

	private final static int PACKETSIZE = 100 ;
	
	public static void main(String[] args) 
   {
		// Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
	         int n 			  = Integer.parseInt( args[2] ) ;
	         socket = new DatagramSocket() ;
     
	         Scanner in;
	         in = new Scanner (System.in);
	         String message = null;

	         while (true)
	         {
	        	 for(int i = 0; i < n; i++) {
	        		 message = "Message" + i;
	        		 byte [] data = message.getBytes() ;
	        		 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        		 socket.send( packet ) ;
	        		 
	        		 System.out.println( "Receiving on port " + port ) ;
		 		     DatagramPacket packet2 = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE );
		 		     
		 		     // Sleeps the thread to make sure that the viewer can see the response before the sending
		 		     try {
		 		    	 Thread.sleep(1000);
		 		    	 socket.receive( packet2 ) ;
		 		     } catch(IOException e) {
		 		    	 e.printStackTrace();
		 		    	 System.exit(1);
		 		     }
		 		     
		 	         String message2 = new String(packet.getData()).trim();
			         System.out.println("ACK: " + message2 ) ;
	        	 }
	 
	         } 
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}
