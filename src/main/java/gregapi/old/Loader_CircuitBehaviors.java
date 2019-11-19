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

package gregapi.old;

public class Loader_CircuitBehaviors implements Runnable {
	@Override
	public void run() {
		new GT_Circuit_Timer(0);
		new GT_Circuit_BasicLogic(1);
		new GT_Circuit_Repeater(2);
		new GT_Circuit_Pulser(3);
		new GT_Circuit_RedstoneMeter(4);
		
		new GT_Circuit_Randomizer(8);
		
		new GT_Circuit_CombinationLock(16);
		new GT_Circuit_BitAnd(17);
		new GT_Circuit_Equals(18);
	}
}
