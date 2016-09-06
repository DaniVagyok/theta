package hu.bme.mit.inf.theta.formalism.ta.op.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static hu.bme.mit.inf.theta.core.expr.impl.Exprs.Add;
import static hu.bme.mit.inf.theta.core.expr.impl.Exprs.Int;
import static hu.bme.mit.inf.theta.formalism.common.stmt.impl.Stmts.Assign;

import java.util.Collection;

import com.google.common.collect.ImmutableSet;

import hu.bme.mit.inf.theta.core.type.RatType;
import hu.bme.mit.inf.theta.formalism.common.decl.ClockDecl;
import hu.bme.mit.inf.theta.formalism.common.stmt.AssignStmt;
import hu.bme.mit.inf.theta.formalism.ta.op.ShiftOp;
import hu.bme.mit.inf.theta.formalism.ta.utils.ClockOpVisitor;

public class ShifOpImpl implements ShiftOp {

	private static final int HASH_SEED = 5521;

	private final ClockDecl clock;
	private final int offset;

	private volatile int hashCode = 0;
	private volatile AssignStmt<RatType, RatType> stmt = null;

	ShifOpImpl(final ClockDecl clock, final int offset) {
		this.clock = checkNotNull(clock);
		this.offset = offset;
	}

	@Override
	public ClockDecl getClock() {
		return clock;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public Collection<? extends ClockDecl> getClocks() {
		return ImmutableSet.of(clock);
	}

	@Override
	public AssignStmt<RatType, RatType> toStmt() {
		AssignStmt<RatType, RatType> result = stmt;
		if (result == null) {
			result = Assign(clock, Add(clock.getRef(), Int(offset)));
			stmt = result;
		}
		return result;
	}

	@Override
	public <P, R> R accept(final ClockOpVisitor<? super P, ? extends R> visitor, final P param) {
		return visitor.visit(this, param);
	}

	@Override
	public int hashCode() {
		int result = hashCode;
		if (result == 0) {
			result = HASH_SEED;
			result = 31 * result + clock.hashCode();
			result = 31 * result + offset;
			hashCode = result;
		}
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof ShiftOp) {
			final ShiftOp that = (ShiftOp) obj;
			return this.getClock().equals(that.getClock()) && this.getOffset() == that.getOffset();
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Shift(");
		sb.append(clock.getName());
		sb.append(", ");
		sb.append(offset);
		sb.append(")");
		return sb.toString();
	}

}