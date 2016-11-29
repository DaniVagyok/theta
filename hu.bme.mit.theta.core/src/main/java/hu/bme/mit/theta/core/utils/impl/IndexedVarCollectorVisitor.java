package hu.bme.mit.theta.core.utils.impl;

import hu.bme.mit.theta.core.expr.ArrayReadExpr;
import hu.bme.mit.theta.core.expr.ArrayWriteExpr;
import hu.bme.mit.theta.core.expr.BinaryExpr;
import hu.bme.mit.theta.core.expr.ConstRefExpr;
import hu.bme.mit.theta.core.expr.FuncAppExpr;
import hu.bme.mit.theta.core.expr.FuncLitExpr;
import hu.bme.mit.theta.core.expr.IndexedConstRefExpr;
import hu.bme.mit.theta.core.expr.IteExpr;
import hu.bme.mit.theta.core.expr.MultiaryExpr;
import hu.bme.mit.theta.core.expr.NullaryExpr;
import hu.bme.mit.theta.core.expr.ProcCallExpr;
import hu.bme.mit.theta.core.expr.UnaryExpr;
import hu.bme.mit.theta.core.type.Type;

public final class IndexedVarCollectorVisitor extends ArityBasedExprVisitor<IndexedVars.Builder, Void> {

	private static final IndexedVarCollectorVisitor INSTANCE;

	static {
		INSTANCE = new IndexedVarCollectorVisitor();
	}

	private IndexedVarCollectorVisitor() {
	}

	public static IndexedVarCollectorVisitor getInstance() {
		return INSTANCE;
	}

	@Override
	public <DeclType extends Type> Void visit(final ConstRefExpr<DeclType> expr, final IndexedVars.Builder param) {
		if (expr instanceof IndexedConstRefExpr) {
			final IndexedConstRefExpr<DeclType> iConstRef = (IndexedConstRefExpr<DeclType>) expr;
			param.add(iConstRef.getDecl());
		}
		return null;
	}

	@Override
	protected <ExprType extends Type> Void visitNullary(final NullaryExpr<ExprType> expr,
			final IndexedVars.Builder param) {
		return null;
	}

	@Override
	protected <OpType extends Type, ExprType extends Type> Void visitUnary(final UnaryExpr<OpType, ExprType> expr,
			final IndexedVars.Builder param) {
		expr.getOp().accept(this, param);
		return null;
	}

	@Override
	protected <LeftOpType extends Type, RightOpType extends Type, ExprType extends Type> Void visitBinary(
			final BinaryExpr<LeftOpType, RightOpType, ExprType> expr, final IndexedVars.Builder param) {
		expr.getLeftOp().accept(this, param);
		expr.getRightOp().accept(this, param);
		return null;
	}

	@Override
	protected <OpsType extends Type, ExprType extends Type> Void visitMultiary(
			final MultiaryExpr<OpsType, ExprType> expr, final IndexedVars.Builder param) {
		expr.getOps().stream().forEach(o -> o.accept(this, param));
		return null;
	}

	@Override
	public <IndexType extends Type, ElemType extends Type> Void visit(final ArrayReadExpr<IndexType, ElemType> expr,
			final IndexedVars.Builder param) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <IndexType extends Type, ElemType extends Type> Void visit(final ArrayWriteExpr<IndexType, ElemType> expr,
			final IndexedVars.Builder param) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <ParamType extends Type, ResultType extends Type> Void visit(final FuncLitExpr<ParamType, ResultType> expr,
			final IndexedVars.Builder param) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <ParamType extends Type, ResultType extends Type> Void visit(final FuncAppExpr<ParamType, ResultType> expr,
			final IndexedVars.Builder param) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <ReturnType extends Type> Void visit(final ProcCallExpr<ReturnType> expr, final IndexedVars.Builder param) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <ExprType extends Type> Void visit(final IteExpr<ExprType> expr, final IndexedVars.Builder param) {
		expr.getCond().accept(this, param);
		expr.getThen().accept(this, param);
		expr.getElse().accept(this, param);
		return null;
	}

}