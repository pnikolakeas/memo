package memo.web.admin.client.node;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import memo.web.admin.client.node.tree.NodeTreeViewModel;

public class NodeView extends Composite {

	interface Binder extends UiBinder<Widget, NodeView> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided = true)
	CellTree cellTree;

	public NodeView() {
		cellTree = new CellTree(new NodeTreeViewModel(), null);

		initWidget(binder.createAndBindUi(this));
	}
}
