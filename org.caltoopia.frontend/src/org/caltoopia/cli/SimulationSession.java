package org.caltoopia.cli;

public class SimulationSession {

	public String calsimPath;
	
	public String systemcPath;
	
	public SimulationSession(String calsimPath, String systemcPath) {
		if (calsimPath == null || systemcPath == null) {
			throw new CompilationError("Invalid or incomplete paths for simulation");
		}
		
		this.calsimPath = calsimPath;
		this.systemcPath = systemcPath;
	}
}
