package speech.mark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String FILE_PATH = "/home/orange/Documents/AIJAVA/kernal/";
	
    public static void main( String[] args )
    {
    
    	String fileName = FILE_PATH+"greeting.txt";

		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(line->{
				Speeker.getSpeek(line);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
