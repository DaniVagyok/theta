package hu.bme.mit.inf.ttmc.formalism.tcfa.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.inf.ttmc.formalism.common.decl.ClockDecl;
import hu.bme.mit.inf.ttmc.formalism.common.decl.VarDecl;
import hu.bme.mit.inf.ttmc.formalism.tcfa.TCFA;
import hu.bme.mit.inf.ttmc.formalism.tcfa.TCFAEdge;
import hu.bme.mit.inf.ttmc.formalism.tcfa.TCFALoc;

public final class NetworkTCFA implements TCFA {

	private final List<TCFA> tcfas;
	private final TCFALoc initLoc;

	public NetworkTCFA(final List<? extends TCFA> tcfas) {
		this.tcfas = ImmutableList.copyOf(checkNotNull(tcfas));

		initLoc = new NetworkTCFALoc(getInitLocs(tcfas));
	}

	private static List<TCFALoc> getInitLocs(final List<? extends TCFA> tcfas) {
		return tcfas.stream().map(TCFA::getInitLoc).collect(toList());
	}

	@Override
	public TCFALoc getInitLoc() {
		return initLoc;
	}

	@Override
	public Collection<? extends TCFALoc> getLocs() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}

	@Override
	public Collection<? extends TCFAEdge> getEdges() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}

	@Override
	public Collection<? extends VarDecl<?>> getDataVars() {
		final Collection<VarDecl<?>> dataVars = new HashSet<>();
		for (final TCFA tcfa : tcfas) {
			dataVars.addAll(tcfa.getDataVars());
		}
		return Collections.unmodifiableCollection(dataVars);
	}

	@Override
	public Collection<? extends ClockDecl> getClockVars() {
		final Collection<ClockDecl> clockVars = new HashSet<>();
		for (final TCFA tcfa : tcfas) {
			clockVars.addAll(tcfa.getClockVars());
		}
		return Collections.unmodifiableCollection(clockVars);
	}

}
