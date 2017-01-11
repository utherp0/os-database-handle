package org.uth.osdbhandle.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;
import org.uth.osdbhandle.SimpleHandle;

/**
 * Wrapper around the SimpleHandle class for tests.
 * @author Ian Lawson
 */
public class HandleTest1 
{
  public static void main( String[] args )
  {
    if( args.length != 4 )
    {
      System.out.println( "Usage: java HandleTest1 driver connection username password");
      System.exit(0);
    }
    
    new HandleTest1( args[0], args[1], args[2], args[3] );
  }
  
  private void log( String message )
  {
    System.out.println( "[HandleTest1] " + message );
  }
  
  public HandleTest1( String driver, String connectionString, String username, String password )
  {
    SimpleHandle handle = new SimpleHandle( driver, connectionString, username, password );
    
    try
    {
      // Initialise Handle
      long start = System.currentTimeMillis();
      handle.initialise();
      long end = System.currentTimeMillis();
      
      Scanner input = new Scanner( System.in );
      
      String command = "welcome";
      String statement = "";
      
      while( !command.equalsIgnoreCase("quit") )
      {
        System.out.print( "Command: (query/update/quit): " );
        
        command = input.nextLine();
        
        switch( command )
        {
          case "query":
            System.out.print( "  Query: " );
            statement = input.nextLine();
            
            ResultSet results = handle.query(statement);
            ResultSetMetaData metadata = results.getMetaData();
            
            log( "Columns returned - " + metadata.getColumnCount());
            
            for( int loop = 0; loop < metadata.getColumnCount(); loop++ )
            {
              String columnName = metadata.getColumnName(loop);
              log( "  Pos: " + loop + " Name: " + columnName );
            }
            
            break;
            
          case "update":
            System.out.print( "  Update: " );
            statement = input.nextLine();
            
            int updateCount = handle.update(statement);
            
            log( "Update returned a count of " + updateCount );
            
            break;
            
          case "quit":
            break;
            
          default:
            log( "Unknown command " + command );
            break;
        }
      }
    }
    catch( Exception exc )
    {
      log( "Test failed due to " + exc.toString());
    }
  }
}
