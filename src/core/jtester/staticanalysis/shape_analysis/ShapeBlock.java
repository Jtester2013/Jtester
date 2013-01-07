package core.jtester.staticanalysis.shape_analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import core.common.cfg.interfaces.*;
import core.common.cfg.model.AbstractBasicBlock;
import core.common.model.functionblock.AnalysisBlockImpl;

public class ShapeBlock extends AnalysisBlockImpl {

	public ShapeBlock(IBasicBlock bb, int lable) {
		super(bb, lable);
	}

	// 这个地方还要好好考虑一下，现在的想法是不论是向前分析还是向后分析，先将传入的表达式放入_temp，然后对_temp进行kill和gen的操作
	public void tansfer() {
		// 清空_temp
		this._temp.clear();
		// 将_entry的元素加到_temp
		// this._temp = (ArrayList<Object>) this._entry.clone();
		if (this._entry.size() != 0) {
			for (int i = 0; i < this._entry.size(); i++) {
				try {
					this._temp.add(((ShapeGraph) this._entry.get(i)).clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}

		this.update();

		// 将_temp的元素赋给_exit
		if (this._temp.size() != 0) {
			this._exit.clear();
			for (int i = 0; i < this._temp.size(); i++) {
				try {
					this._exit.add(((ShapeGraph) this._temp.get(i)).clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void update() {
		if (_bb instanceof IStartNode) {
			this.setInitLink();
		} else if (_bb instanceof IPlainNode) {
			this.updateIPlainNode();
		}
	}

	private void setInitLink() {
		ArrayList<String> ln = new ArrayList<String>();
		ln.add("p");

		AbstractLocation al5 = new AbstractLocation("CONCRETE", ln);
		AbstractState as = new AbstractState("p", al5);

		AbstractHeap ah = new AbstractHeap();

		ArrayList<String> ln2 = new ArrayList<String>();
		ln2.add("p");
		AbstractLocation al1 = new AbstractLocation("CONCRETE", ln2);

		ArrayList<String> ln3 = new ArrayList<String>();
		AbstractLocation al2 = new AbstractLocation("NILL", ln3);
		ah.addLinkedPair(al1, al2);

		ArrayList<String> ln4 = new ArrayList<String>();
		AbstractLocation al3 = new AbstractLocation("NILL", ln4);
		ArrayList<String> ln5 = new ArrayList<String>();
		AbstractLocation al4 = new AbstractLocation("NILL", ln5);
		ah.addLinkedPair(al3, al4);

		ShapeGraph sg = new ShapeGraph(as, ah);

		this._temp.add(sg);
	}

	private void updateIPlainNode() {
		// 先判断是否是初始化或者赋值语句
		ASTNode node = (ASTNode) ((AbstractBasicBlock) this._bb).getData();

		if (node instanceof ExpressionStatement) {
			Expression exp = ((ExpressionStatement) node).getExpression();
			if (exp instanceof Assignment) {
				Assignment assignment = (Assignment) exp;
				Expression leftExp = assignment.getLeftHandSide();
				Expression rightExp = assignment.getRightHandSide();
				
				if(leftExp instanceof SimpleName && rightExp instanceof SimpleName){
					this.update4AssXX(leftExp, rightExp);
				}else if(leftExp instanceof SimpleName && rightExp instanceof QualifiedName){
					this.update4AssXXSel(leftExp, rightExp);
				}else if(leftExp instanceof QualifiedName && rightExp instanceof SimpleName){
					this.update4AssXSelY(leftExp, rightExp);
				}
			}
			
		} else if (node instanceof VariableDeclarationStatement) {
			VariableDeclarationStatement statement = (VariableDeclarationStatement) node;
			List fragments = statement.fragments();
			
			for (int i = 0; i < fragments.size(); i++) {
				VariableDeclarationFragment fragment = (VariableDeclarationFragment) fragments.get(i);
				SimpleName name = fragment.getName();
				Expression exp = fragment.getInitializer();
				if(exp == null){
				} else if(exp instanceof SimpleName){
					this.update4DelXY(name, exp);
				} else if(exp.toString().equals("null")){
					this.update4DelXY(name, exp);
				} else if(exp instanceof QualifiedName){
					this.update4DelXYsel(name, exp);
				} 
			}
		} 
	}

	private void update4DelXYsel(Expression name, Expression exp) {
	}

	private void update4DelXY(Expression name, Expression initValue) {
		ArrayList<Object> temp = new ArrayList<Object>();
		int size = this._temp.size();
		for (int i = 0; i < size; i++) {
			ArrayList<ShapeGraph> sgs = ((ShapeGraph) this._temp.get(i))
					.transfer4DelXY(name, initValue);
			for (int j = 0; j < sgs.size(); j++) {
				ShapeGraph sg = sgs.get(j);
				temp.add(sg);
			}
		}

		this._temp.clear();
		this._temp = temp;
	}

	private void update4AssXSelY(Expression oprand1, Expression oprand2) {
		ArrayList<Object> temp = new ArrayList<Object>();
		int size = this._temp.size();
		for (int i = 0; i < size; i++) {
			ArrayList<ShapeGraph> sg = ((ShapeGraph) this._temp.get(i)).transfer4AssXSelY(oprand1, oprand2);
			for (int j = 0; j < sg.size(); j++) {
				temp.add(sg.get(j));
			}
		}
		this._temp.clear();
		this._temp = temp;
	}

	private void update4AssXXSel(Expression oprand1, Expression oprand2) {
		ArrayList<Object> temp = new ArrayList<Object>();
		int size = this._temp.size();
		for (int i = 0; i < size; i++) {
			ArrayList<ShapeGraph> sg = ((ShapeGraph) this._temp.get(i))
					.transfer4AssXXSel(oprand1, oprand2);
			for (int j = 0; j < sg.size(); j++) {
				temp.add(sg.get(j));
			}
		}
		this._temp.clear();
		this._temp = temp;
	}

	private void update4AssXX(Expression oprand1, Expression oprand2) {
		ArrayList<Object> temp = new ArrayList<Object>();
		int size = this._temp.size();
		for (int i = 0; i < size; i++) {
			ArrayList<ShapeGraph> sgs = ((ShapeGraph) this._temp.get(i)).transfer4AssXY(oprand1, oprand2);
			for (int j = 0; j < sgs.size(); j++) {
				ShapeGraph sg = sgs.get(j);
				temp.add(sg);
			}
		}
		this._temp.clear();
		this._temp = temp;
	}
}
