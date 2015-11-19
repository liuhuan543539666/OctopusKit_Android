/**
 * Argument
 * <p/>
 *
 * For more information, visit the project page:
 * https://github.com/icoco/ixkit
 *
 * @author Robin Cheung <iRobinCheung@hotmail.com>
 * @version 1.0.1
 */
package net.ixkit.octopus.lang;

import java.util.HashMap;



public class Argument  {
 	public static <T> HashMap<T, T> toMap(T ... arguments ){
		HashMap<T, T> result = new HashMap<T, T>();
		if (null == arguments){
			return result; 
		}
		int count = arguments.length;
		for (int i = 0; i < count; i++){
			T key = arguments[i];
			T  value = arguments[i+1];
			i = i + 1;
			//@step
			result.put(key, value);
		}	
		 return result;
	}	
}
