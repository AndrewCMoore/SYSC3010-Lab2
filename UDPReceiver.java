import java.net.*;

public class UDPReceiver {

	private final static int PACKETSIZE = 100 ;

	public static void main( String args[] )
	{ 

	      // Check the arguments
	      if( args.length != 1 )
	      {
	         System.out.println( "usage: UDPReceiver port" ) ;
	         return ;
	      }
	      try
	      {
	         // Convert the argument to ensure that is it valid
	         int port = Integer.parseInt( args[0] ) ;

	         // Construct the socket
	         DatagramSocket socket = new DatagramSocket( port ) ;

	         for( ;; )
	         {
		        System.out.println( "Receiving on port " + port ) ;
		        DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
	            socket.receive( packet ) ;

	            String message = new String(packet.getData()).trim();
	            System.out.println( packet.getAddress() + " " + packet.getPort() + ": " + message ) ;
	            
		    // Resending the data back to the sender file
	            byte[] data = message.getBytes();
	            DatagramPacket packet2 = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
	            System.out.println("Sending returning message");
	            socket.send(packet2);
	        }  
	     }
	     catch( Exception e )
	     {
	        System.out.println( e ) ;
	     }
  }
}
