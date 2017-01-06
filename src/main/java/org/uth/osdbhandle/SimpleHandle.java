package org.uth.osdbhandle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Simple Handle for tests.
 * @author Ian Lawson
 */
public class SimpleHandle 
{
  private Connection _connection = null;  
  private Statement _statement = null;
  private ResultSet _resultsSet = null;
  
  private String _driver = null;
  private String _connectionString = null;
  private String _username = null;
  private String _password = null;
  
  public SimpleHandle( String driver, String connectionString, String username, String password )
  {
    _driver = driver;
    _connectionString = connectionString;
    _username = username;
    _password = password;
  }
  
  public void initialise() throws SQLException, ClassNotFoundException
  {
    // Register the driver
    Class.forName( _driver );
    
    // Setup the Connection
    _connection = DriverManager.getConnection(_connectionString, _username, _password);
  }
  
  public void close() throws SQLException
  {
    if( _statement != null ) _statement.close();
    if( _connection != null ) _connection.close();
  }
  
  public ResultSet query( String sqlStatement ) throws SQLException
  {
    _statement = _connection.createStatement();
    
    _resultsSet = _statement.executeQuery( sqlStatement );
    
    return _resultsSet;
  }
  
  public int update( String sqlStatement ) throws SQLException
  {
    _statement = _connection.createStatement();
    
    return _statement.executeUpdate( sqlStatement );
  }
}
