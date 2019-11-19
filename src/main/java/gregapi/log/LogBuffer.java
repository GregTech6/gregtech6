/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

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
	public final List<String> mBufferedLog = new ArrayListNoNulls<>();
	
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
