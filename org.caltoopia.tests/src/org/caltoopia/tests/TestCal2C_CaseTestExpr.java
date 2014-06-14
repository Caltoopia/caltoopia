/* 
 * Copyright (c) Ericsson AB, 2013
 * All rights reserved.
 *
 * License terms:
 *
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the 
 *       following disclaimer.
 *     * Redistributions in binary form must reproduce the 
 *       above copyright notice, this list of conditions and 
 *       the following disclaimer in the documentation and/or 
 *       other materials provided with the distribution.
 *     * Neither the name of the copyright holder nor the names 
 *       of its contributors may be used to endorse or promote 
 *       products derived from this software without specific 
 *       prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.caltoopia.tests;

import static org.junit.Assert.*;
import org.caltoopia.cli.Cal2C;
import org.caltoopia.cli.CompilationSession;
import org.junit.Test;

public class TestCal2C_CaseTestExpr {

	@Test
	public void test() {
		String args[] = {"--top", "a.top", 
				         "--path", Util.getCalAppsDir() + "CaseTestExpr" + ":" + Util.getCalAppsDir() + "System",
				         "--output", Util.getOutputDir() + "CaseTestExpr",
				         "--runtime", Util.getRuntimeDir(), "--clean"};
		CompilationSession session = null;
		try {
			session = Cal2C.compile(args);
			session.setWorkingDirectory(Util.getCalAppsDir() + "CaseTestExpr");
			Util.build(session);
			Util.run(session);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		if(!Util.goldCheck(session,
				Util.getCalAppsDir() + "CaseTestExpr/" + 
					"gold/caseTestExpr.txt", 
				Util.getCalAppsDir() + "CaseTestExpr/" +
					"output/caseTestExpr.txt")) {
			fail("Output differs from gold vector!!!");
		}

	}
}
