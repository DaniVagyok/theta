package hu.bme.mit.inf.theta.cegar.interpolatingcegar.steps.refinement;

import java.util.List;

import hu.bme.mit.inf.theta.cegar.interpolatingcegar.data.Interpolant;
import hu.bme.mit.inf.theta.cegar.interpolatingcegar.data.InterpolatedAbstractState;
import hu.bme.mit.inf.theta.cegar.interpolatingcegar.data.InterpolatedAbstractSystem;

public interface Splitter {
	public int split(InterpolatedAbstractSystem system, List<InterpolatedAbstractState> abstractCounterEx, Interpolant interpolant);
}