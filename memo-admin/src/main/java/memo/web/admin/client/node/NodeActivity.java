package memo.web.admin.client.node;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class NodeActivity extends AbstractActivity {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		NodeView view = new NodeView();
		panel.setWidget(view);
	}
}
