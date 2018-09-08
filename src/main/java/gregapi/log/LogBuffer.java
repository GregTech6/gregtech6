package gregapi.log;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import gregapi.code.ArrayListNoNulls;

/**
 * @author Gregorius Techneticies
 * 
 * GT_Log doesn't exist anymore and the Logs are now inside CS.java
 */
public class LogBuffer extends PrintStream {
    public final List<String> mBufferedLog = new ArrayListNoNulls();
    
	@SuppressWarnings("resource")
	public LogBuffer() {
		super(new OutputStream() {@Override public void write(int arg0) {/*Do nothing*/}});
	}
	
	@Override
	public void println(String aString) {
		mBufferedLog.add(aString);
	}
	
	@Override
	public void print(String aString) {
		mBufferedLog.add(aString);
	}
}