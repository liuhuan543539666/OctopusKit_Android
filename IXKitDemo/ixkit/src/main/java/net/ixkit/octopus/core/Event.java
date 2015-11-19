/**
 * Event Interface
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */

package net.ixkit.octopus.core;


public interface Event<T> {
    public Object beforeSend(T sender);
    
    public Object dataFilter(T response);
    
    public Object success(T response);
    
    public Exception error(Exception error );
    
    public Object complete(T response);
}
