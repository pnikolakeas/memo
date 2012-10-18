/*
 * Copyright (C) 2010 Dimitrios Menounos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package memo.web.admin.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import memo.web.admin.client.menu.CmsMenuBar;
import memo.web.admin.client.node.NodePlace;

public class AdminEntryPoint implements EntryPoint {

	private EventBus eventBus;
	private DockLayoutPanel dockPanel;

	public void onModuleLoad() {
		initPanels();
		initMVP();
	}

	private void initPanels() {
		dockPanel = new DockLayoutPanel(Unit.PX);
		dockPanel.addStyleName("RootDockLayout");
		dockPanel.addNorth(new CmsMenuBar(), 20);

		RootLayoutPanel rootPanel = RootLayoutPanel.get();
		rootPanel.addStyleName("RootLayout");
		rootPanel.add(dockPanel);
	}

	private void initMVP() {
		eventBus = new SimpleEventBus();

		// ActivityManager switches the current
		// Activity in response to PlaceChangeEvents
		ActivityMapper activityMapper = new CmsActivityMapper();
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(new AcceptsOneWidget() {

			private Widget widget;

			@Override
			public void setWidget(IsWidget w) {
				if (widget != null) {
					dockPanel.remove(widget);
				}

				if (w != null) {
					widget = w.asWidget();
					dockPanel.add(widget);
				}
			}
		});

		// PlaceController maintains the global Place state and also
		// fires PlaceChangeEvents when switching between activities
		PlaceController placeController = new PlaceController(eventBus);

		// PlaceHistoryHandler updates the History in response to
		// PlaceChangeEvents
		PlaceHistoryMapper historyMapper = GWT.create(CmsPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, new NodePlace());
		historyHandler.handleCurrentHistory();
	}
}
